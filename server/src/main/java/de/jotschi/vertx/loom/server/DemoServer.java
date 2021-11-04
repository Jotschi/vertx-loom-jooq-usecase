package de.jotschi.vertx.loom.server;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import de.jotschi.vertx.loom.option.DatabaseOptions;
import de.jotschi.vertx.loom.server.verticle.EventVerticle;
import de.jotschi.vertx.loom.server.verticle.HttpServerVerticle;
import de.jotschi.vertx.loom.server.work.BlockingCodeWorkload;
import de.jotschi.vertx.loom.server.work.DatabaseWorkload;
import de.jotschi.vertx.loom.server.work.DiskIOWorkload;
import de.jotschi.vertx.loom.server.work.HttpClientWorkload;
import de.jotschi.vertx.loom.server.work.HttpServerWorkload;
import de.jotschi.vertx.loom.server.work.eventbus.EventbusWorkload;
import de.jotschi.vertx.loom.server.work.eventbus.GameResult;
import de.jotschi.vertx.loom.server.work.eventbus.GameResultCodec;
import io.vertx.loom.core.Vertx;

public class DemoServer {

  private Vertx vertx;
  private DatabaseOptions databaseOptions;
  private DatabaseWorkload db;
  private DiskIOWorkload disk;
  private EventbusWorkload eventbus;
  private BlockingCodeWorkload blockingCode;
  private HttpClientWorkload httpClient;
  private HttpServerWorkload httpServer;

  public DemoServer(DatabaseOptions options) {
    this.vertx = Vertx.vertx();
    vertx.eventBus().getDelegate().registerDefaultCodec(GameResult.class, new GameResultCodec());
    // Use defaults as a fallback
    if (options == null) {
      options = new DatabaseOptions();
      options.setPort(5432);
      options.setHost("127.0.0.1");
      options.setUsername("postgres");
      options.setPassword("finger");
      options.setDatabaseName("postgres");
    }
    this.databaseOptions = options;

    // Setup various workloads which will demo operations and code flow
    this.db = new DatabaseWorkload(this);
    this.disk = new DiskIOWorkload(this);
    this.eventbus = new EventbusWorkload(this);
    this.blockingCode = new BlockingCodeWorkload(this);
    this.httpClient = new HttpClientWorkload(this);
    this.httpServer = new HttpServerWorkload(this);

  }

  public DemoServer() {
    this(null);
  }

  public DemoServer start() {
    CountDownLatch l = new CountDownLatch(2);
    vertx.deployVerticle(new HttpServerVerticle(this), r -> {
      l.countDown();
    });
    vertx.deployVerticle(new EventVerticle(this), r -> {
      l.countDown();
    });
    try {
      l.await(10, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return this;
  }

  public Vertx vertx() {
    return vertx;
  }

  public DatabaseWorkload db() {
    return db;
  }

  public EventbusWorkload eventbus() {
    return eventbus;
  }

  public BlockingCodeWorkload blockingCode() {
    return blockingCode;
  }

  public DiskIOWorkload disk() {
    return disk;
  }

  public HttpClientWorkload httpClient() {
    return httpClient;
  }

  public HttpServerWorkload httpServer() {
    return httpServer;
  }

  public DatabaseOptions databaseOptions() {
    return databaseOptions;
  }

}
