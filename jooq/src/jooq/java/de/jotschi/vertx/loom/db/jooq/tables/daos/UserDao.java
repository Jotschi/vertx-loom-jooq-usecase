/*
 * This file is generated by jOOQ.
 */
package de.jotschi.vertx.loom.db.jooq.tables.daos;


import de.jotschi.vertx.loom.db.jooq.tables.User;
import de.jotschi.vertx.loom.db.jooq.tables.records.UserRecord;

import io.github.jklingsporn.vertx.jooq.shared.reactive.AbstractReactiveVertxDAO;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import org.jooq.Configuration;


import java.util.List;
import io.reactivex.rxjava3.core.Single;
import java.util.Optional;
import io.github.jklingsporn.vertx.jooq.rx3.reactivepg.ReactiveRXQueryExecutor;
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserDao extends AbstractReactiveVertxDAO<UserRecord, de.jotschi.vertx.loom.db.jooq.tables.pojos.User, UUID, Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>>, Single<Optional<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>>, Single<Integer>, Single<UUID>> implements io.github.jklingsporn.vertx.jooq.rx.VertxDAO<UserRecord,de.jotschi.vertx.loom.db.jooq.tables.pojos.User,UUID> {

        /**
     * @param configuration The Configuration used for rendering and query execution.
     * @param vertx the vertx instance
     */
        public UserDao(Configuration configuration, io.vertx.rxjava3.sqlclient.SqlClient delegate) {
                super(User.USER, de.jotschi.vertx.loom.db.jooq.tables.pojos.User.class, new ReactiveRXQueryExecutor<UserRecord,de.jotschi.vertx.loom.db.jooq.tables.pojos.User,UUID>(configuration,delegate,de.jotschi.vertx.loom.db.jooq.tables.mappers.RowMappers.getUserMapper()));
        }

        @Override
        protected UUID getId(de.jotschi.vertx.loom.db.jooq.tables.pojos.User object) {
                return object.getUuid();
        }

        /**
     * Find records that have <code>username IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByUsername(Collection<String> values) {
                return findManyByCondition(User.USER.USERNAME.in(values));
        }

        /**
     * Find records that have <code>username IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByUsername(Collection<String> values, int limit) {
                return findManyByCondition(User.USER.USERNAME.in(values),limit);
        }

        /**
     * Find records that have <code>firstname IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByFirstname(Collection<String> values) {
                return findManyByCondition(User.USER.FIRSTNAME.in(values));
        }

        /**
     * Find records that have <code>firstname IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByFirstname(Collection<String> values, int limit) {
                return findManyByCondition(User.USER.FIRSTNAME.in(values),limit);
        }

        /**
     * Find records that have <code>lastname IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByLastname(Collection<String> values) {
                return findManyByCondition(User.USER.LASTNAME.in(values));
        }

        /**
     * Find records that have <code>lastname IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByLastname(Collection<String> values, int limit) {
                return findManyByCondition(User.USER.LASTNAME.in(values),limit);
        }

        /**
     * Find records that have <code>passwordhash IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByPasswordhash(Collection<String> values) {
                return findManyByCondition(User.USER.PASSWORDHASH.in(values));
        }

        /**
     * Find records that have <code>passwordhash IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByPasswordhash(Collection<String> values, int limit) {
                return findManyByCondition(User.USER.PASSWORDHASH.in(values),limit);
        }

        /**
     * Find records that have <code>email IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByEmail(Collection<String> values) {
                return findManyByCondition(User.USER.EMAIL.in(values));
        }

        /**
     * Find records that have <code>email IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByEmail(Collection<String> values, int limit) {
                return findManyByCondition(User.USER.EMAIL.in(values),limit);
        }

        /**
     * Find records that have <code>enabled IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByEnabled(Collection<Boolean> values) {
                return findManyByCondition(User.USER.ENABLED.in(values));
        }

        /**
     * Find records that have <code>enabled IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByEnabled(Collection<Boolean> values, int limit) {
                return findManyByCondition(User.USER.ENABLED.in(values),limit);
        }

        /**
     * Find records that have <code>meta IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByMeta(Collection<String> values) {
                return findManyByCondition(User.USER.META.in(values));
        }

        /**
     * Find records that have <code>meta IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByMeta(Collection<String> values, int limit) {
                return findManyByCondition(User.USER.META.in(values),limit);
        }

        /**
     * Find records that have <code>created IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByCreated(Collection<LocalDateTime> values) {
                return findManyByCondition(User.USER.CREATED.in(values));
        }

        /**
     * Find records that have <code>created IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByCreated(Collection<LocalDateTime> values, int limit) {
                return findManyByCondition(User.USER.CREATED.in(values),limit);
        }

        /**
     * Find records that have <code>creator_uuid IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByCreatorUuid(Collection<UUID> values) {
                return findManyByCondition(User.USER.CREATOR_UUID.in(values));
        }

        /**
     * Find records that have <code>creator_uuid IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByCreatorUuid(Collection<UUID> values, int limit) {
                return findManyByCondition(User.USER.CREATOR_UUID.in(values),limit);
        }

        /**
     * Find records that have <code>edited IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByEdited(Collection<LocalDateTime> values) {
                return findManyByCondition(User.USER.EDITED.in(values));
        }

        /**
     * Find records that have <code>edited IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByEdited(Collection<LocalDateTime> values, int limit) {
                return findManyByCondition(User.USER.EDITED.in(values),limit);
        }

        /**
     * Find records that have <code>editor_uuid IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByEditorUuid(Collection<UUID> values) {
                return findManyByCondition(User.USER.EDITOR_UUID.in(values));
        }

        /**
     * Find records that have <code>editor_uuid IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findManyByEditorUuid(Collection<UUID> values, int limit) {
                return findManyByCondition(User.USER.EDITOR_UUID.in(values),limit);
        }

        /**
     * Find a unique record that has <code>username = value</code> asynchronously
     */
        public Single<Optional<de.jotschi.vertx.loom.db.jooq.tables.pojos.User>> findOneByUsername(String value) {
                return findOneByCondition(User.USER.USERNAME.eq(value));
        }

        @Override
        public ReactiveRXQueryExecutor<UserRecord,de.jotschi.vertx.loom.db.jooq.tables.pojos.User,UUID> queryExecutor(){
                return (ReactiveRXQueryExecutor<UserRecord,de.jotschi.vertx.loom.db.jooq.tables.pojos.User,UUID>) super.queryExecutor();
        }
}
