# Vert.x Loom jOOQ Usecase

This project contains a usecase study for the vertx-loom wrapper API.

## Demo

The demo provides a Vert.x server which exposes the `/demo` endpoint. Calling this endpoint will:

* Disk IO: Read the pom.xml
* Jooq: Create a user in the database
* Eventbus: Dispatch a eventbus event to tigger a game. The eventbus handler will query another server endpoint via a http client call for a number. A reply handler with the result will be invoked.
* Blocking Code: A blocking code section will be invoked via Async/Await
* Jooq: A list of user Ids will be read from the server

Excerpt:
```java
Buffer buffer = await(demo().disk().readFile());
PocUser user = await(demo().db().createUser());
GameResult result = await(demo().eventbus().playGame());
String computeResult = await(demo().blockingCode().computeStuff());
```

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

