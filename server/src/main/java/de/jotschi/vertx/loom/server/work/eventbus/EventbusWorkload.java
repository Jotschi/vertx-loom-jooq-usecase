package de.jotschi.vertx.loom.server.work.eventbus;

import static io.vertx.lang.loom.Async.await;

import de.jotschi.vertx.loom.server.DemoServer;
import de.jotschi.vertx.loom.server.work.AbstractWorkload;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.loom.core.Promise;
import io.vertx.loom.core.eventbus.Message;

public class EventbusWorkload extends AbstractWorkload {

  public static final String EVENT_ADDR = "dummy";

  public EventbusWorkload(DemoServer demo) {
    super(demo);
  }

  public Future<GameResult> playGame() {
    Promise<GameResult> promise = Promise.promise();
    System.out.println("[Eventbus] -> [Http Client] Sending roll dice request");
    int roll = await(demo().httpClient().rollDice());
    System.out.println("[Eventbus] -> [Eventbus] Sending game event");
    vertx().eventBus().request(EVENT_ADDR, roll, (AsyncResult<Message<GameResult>> rh) -> {
      if (rh.failed()) {
        promise.fail(rh.cause());
      } else {
        promise.complete(rh.result().body());
      }
    });
    return promise.future();
  }

  public void subscribe() {
    // Accept the event and decide whether the requestor has won
    vertx().eventBus().localConsumer(EVENT_ADDR, (Message<Integer> msg) -> {
      Integer rolledNumber = msg.body();
      System.out.println("[Eventbus] <- [Eventbus] Sending Reply: " + rolledNumber);
      if (rolledNumber > 42) {
        msg.reply(GameResult.WON);
      } else {
        msg.reply(GameResult.LOST);
      }
    });
  }
}
