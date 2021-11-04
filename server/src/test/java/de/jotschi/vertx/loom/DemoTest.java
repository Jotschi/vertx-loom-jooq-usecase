package de.jotschi.vertx.loom;

import static io.vertx.lang.loom.Async.await;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import de.jotschi.vertx.loom.option.DatabaseOptions;
import de.jotschi.vertx.loom.server.DemoServer;
import de.jotschi.vertx.loom.server.work.eventbus.GameResult;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.loom.ext.web.RoutingContext;

public class DemoTest extends AbstactAsyncTest {

  @Rule
  public PocPostgreSQLContainer container = new PocPostgreSQLContainer();

  @Test
  public void testDemoProcess() {
    DatabaseOptions options = container.getOptions();
    DemoServer demo = new DemoServer(options).start();
    demo.vertx().runOnContext(v -> {
      RoutingContext rc = Mockito.mock(RoutingContext.class);
      demo.httpServer().handleDemo(rc);
      testComplete();
    });
    waitFor();
  }

  @Test
  public void testDemoEndpoint() {
    DatabaseOptions options = container.getOptions();
    DemoServer demo = new DemoServer(options).start();
    Future<JsonObject> resp = demo.httpClient().invokeDemo();
    demo.vertx().runOnContext(c -> {
      JsonObject json = await(resp);
      System.out.println("[Test] Response:\n" + json.encodePrettily());
      testComplete();
    });
    waitFor();
  }

  @Test
  public void testEventbus() {
    DemoServer demo = new DemoServer().start();
    demo.vertx().runOnContext(v -> {
      demo.eventbus().subscribe();
      GameResult result = await(demo.eventbus().playGame());
      System.out.println("[Test] Result: " + result.name());
      testComplete();
    });
    waitFor();
  }
}
