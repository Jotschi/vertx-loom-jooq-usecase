package de.jotschi.vertx.loom.server;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;

import de.jotschi.vertx.loom.db.PocUser;
import de.jotschi.vertx.loom.db.impl.PocUserDaoImpl;
import de.jotschi.vertx.loom.db.jooq.tables.daos.UserDao;
import de.jotschi.vertx.loom.option.DatabaseOptions;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.lang.loom.Async;
import io.vertx.loom.core.Vertx;
import io.vertx.loom.ext.web.Router;
import io.vertx.loom.pgclient.PgPool;
import io.vertx.loom.sqlclient.Pool;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.PoolOptions;

public class ServerRunner {

  private PgPool poolClient;
  private io.vertx.rxjava3.sqlclient.Pool pool;
  private Vertx vertx;
  private PocUserDaoImpl userDao;

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

    // FlywayHelper.migrate(options);

    PgConnectOptions config = new PgConnectOptions()
      .setHost(options.getHost())
      .setPort(options.getPort())
      .setUser(options.getUsername())
      .setPassword(options.getPassword())
      .setDatabase(options.getDatabaseName());

    this.poolClient = PgPool.pool(vertx, config, new PoolOptions().setMaxSize(32));
    Pool p = new Pool(poolClient.getDelegate());
    this.pool = new io.vertx.rxjava3.sqlclient.Pool(p.getDelegate());

    UserDao jooqUserDao = new UserDao(jooqConfiguration(), pool);
    this.userDao = new PocUserDaoImpl(jooqUserDao);
    return this;
  }

  private Configuration jooqConfiguration() {
    Configuration configuration = new DefaultConfiguration();
    return configuration.set(SQLDialect.POSTGRES);
  }

  private void start() {
    Router router = Router.router(vertx);
    router.route("/users").handler(rc -> {
      List<String> userUuids = loadUserIds().toList().blockingGet();
      JsonObject response = new JsonObject();
      response.put("userUuids", new JsonArray(userUuids));
      rc.end(response.encodePrettily());
    });

    // TODO Create a clean route with POST - for now GET will do
    router.route("/addUser").handler(rc -> {
      PocUser user = Async.await(createUser());
      JsonObject response = new JsonObject();
      response.put("username", user.getUsername());
      response.put("uuid", user.getUuid().toString());
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

  private AtomicLong counter = new AtomicLong();

  private Future<PocUser> createUser() {
    return Async.async(() -> {
      Single<? extends PocUser> user = userDao.createUser("dummy_" + counter.incrementAndGet());
      return user.blockingGet();
    });
  }

  private Observable<String> loadUserIds() {
    return userDao.loadUsers().map(u -> u.getUuid().toString());
  }

}
