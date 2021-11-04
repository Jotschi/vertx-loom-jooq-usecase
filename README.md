# Vert.x Loom jOOQ Usecase

This project contains a usecase study for the vertx-loom wrapper API.

## Demo

The demo provides a Vert.x server which exposes the `/demo` endpoint. Calling this endpoint will:

* Disk IO: Read the pom.xml
* Jooq: Create a user in the database
* Eventbus: Dispatch a eventbus event to tigger a game. The eventbus handler will query another server endpoint via a http client call for a number. A reply handler with the result will be invoked.
* Blocking Code: A blocking code section will be invoked via Async/Await
* JOOQ: A list of user Ids will be read from the server

Example:
```java
Buffer buffer = await(demo().disk().readFile());
PocUser user = await(demo().db().createUser());
GameResult result = await(demo().eventbus().playGame());
String computeResult = await(demo().blockingCode().computeStuff());
```

The easiest way to run the demo is to invoke the [DemoTest unit test](blob/master/server/src/test/java/de/jotschi/vertx/loom/DemoTest.java#L34:L44).

## Important Notice

This project contains experimental code which is based upon not yet released changes.

* [Loom Wrapper API for Vert.x](https://github.com/Jotschi/vertx-loom)
* [RxJava3 Support for vertx-jooq](https://github.com/jklingsporn/vertx-jooq/pull/191)
* [Codegen changes needed for wrapper generation](https://github.com/vert-x3/vertx-rx/pull/271)

Additionally this project requires JDK 18 (Project Loom) for execution and building.

## Versions

* Vert.x version: 4.2.1-SNAPSHOT
* Dagger version: 2.35.1
* Vert.x jOOQ version: 6.2.0

Project modules:

* api - HighLevel API for DAO / Domain model
* bom - BOM POM for dependency management
* flyway - Database schema migration code
* jooq - jOOQ code generation and tests
* server - Vert.x Server code

