package de.jotschi.vertx.loom.server;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;

import de.jotschi.vertx.loom.db.PocUserDao;
import de.jotschi.vertx.loom.db.flyway.FlywayHelper;
import de.jotschi.vertx.loom.db.impl.PocUserDaoImpl;
import de.jotschi.vertx.loom.db.jooq.tables.daos.UserDao;
import de.jotschi.vertx.loom.option.DatabaseOptions;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.lang.loom.Async;
import io.vertx.loom.core.Vertx;
import io.vertx.loom.ext.web.Router;
import io.vertx.loom.pgclient.PgPool;
import io.vertx.loom.sqlclient.Pool;
import io.vertx.loom.sqlclient.SqlClient;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.PoolOptions;

public class ServerRunner {

  private PgPool poolClient;
  private SqlClient sqlClient;
  private Vertx vertx;

  public static void main(String[] args) throws IOException {
    new ServerRunner().init().start();
    System.out.println("Press enter to stop server");
    System.in.read();
  }

  private ServerRunner init() {

    DatabaseOptions options = new DatabaseOptions();
    options.setPort(5432);
    options.setHost("127.0.0.1");
    options.setUsername("postgres");
    options.setPassword("finger");
    options.setDatabaseName("postgres");

    this.vertx = Vertx.vertx();

    FlywayHelper.migrate(options);

    PgConnectOptions config = new PgConnectOptions()
      .setHost(options.getHost())
      .setPort(options.getPort())
      .setUser(options.getUsername())
      .setPassword(options.getPassword())
      .setDatabase(options.getDatabaseName());

    this.poolClient = PgPool.pool(vertx, config, new PoolOptions().setMaxSize(32));
    this.sqlClient = new Pool(poolClient);

    return this;
  }

  private Configuration jooqConfiguration() {
    Configuration configuration = new DefaultConfiguration();
    return configuration.set(SQLDialect.POSTGRES);
  }

  public PocUserDao userDao() {
    UserDao userDao = new UserDao(jooqConfiguration(), sqlClient);
    return new PocUserDaoImpl(userDao);
  }

  private void start() {
    Router router = Router.router(vertx);
    router.route("/test").handler(rc -> {
      List<Long> userIds = Async.await(loadUserIds());
      JsonObject response = new JsonObject();
      response.put("userIds", new JsonArray(userIds));
      rc.end(response.encodePrettily());
    });

    vertx.createHttpServer().requestHandler(router).listen(8080, "localhost", s -> {
      if (s.failed()) {
        System.err.println("Server startup failed");
        s.cause().printStackTrace();
      } else {
        System.out.println("Server running on port: " + s.result().actualPort());
      }
    });

  }

  private Future<List<Long>> loadUserIds() {
    return Async.async(() -> {
      System.out.println("Loading IDS");
      return Arrays.asList(1L, 2L, 3L);
    });
  }

}
