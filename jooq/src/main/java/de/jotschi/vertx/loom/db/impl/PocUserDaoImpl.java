package de.jotschi.vertx.loom.db.impl;

import static de.jotschi.vertx.loom.util.JooqWrapperHelper.unwrap;
import static de.jotschi.vertx.loom.util.JooqWrapperHelper.wrap;

import java.util.Objects;
import java.util.UUID;

import de.jotschi.vertx.loom.db.PocUser;
import de.jotschi.vertx.loom.db.PocUserDao;
import de.jotschi.vertx.loom.db.jooq.tables.daos.UserDao;
import de.jotschi.vertx.loom.db.jooq.tables.pojos.User;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class PocUserDaoImpl implements PocUserDao {

	private final UserDao userDao;

	public PocUserDaoImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Single<? extends PocUser> createUser(String username) {
		User user = new User();
		user.setUsername(username);
		return userDao.insertReturningPrimary(user).map(pk -> new PocUserImpl(user.setUuid(pk)));
	}

	@Override
	public Maybe<? extends PocUser> loadUser(UUID uuid) {
		return wrap(userDao.findOneById(uuid), PocUserImpl.class);
	}

	@Override
	public Completable updateUser(PocUser user) {
		Objects.requireNonNull(user, "User must not be null");
		return userDao.update(unwrap(user)).ignoreElement();
	}

	@Override
	public Completable deleteUser(PocUser user) {
		Objects.requireNonNull(user, "User must not be null");
		return userDao.deleteById(user.getUuid()).ignoreElement();
	}

}
