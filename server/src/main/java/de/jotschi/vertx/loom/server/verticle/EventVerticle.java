package de.jotschi.vertx.loom.server.verticle;

import de.jotschi.vertx.loom.server.DemoServer;
import io.vertx.core.Promise;
import io.vertx.loom.core.AbstractVerticle;

public class EventVerticle extends AbstractVerticle {

  private DemoServer demo;

  public EventVerticle(DemoServer demo) {
    this.demo = demo;
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    demo.eventbus().subscribe();
    startPromise.complete();
  }
}
