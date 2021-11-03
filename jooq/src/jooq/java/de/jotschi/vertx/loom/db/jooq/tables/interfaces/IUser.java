/*
 * This file is generated by jOOQ.
 */
package de.jotschi.vertx.loom.db.jooq.tables.interfaces;


import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


import static io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo.*;
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface IUser extends VertxPojo, Serializable {

    /**
     * Setter for <code>public.user.uuid</code>.
     */
    public IUser setUuid(UUID value);

    /**
     * Getter for <code>public.user.uuid</code>.
     */
    public UUID getUuid();

    /**
     * Setter for <code>public.user.username</code>.
     */
    public IUser setUsername(String value);

    /**
     * Getter for <code>public.user.username</code>.
     */
    public String getUsername();

    /**
     * Setter for <code>public.user.firstname</code>.
     */
    public IUser setFirstname(String value);

    /**
     * Getter for <code>public.user.firstname</code>.
     */
    public String getFirstname();

    /**
     * Setter for <code>public.user.lastname</code>.
     */
    public IUser setLastname(String value);

    /**
     * Getter for <code>public.user.lastname</code>.
     */
    public String getLastname();

    /**
     * Setter for <code>public.user.passwordhash</code>.
     */
    public IUser setPasswordhash(String value);

    /**
     * Getter for <code>public.user.passwordhash</code>.
     */
    public String getPasswordhash();

    /**
     * Setter for <code>public.user.email</code>.
     */
    public IUser setEmail(String value);

    /**
     * Getter for <code>public.user.email</code>.
     */
    public String getEmail();

    /**
     * Setter for <code>public.user.enabled</code>.
     */
    public IUser setEnabled(Boolean value);

    /**
     * Getter for <code>public.user.enabled</code>.
     */
    public Boolean getEnabled();

    /**
     * Setter for <code>public.user.meta</code>.
     */
    public IUser setMeta(String value);

    /**
     * Getter for <code>public.user.meta</code>.
     */
    public String getMeta();

    /**
     * Setter for <code>public.user.created</code>.
     */
    public IUser setCreated(LocalDateTime value);

    /**
     * Getter for <code>public.user.created</code>.
     */
    public LocalDateTime getCreated();

    /**
     * Setter for <code>public.user.creator_uuid</code>.
     */
    public IUser setCreatorUuid(UUID value);

    /**
     * Getter for <code>public.user.creator_uuid</code>.
     */
    public UUID getCreatorUuid();

    /**
     * Setter for <code>public.user.edited</code>.
     */
    public IUser setEdited(LocalDateTime value);

    /**
     * Getter for <code>public.user.edited</code>.
     */
    public LocalDateTime getEdited();

    /**
     * Setter for <code>public.user.editor_uuid</code>.
     */
    public IUser setEditorUuid(UUID value);

    /**
     * Getter for <code>public.user.editor_uuid</code>.
     */
    public UUID getEditorUuid();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IUser
     */
    public void from(IUser from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IUser
     */
    public <E extends IUser> E into(E into);

        @Override
        public default IUser fromJson(io.vertx.core.json.JsonObject json) {
                // Omitting unrecognized type java.util.UUID for column uuid!
                setOrThrow(this::setUsername,json::getString,"username","java.lang.String");
                setOrThrow(this::setFirstname,json::getString,"firstname","java.lang.String");
                setOrThrow(this::setLastname,json::getString,"lastname","java.lang.String");
                setOrThrow(this::setPasswordhash,json::getString,"passwordhash","java.lang.String");
                setOrThrow(this::setEmail,json::getString,"email","java.lang.String");
                setOrThrow(this::setEnabled,json::getBoolean,"enabled","java.lang.Boolean");
                setOrThrow(this::setMeta,json::getString,"meta","java.lang.String");
                setOrThrow(this::setCreated,key -> {String s = json.getString(key); return s==null?null:java.time.LocalDateTime.parse(s);},"created","java.time.LocalDateTime");
                // Omitting unrecognized type java.util.UUID for column creator_uuid!
                setOrThrow(this::setEdited,key -> {String s = json.getString(key); return s==null?null:java.time.LocalDateTime.parse(s);},"edited","java.time.LocalDateTime");
                // Omitting unrecognized type java.util.UUID for column editor_uuid!
                return this;
        }


        @Override
        public default io.vertx.core.json.JsonObject toJson() {
                io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
                // Omitting unrecognized type java.util.UUID for column uuid!
                json.put("username",getUsername());
                json.put("firstname",getFirstname());
                json.put("lastname",getLastname());
                json.put("passwordhash",getPasswordhash());
                json.put("email",getEmail());
                json.put("enabled",getEnabled());
                json.put("meta",getMeta());
                json.put("created",getCreated()==null?null:getCreated().toString());
                // Omitting unrecognized type java.util.UUID for column creator_uuid!
                json.put("edited",getEdited()==null?null:getEdited().toString());
                // Omitting unrecognized type java.util.UUID for column editor_uuid!
                return json;
        }

}