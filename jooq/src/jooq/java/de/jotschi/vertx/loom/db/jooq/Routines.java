/*
 * This file is generated by jOOQ.
 */
package de.jotschi.vertx.loom.db.jooq;


import de.jotschi.vertx.loom.db.jooq.routines.UuidGenerateV1;
import de.jotschi.vertx.loom.db.jooq.routines.UuidGenerateV1mc;
import de.jotschi.vertx.loom.db.jooq.routines.UuidGenerateV3;
import de.jotschi.vertx.loom.db.jooq.routines.UuidGenerateV4;
import de.jotschi.vertx.loom.db.jooq.routines.UuidGenerateV5;
import de.jotschi.vertx.loom.db.jooq.routines.UuidNil;
import de.jotschi.vertx.loom.db.jooq.routines.UuidNsDns;
import de.jotschi.vertx.loom.db.jooq.routines.UuidNsOid;
import de.jotschi.vertx.loom.db.jooq.routines.UuidNsUrl;
import de.jotschi.vertx.loom.db.jooq.routines.UuidNsX500;

import java.util.UUID;

import org.jooq.Configuration;
import org.jooq.Field;


/**
 * Convenience access to all stored procedures and functions in public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Routines {

    /**
     * Call <code>public.uuid_generate_v1</code>
     */
    public static UUID uuidGenerateV1(
          Configuration configuration
    ) {
        UuidGenerateV1 f = new UuidGenerateV1();

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.uuid_generate_v1</code> as a field.
     */
    public static Field<UUID> uuidGenerateV1() {
        UuidGenerateV1 f = new UuidGenerateV1();

        return f.asField();
    }

    /**
     * Call <code>public.uuid_generate_v1mc</code>
     */
    public static UUID uuidGenerateV1mc(
          Configuration configuration
    ) {
        UuidGenerateV1mc f = new UuidGenerateV1mc();

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.uuid_generate_v1mc</code> as a field.
     */
    public static Field<UUID> uuidGenerateV1mc() {
        UuidGenerateV1mc f = new UuidGenerateV1mc();

        return f.asField();
    }

    /**
     * Call <code>public.uuid_generate_v3</code>
     */
    public static UUID uuidGenerateV3(
          Configuration configuration
        , UUID namespace
        , String name
    ) {
        UuidGenerateV3 f = new UuidGenerateV3();
        f.setNamespace(namespace);
        f.setName_(name);

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.uuid_generate_v3</code> as a field.
     */
    public static Field<UUID> uuidGenerateV3(
          UUID namespace
        , String name
    ) {
        UuidGenerateV3 f = new UuidGenerateV3();
        f.setNamespace(namespace);
        f.setName_(name);

        return f.asField();
    }

    /**
     * Get <code>public.uuid_generate_v3</code> as a field.
     */
    public static Field<UUID> uuidGenerateV3(
          Field<UUID> namespace
        , Field<String> name
    ) {
        UuidGenerateV3 f = new UuidGenerateV3();
        f.setNamespace(namespace);
        f.setName_(name);

        return f.asField();
    }

    /**
     * Call <code>public.uuid_generate_v4</code>
     */
    public static UUID uuidGenerateV4(
          Configuration configuration
    ) {
        UuidGenerateV4 f = new UuidGenerateV4();

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.uuid_generate_v4</code> as a field.
     */
    public static Field<UUID> uuidGenerateV4() {
        UuidGenerateV4 f = new UuidGenerateV4();

        return f.asField();
    }

    /**
     * Call <code>public.uuid_generate_v5</code>
     */
    public static UUID uuidGenerateV5(
          Configuration configuration
        , UUID namespace
        , String name
    ) {
        UuidGenerateV5 f = new UuidGenerateV5();
        f.setNamespace(namespace);
        f.setName_(name);

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.uuid_generate_v5</code> as a field.
     */
    public static Field<UUID> uuidGenerateV5(
          UUID namespace
        , String name
    ) {
        UuidGenerateV5 f = new UuidGenerateV5();
        f.setNamespace(namespace);
        f.setName_(name);

        return f.asField();
    }

    /**
     * Get <code>public.uuid_generate_v5</code> as a field.
     */
    public static Field<UUID> uuidGenerateV5(
          Field<UUID> namespace
        , Field<String> name
    ) {
        UuidGenerateV5 f = new UuidGenerateV5();
        f.setNamespace(namespace);
        f.setName_(name);

        return f.asField();
    }

    /**
     * Call <code>public.uuid_nil</code>
     */
    public static UUID uuidNil(
          Configuration configuration
    ) {
        UuidNil f = new UuidNil();

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.uuid_nil</code> as a field.
     */
    public static Field<UUID> uuidNil() {
        UuidNil f = new UuidNil();

        return f.asField();
    }

    /**
     * Call <code>public.uuid_ns_dns</code>
     */
    public static UUID uuidNsDns(
          Configuration configuration
    ) {
        UuidNsDns f = new UuidNsDns();

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.uuid_ns_dns</code> as a field.
     */
    public static Field<UUID> uuidNsDns() {
        UuidNsDns f = new UuidNsDns();

        return f.asField();
    }

    /**
     * Call <code>public.uuid_ns_oid</code>
     */
    public static UUID uuidNsOid(
          Configuration configuration
    ) {
        UuidNsOid f = new UuidNsOid();

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.uuid_ns_oid</code> as a field.
     */
    public static Field<UUID> uuidNsOid() {
        UuidNsOid f = new UuidNsOid();

        return f.asField();
    }

    /**
     * Call <code>public.uuid_ns_url</code>
     */
    public static UUID uuidNsUrl(
          Configuration configuration
    ) {
        UuidNsUrl f = new UuidNsUrl();

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.uuid_ns_url</code> as a field.
     */
    public static Field<UUID> uuidNsUrl() {
        UuidNsUrl f = new UuidNsUrl();

        return f.asField();
    }

    /**
     * Call <code>public.uuid_ns_x500</code>
     */
    public static UUID uuidNsX500(
          Configuration configuration
    ) {
        UuidNsX500 f = new UuidNsX500();

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.uuid_ns_x500</code> as a field.
     */
    public static Field<UUID> uuidNsX500() {
        UuidNsX500 f = new UuidNsX500();

        return f.asField();
    }
}
