package de.jotschi.vertx.loom.server.work;

import de.jotschi.vertx.loom.server.DemoServer;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.loom.core.Promise;
import io.vertx.loom.core.http.HttpClient;

/**
 * Some HTTP client sepcific workloads.
 */
public class HttpClientWorkload extends AbstractWorkload {

  private HttpClient client;

  public HttpClientWorkload(DemoServer demo) {
    super(demo);
    this.client = vertx().createHttpClient();
  }

  public Future<Integer> rollDice() {
    Promise<Integer> promise = Promise.promise();
    System.out.println("[HTTP Client] -> [HTTP Server] Rolling dice");
    client.request(HttpMethod.GET, 8080, "localhost", "/rollDice", req -> {
      if (req.failed()) {
        promise.fail(req.cause());
        return;
      } else {
        req.result().send(re -> {
          re.result().bodyHandler(bh -> {
            int roll = Integer.valueOf(bh.toString());
            System.out.println("[Http Client] Client Resp: " + roll);
            promise.complete(roll);
          });
        });
      }
    });
    return promise.future();
  }

  public Future<JsonObject> invokeDemo() {
    Promise<JsonObject> promise = Promise.promise();
    client.request(HttpMethod.GET, 8080, "localhost", "/demo", req -> {
      req.result().send(resp -> {
        resp.result().bodyHandler(bh -> {
          promise.complete((JsonObject) bh.toJson());
        });
      });
    });
    return promise.future();
  }

}
