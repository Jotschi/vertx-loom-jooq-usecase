package de.jotschi.vertx.loom.server.work.eventbus;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class GameResultCodec implements MessageCodec<GameResult, GameResult> {

  @Override
  public void encodeToWire(Buffer buffer, GameResult s) {
    buffer.appendInt(s.ordinal());
  }

  @Override
  public GameResult decodeFromWire(int pos, Buffer buffer) {
    int ord = buffer.getInt(pos);
    return GameResult.values()[ord];
  }

  @Override
  public GameResult transform(GameResult s) {
    // Game result is immutable
    return s;
  }

  @Override
  public String name() {
    return "game-result";
  }

  @Override
  public byte systemCodecID() {
    return -1;
  }

}
