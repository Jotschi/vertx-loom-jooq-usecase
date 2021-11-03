/*
 * This file is generated by jOOQ.
 */
package de.jotschi.vertx.loom.db.jooq.tables.daos;


import de.jotschi.vertx.loom.db.jooq.tables.Group;
import de.jotschi.vertx.loom.db.jooq.tables.records.GroupRecord;

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
public class GroupDao extends AbstractReactiveVertxDAO<GroupRecord, de.jotschi.vertx.loom.db.jooq.tables.pojos.Group, UUID, Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>>, Single<Optional<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>>, Single<Integer>, Single<UUID>> implements io.github.jklingsporn.vertx.jooq.rx.VertxDAO<GroupRecord,de.jotschi.vertx.loom.db.jooq.tables.pojos.Group,UUID> {

        /**
     * @param configuration The Configuration used for rendering and query execution.
     * @param vertx the vertx instance
     */
        public GroupDao(Configuration configuration, io.vertx.rxjava3.sqlclient.SqlClient delegate) {
                super(Group.GROUP, de.jotschi.vertx.loom.db.jooq.tables.pojos.Group.class, new ReactiveRXQueryExecutor<GroupRecord,de.jotschi.vertx.loom.db.jooq.tables.pojos.Group,UUID>(configuration,delegate,de.jotschi.vertx.loom.db.jooq.tables.mappers.RowMappers.getGroupMapper()));
        }

        @Override
        protected UUID getId(de.jotschi.vertx.loom.db.jooq.tables.pojos.Group object) {
                return object.getUuid();
        }

        /**
     * Find records that have <code>name IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>> findManyByName(Collection<String> values) {
                return findManyByCondition(Group.GROUP.NAME.in(values));
        }

        /**
     * Find records that have <code>name IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>> findManyByName(Collection<String> values, int limit) {
                return findManyByCondition(Group.GROUP.NAME.in(values),limit);
        }

        /**
     * Find records that have <code>meta IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>> findManyByMeta(Collection<String> values) {
                return findManyByCondition(Group.GROUP.META.in(values));
        }

        /**
     * Find records that have <code>meta IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>> findManyByMeta(Collection<String> values, int limit) {
                return findManyByCondition(Group.GROUP.META.in(values),limit);
        }

        /**
     * Find records that have <code>created IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>> findManyByCreated(Collection<LocalDateTime> values) {
                return findManyByCondition(Group.GROUP.CREATED.in(values));
        }

        /**
     * Find records that have <code>created IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>> findManyByCreated(Collection<LocalDateTime> values, int limit) {
                return findManyByCondition(Group.GROUP.CREATED.in(values),limit);
        }

        /**
     * Find records that have <code>creator_uuid IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>> findManyByCreatorUuid(Collection<UUID> values) {
                return findManyByCondition(Group.GROUP.CREATOR_UUID.in(values));
        }

        /**
     * Find records that have <code>creator_uuid IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>> findManyByCreatorUuid(Collection<UUID> values, int limit) {
                return findManyByCondition(Group.GROUP.CREATOR_UUID.in(values),limit);
        }

        /**
     * Find records that have <code>edited IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>> findManyByEdited(Collection<LocalDateTime> values) {
                return findManyByCondition(Group.GROUP.EDITED.in(values));
        }

        /**
     * Find records that have <code>edited IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>> findManyByEdited(Collection<LocalDateTime> values, int limit) {
                return findManyByCondition(Group.GROUP.EDITED.in(values),limit);
        }

        /**
     * Find records that have <code>editor_uuid IN (values)</code> asynchronously
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>> findManyByEditorUuid(Collection<UUID> values) {
                return findManyByCondition(Group.GROUP.EDITOR_UUID.in(values));
        }

        /**
     * Find records that have <code>editor_uuid IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>> findManyByEditorUuid(Collection<UUID> values, int limit) {
                return findManyByCondition(Group.GROUP.EDITOR_UUID.in(values),limit);
        }

        /**
     * Find a unique record that has <code>name = value</code> asynchronously
     */
        public Single<Optional<de.jotschi.vertx.loom.db.jooq.tables.pojos.Group>> findOneByName(String value) {
                return findOneByCondition(Group.GROUP.NAME.eq(value));
        }

        @Override
        public ReactiveRXQueryExecutor<GroupRecord,de.jotschi.vertx.loom.db.jooq.tables.pojos.Group,UUID> queryExecutor(){
                return (ReactiveRXQueryExecutor<GroupRecord,de.jotschi.vertx.loom.db.jooq.tables.pojos.Group,UUID>) super.queryExecutor();
        }
}
