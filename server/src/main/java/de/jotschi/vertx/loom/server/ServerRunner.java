package de.jotschi.vertx.loom.server;

import java.io.IOException;

public class ServerRunner {

  public static void main(String[] args) throws IOException {
    // Please note that the server requires a running postgresql
    // Use the DemoTest class if you want to use a testcontainer instead.
    new DemoServer().start();
    System.out.println("Press enter to stop server");
    System.in.read();
  }

}
