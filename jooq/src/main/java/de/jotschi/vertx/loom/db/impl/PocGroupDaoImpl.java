package de.jotschi.vertx.loom.db.impl;

import static de.jotschi.vertx.loom.db.jooq.tables.User.USER;
import static de.jotschi.vertx.loom.db.jooq.tables.UserGroup.USER_GROUP;
import static de.jotschi.vertx.loom.util.JooqWrapperHelper.unwrap;
import static de.jotschi.vertx.loom.util.JooqWrapperHelper.wrap;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.jooq.Configuration;
import org.jooq.ResultQuery;

import de.jotschi.vertx.loom.db.PocGroup;
import de.jotschi.vertx.loom.db.PocGroupDao;
import de.jotschi.vertx.loom.db.PocUser;
import de.jotschi.vertx.loom.db.jooq.tables.daos.GroupDao;
import de.jotschi.vertx.loom.db.jooq.tables.daos.UserDao;
import de.jotschi.vertx.loom.db.jooq.tables.daos.UserGroupDao;
import de.jotschi.vertx.loom.db.jooq.tables.pojos.Group;
import de.jotschi.vertx.loom.db.jooq.tables.pojos.User;
import de.jotschi.vertx.loom.db.jooq.tables.pojos.UserGroup;
import de.jotschi.vertx.loom.db.jooq.tables.records.UserGroupRecord;
import de.jotschi.vertx.loom.db.jooq.tables.records.UserRecord;
import de.jotschi.vertx.loom.util.JooqWrapperHelper;
import io.github.jklingsporn.vertx.jooq.rx3.reactivepg.ReactiveRXQueryExecutor;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class PocGroupDaoImpl extends GroupDao implements PocGroupDao {

  private final UserDao userDao;
  private final UserGroupDao userGroupDao;

  public PocGroupDaoImpl(Configuration configuration, io.vertx.rxjava3.sqlclient.SqlClient delegate, UserDao userDao, UserGroupDao userGroupDao) {
    super(configuration, delegate);
    this.userDao = userDao;
    this.userGroupDao = userGroupDao;
  }

  @Override
  public Maybe<? extends PocGroup> loadGroup(UUID uuid) {
    return wrap(findOneById(uuid), PocGroupImpl.class);
  }

  @Override
  public Completable deleteGroup(UUID uuid) {
    Objects.requireNonNull(uuid, "Group uuid must not be null");
    return deleteById(uuid).ignoreElement();
  }

  @Override
  public Single<PocGroup> createGroup(String name) {
    Group group = new Group();
    group.setName(name);
    return insertReturningPrimary(group).map(pk -> new PocGroupImpl(group.setUuid(pk)));
  }

  @Override
  public Completable updateGroup(PocGroup group) {
    Objects.requireNonNull(group, "Group must not be null");
    return update(unwrap(group)).ignoreElement();
  }

  @Override
  public Completable addUserToGroup(PocGroup group, PocUser user) {
    UserGroup userGroup = new UserGroup();
    userGroup.setGroupUuid(group.getUuid());
    userGroup.setUserUuid(user.getUuid());
    return userGroupDao.insert(userGroup).ignoreElement();
  }

  @Override
  public Completable removeUserFromGroup(PocGroup group, PocUser user) {
    UserGroupRecord record = new UserGroupRecord(user.getUuid(), group.getUuid());
    return userGroupDao.deleteById(record).ignoreElement();
  }

  @Override
  public Observable<PocUser> loadUsers(PocGroup group) {
    ReactiveRXQueryExecutor<UserRecord, User, UUID> queryExecutor = userDao.queryExecutor();
    Single<List<User>> result = queryExecutor.findMany(dslContext -> dslContext.select()
      .from(USER_GROUP
        .join(USER)
        .on(USER.UUID.eq(USER_GROUP.USER_UUID))
        .where(USER_GROUP.GROUP_UUID.eq(group.getUuid())).asTable(USER))
      .coerce(USER));

    return result.flatMapObservable(list -> {
      return Observable.fromIterable(list);
    }).map(jooq -> {
      return JooqWrapperHelper.wrap(jooq, PocUserImpl.class);
    });
  }

  @Override
  public Observable<PocUser> addTwoUsers() {
    Observable<User> txOperation = userDao.queryExecutor().beginTransaction()
      .flatMapObservable(tx -> {
        // Load elements conveniently via the findMany method
        Single<List<User>> existingUsers = tx.findMany(ctx -> {
          ResultQuery<UserRecord> userRecords = ctx.select().from(USER).coerce(USER);
          return userRecords;
        });

        User userPojo = new User();
        userPojo.setUsername("ABCD");

        User userPojo2 = new User();
        userPojo2.setUsername("ABCD2");

        // Inserts can be executed within the given transaction
        Single<User> createdUser1 = tx.executeAny(ctx -> {
          UserRecord record = ctx.newRecord(userDao.getTable(), userPojo);
          return ctx.insertInto(userDao.getTable())
            .set(record)
            .returning(USER.getPrimaryKey().getFieldsArray());
        }).map(rows -> rows.iterator().next())
          .map(io.vertx.rxjava3.sqlclient.Row::getDelegate)
          .map(keyConverter()::apply)
          .map(pk -> userPojo.setUuid(pk));

        // The operation can also be encapsulated within a dedicated method
        Single<User> createdUser2 = DaoOps.insertUser(tx, userPojo2, keyConverter());
        
        // Lets combine the created users with the previously loaded users
        Single<List<User>> s = Single.zip(existingUsers, createdUser1, createdUser2,
          (u1, c1, c2) -> {
            System.out.println("Adding users");
            u1.add(c1);
            u1.add(c2);
            return u1;
          });

        Observable<User> obs = s.flatMapObservable(Observable::fromIterable);
        // Now commit the tx and return the results
        return tx.commit().andThen(obs);
      });

    return txOperation.map(jooq -> {
      return JooqWrapperHelper.wrap(jooq, PocUserImpl.class);
    });
  }
}
