/*
 * This file is generated by jOOQ.
 */
package de.jotschi.vertx.loom.db.jooq.tables.pojos;


import de.jotschi.vertx.loom.db.jooq.tables.interfaces.IUserGroup;

import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;

import java.util.UUID;


import static io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo.*;
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserGroup implements VertxPojo, IUserGroup {

    private static final long serialVersionUID = 1L;

    private UUID userUuid;
    private UUID groupUuid;

    public UserGroup() {}

    public UserGroup(IUserGroup value) {
        this.userUuid = value.getUserUuid();
        this.groupUuid = value.getGroupUuid();
    }

    public UserGroup(
        UUID userUuid,
        UUID groupUuid
    ) {
        this.userUuid = userUuid;
        this.groupUuid = groupUuid;
    }

        public UserGroup(io.vertx.core.json.JsonObject json) {
                this();
                fromJson(json);
        }

    /**
     * Getter for <code>public.user_group.user_uuid</code>.
     */
    @Override
    public UUID getUserUuid() {
        return this.userUuid;
    }

    /**
     * Setter for <code>public.user_group.user_uuid</code>.
     */
    @Override
    public UserGroup setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
        return this;
    }

    /**
     * Getter for <code>public.user_group.group_uuid</code>.
     */
    @Override
    public UUID getGroupUuid() {
        return this.groupUuid;
    }

    /**
     * Setter for <code>public.user_group.group_uuid</code>.
     */
    @Override
    public UserGroup setGroupUuid(UUID groupUuid) {
        this.groupUuid = groupUuid;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UserGroup (");

        sb.append(userUuid);
        sb.append(", ").append(groupUuid);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IUserGroup from) {
        setUserUuid(from.getUserUuid());
        setGroupUuid(from.getGroupUuid());
    }

    @Override
    public <E extends IUserGroup> E into(E into) {
        into.from(this);
        return into;
    }
}
