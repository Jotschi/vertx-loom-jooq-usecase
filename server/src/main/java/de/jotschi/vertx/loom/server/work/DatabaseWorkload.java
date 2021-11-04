package de.jotschi.vertx.loom.server.work;

import java.util.concurrent.atomic.AtomicLong;

import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;

import de.jotschi.vertx.loom.db.PocUser;
import de.jotschi.vertx.loom.db.flyway.FlywayHelper;
import de.jotschi.vertx.loom.db.impl.PocUserDaoImpl;
import de.jotschi.vertx.loom.db.jooq.tables.daos.UserDao;
import de.jotschi.vertx.loom.option.DatabaseOptions;
import de.jotschi.vertx.loom.server.DemoServer;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.vertx.core.Future;
import io.vertx.lang.loom.Async;
import io.vertx.loom.pgclient.PgPool;
import io.vertx.loom.sqlclient.Pool;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.PoolOptions;

public class DatabaseWorkload extends AbstractWorkload {

  public DatabaseWorkload(DemoServer demo) {
    super(demo);
    init();
  }

  private PgPool poolClient;
  private io.vertx.rxjava3.sqlclient.Pool pool;
  private PocUserDaoImpl userDao;
  private AtomicLong counter = new AtomicLong();

  private DatabaseWorkload init() {

    DatabaseOptions options = demo().databaseOptions();
    FlywayHelper.migrate(options);
    PgConnectOptions config = new PgConnectOptions()
      .setHost(options.getHost())
      .setPort(options.getPort())
      .setUser(options.getUsername())
      .setPassword(options.getPassword())
      .setDatabase(options.getDatabaseName());

    this.poolClient = PgPool.pool(vertx(), config, new PoolOptions().setMaxSize(32));
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

  public Future<PocUser> createUser() {
    return Async.async(() -> {
      Single<? extends PocUser> user = userDao.createUser("dummy_" + counter.incrementAndGet());
      return user.blockingGet();
    });
  }

  public Observable<String> loadUserIds() {
    return userDao.loadUsers().map(u -> u.getUuid().toString());
  }
}
