/*
 * This file is generated by jOOQ.
 */
package de.jotschi.vertx.loom.db.jooq.tables;


import de.jotschi.vertx.loom.db.jooq.Keys;
import de.jotschi.vertx.loom.db.jooq.Public;
import de.jotschi.vertx.loom.db.jooq.tables.records.UserGroupRecord;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserGroup extends TableImpl<UserGroupRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.user_group</code>
     */
    public static final UserGroup USER_GROUP = new UserGroup();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserGroupRecord> getRecordType() {
        return UserGroupRecord.class;
    }

    /**
     * The column <code>public.user_group.user_uuid</code>.
     */
    public final TableField<UserGroupRecord, UUID> USER_UUID = createField(DSL.name("user_uuid"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.user_group.group_uuid</code>.
     */
    public final TableField<UserGroupRecord, UUID> GROUP_UUID = createField(DSL.name("group_uuid"), SQLDataType.UUID.nullable(false), this, "");

    private UserGroup(Name alias, Table<UserGroupRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserGroup(Name alias, Table<UserGroupRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.user_group</code> table reference
     */
    public UserGroup(String alias) {
        this(DSL.name(alias), USER_GROUP);
    }

    /**
     * Create an aliased <code>public.user_group</code> table reference
     */
    public UserGroup(Name alias) {
        this(alias, USER_GROUP);
    }

    /**
     * Create a <code>public.user_group</code> table reference
     */
    public UserGroup() {
        this(DSL.name("user_group"), null);
    }

    public <O extends Record> UserGroup(Table<O> child, ForeignKey<O, UserGroupRecord> key) {
        super(child, key, USER_GROUP);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<UserGroupRecord> getPrimaryKey() {
        return Keys.USER_GROUP_PKEY;
    }

    @Override
    public List<UniqueKey<UserGroupRecord>> getKeys() {
        return Arrays.<UniqueKey<UserGroupRecord>>asList(Keys.USER_GROUP_PKEY);
    }

    @Override
    public List<ForeignKey<UserGroupRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<UserGroupRecord, ?>>asList(Keys.USER_GROUP__USER_GROUP_USER_UUID_FKEY, Keys.USER_GROUP__USER_GROUP_GROUP_UUID_FKEY);
    }

    private transient User _user;
    private transient Group _group;

    public User user() {
        if (_user == null)
            _user = new User(this, Keys.USER_GROUP__USER_GROUP_USER_UUID_FKEY);

        return _user;
    }

    public Group group() {
        if (_group == null)
            _group = new Group(this, Keys.USER_GROUP__USER_GROUP_GROUP_UUID_FKEY);

        return _group;
    }

    @Override
    public UserGroup as(String alias) {
        return new UserGroup(DSL.name(alias), this);
    }

    @Override
    public UserGroup as(Name alias) {
        return new UserGroup(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserGroup rename(String name) {
        return new UserGroup(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserGroup rename(Name name) {
        return new UserGroup(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<UUID, UUID> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}