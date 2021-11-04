package de.jotschi.vertx.loom.server.work;

import static io.vertx.lang.loom.Async.await;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.jotschi.vertx.loom.db.PocUser;
import de.jotschi.vertx.loom.server.DemoServer;
import de.jotschi.vertx.loom.server.work.eventbus.GameResult;
import io.reactivex.rxjava3.core.Observable;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.loom.core.buffer.Buffer;
import io.vertx.loom.ext.web.RoutingContext;

/**
 * Workload which contains the handler code for REST methods.
 */
public class HttpServerWorkload extends AbstractWorkload {

  public HttpServerWorkload(DemoServer demo) {
    super(demo);
  }

  public void handleRollDice(RoutingContext rc) {
    int roll = (int) (Math.random() * 100);
    System.out.println("[Http Server] -> [Http Client] Rolled: " + roll);
    rc.end(String.valueOf(roll));
  }

  public void handleDemo(RoutingContext rc) {
    long start = System.currentTimeMillis();

    // Lets call some async apis and use await to wait for the operation.
    Buffer buffer = await(demo().disk().readFile());
    PocUser user = await(demo().db().createUser());
    GameResult result = await(demo().eventbus().playGame());
    String computeResult = await(demo().blockingCode().computeStuff());

    System.out.println("[Database] User created: " + user.getUuid());
    System.out.println("[Disk IO] Read: " + buffer.toString().length() + " characters from pom.xml");
    System.out.println("[Eventbus] Game Result: " + result);
    System.out.println("[Blocking Code] Result: " + computeResult);

    // Async operations can also be invoked first and waited for in-parallel
    Set<Future<String>> ops = new HashSet<>();
    for (int i = 0; i < 20; i++) {
      ops.add(demo().blockingCode().computeStuff());
    }
    for (Future<String> op : ops) {
      await(op);
    }

    Observable<String> usersObs = demo().db().loadUserIds();
    List<String> userUuids = await(usersObs);

    long dur = System.currentTimeMillis() - start;
    System.out.println("\nAll operations took: " + dur + " [ms]");

    JsonObject response = new JsonObject();
    response.put("username", user.getUsername());
    response.put("uuid", user.getUuid().toString());
    response.put("userUuids", new JsonArray(userUuids));
    rc.end(response.encodePrettily());
  }

}
