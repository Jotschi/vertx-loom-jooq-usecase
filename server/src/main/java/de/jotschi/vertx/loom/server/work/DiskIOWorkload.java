package de.jotschi.vertx.loom.server.work;

import de.jotschi.vertx.loom.server.DemoServer;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.loom.core.buffer.Buffer;

public class DiskIOWorkload extends AbstractWorkload {

  public DiskIOWorkload(DemoServer demo) {
    super(demo);
  }

  public Future<Buffer> readFile() {
    System.out.println("[Disk IO] Reading file");
    Promise<Buffer> promise = Promise.promise();
    vertx().fileSystem().readFile("pom.xml", promise);
    return promise.future();
  }

}
