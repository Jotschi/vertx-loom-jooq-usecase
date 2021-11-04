package de.jotschi.vertx.loom.server.verticle;

import de.jotschi.vertx.loom.server.DemoServer;
import io.vertx.core.Promise;
import io.vertx.loom.core.AbstractVerticle;
import io.vertx.loom.core.http.HttpServer;
import io.vertx.loom.ext.web.Router;

public class HttpServerVerticle extends AbstractVerticle {

  private DemoServer demo;

  public HttpServerVerticle(DemoServer demo) {
    this.demo = demo;
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    Router router = Router.router(vertx);

    router.route("/demo").handler(rc -> {
      demo.httpServer().handleDemo(rc);
    });

    router.route("/rollDice").handler(rc -> {
      demo.httpServer().handleRollDice(rc);
    });

    // Start the http server
    HttpServer server = vertx.createHttpServer();
    server.requestHandler(router).listen(8080, "localhost", s -> {
      if (s.failed()) {
        System.err.println("Server startup failed");
        startPromise.fail(s.cause());
      } else {
        System.out.println("Server running on port: " + s.result().actualPort());
        startPromise.complete();
      }
    });
  }
}
