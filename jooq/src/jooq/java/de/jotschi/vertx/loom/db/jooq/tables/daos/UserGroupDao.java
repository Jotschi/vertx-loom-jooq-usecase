/*
 * This file is generated by jOOQ.
 */
package de.jotschi.vertx.loom.db.jooq.tables.daos;


import de.jotschi.vertx.loom.db.jooq.tables.UserGroup;
import de.jotschi.vertx.loom.db.jooq.tables.records.UserGroupRecord;

import io.github.jklingsporn.vertx.jooq.shared.reactive.AbstractReactiveVertxDAO;

import java.util.Collection;
import java.util.UUID;

import org.jooq.Configuration;
import org.jooq.Record2;


import java.util.List;
import io.reactivex.rxjava3.core.Single;
import java.util.Optional;
import io.github.jklingsporn.vertx.jooq.rx3.reactivepg.ReactiveRXQueryExecutor;
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserGroupDao extends AbstractReactiveVertxDAO<UserGroupRecord, de.jotschi.vertx.loom.db.jooq.tables.pojos.UserGroup, Record2<UUID, UUID>, Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.UserGroup>>, Single<Optional<de.jotschi.vertx.loom.db.jooq.tables.pojos.UserGroup>>, Single<Integer>, Single<Record2<UUID, UUID>>> implements io.github.jklingsporn.vertx.jooq.rx.VertxDAO<UserGroupRecord,de.jotschi.vertx.loom.db.jooq.tables.pojos.UserGroup,Record2<UUID, UUID>> {

        /**
     * @param configuration The Configuration used for rendering and query execution.
     * @param vertx the vertx instance
     */
        public UserGroupDao(Configuration configuration, io.vertx.rxjava3.sqlclient.SqlClient delegate) {
                super(UserGroup.USER_GROUP, de.jotschi.vertx.loom.db.jooq.tables.pojos.UserGroup.class, new ReactiveRXQueryExecutor<UserGroupRecord,de.jotschi.vertx.loom.db.jooq.tables.pojos.UserGroup,Record2<UUID, UUID>>(configuration,delegate,de.jotschi.vertx.loom.db.jooq.tables.mappers.RowMappers.getUserGroupMapper()));
        }

        @Override
        protected Record2<UUID, UUID> getId(de.jotschi.vertx.loom.db.jooq.tables.pojos.UserGroup object) {
                return compositeKeyRecord(object.getUserUuid(), object.getGroupUuid());
        }

        /**
     * Find records that have <code>group_uuid IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.UserGroup>> findManyByGroupUuid(Collection<UUID> values) {
                return findManyByCondition(UserGroup.USER_GROUP.GROUP_UUID.in(values));
        }

        /**
     * Find records that have <code>group_uuid IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.UserGroup>> findManyByGroupUuid(Collection<UUID> values, int limit) {
                return findManyByCondition(UserGroup.USER_GROUP.GROUP_UUID.in(values),limit);
        }

        @Override
        public ReactiveRXQueryExecutor<UserGroupRecord,de.jotschi.vertx.loom.db.jooq.tables.pojos.UserGroup,Record2<UUID, UUID>> queryExecutor(){
                return (ReactiveRXQueryExecutor<UserGroupRecord,de.jotschi.vertx.loom.db.jooq.tables.pojos.UserGroup,Record2<UUID, UUID>>) super.queryExecutor();
        }
}
