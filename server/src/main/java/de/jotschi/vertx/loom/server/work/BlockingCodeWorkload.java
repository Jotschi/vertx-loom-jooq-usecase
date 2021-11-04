package de.jotschi.vertx.loom.server.work;

import static io.vertx.lang.loom.Async.async;

import de.jotschi.vertx.loom.server.DemoServer;
import io.vertx.core.Future;

/**
 * Add some blocking code workload. 
 */
public class BlockingCodeWorkload extends AbstractWorkload {

  public BlockingCodeWorkload(DemoServer demo) {
    super(demo);
  }

  public Future<String> computeStuff() {
    return async(() -> {
      try {
        if (!Thread.currentThread().isVirtual()) {
          throw new RuntimeException("This code should be executed in a virtual thread");
        }
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "42";
    });
  }

}
