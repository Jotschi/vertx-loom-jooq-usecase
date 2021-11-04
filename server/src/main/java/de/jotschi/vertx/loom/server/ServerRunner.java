package de.jotschi.vertx.loom.server;

import java.io.IOException;

public class ServerRunner {

  public static void main(String[] args) throws IOException {
    new DemoServer().start();
    System.out.println("Press enter to stop server");
    System.in.read();
  }

}
