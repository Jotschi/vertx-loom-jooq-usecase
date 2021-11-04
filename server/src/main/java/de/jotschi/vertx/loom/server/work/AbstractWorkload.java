package de.jotschi.vertx.loom.server.work;

import de.jotschi.vertx.loom.server.DemoServer;
import io.vertx.loom.core.Vertx;

public abstract class AbstractWorkload {

  private DemoServer demo;

  public AbstractWorkload(DemoServer demo) {
    this.demo = demo;
  }

  public Vertx vertx() {
    return demo.vertx();
  }

  public DemoServer demo() {
    return demo;
  }

}
