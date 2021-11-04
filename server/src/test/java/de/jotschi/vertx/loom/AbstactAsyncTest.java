package de.jotschi.vertx.loom;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.junit.After;
import org.junit.Before;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * Normally I would use io.vertx.test.core.AsyncTestBase but this leads to a nameclash with used await methods.
 */
public abstract class AbstactAsyncTest {

  FixedAsyncTestBase base = new FixedAsyncTestBase();

  @Before
  public void before() throws Exception {
    base.before();
  }

  @After
  public void after() throws Exception {
    base.after();
  }

  public void waitFor() {
    base.await(20, TimeUnit.SECONDS);
  }

  public <T> Handler<AsyncResult<T>> onSuccess(Consumer<T> consumer) {
    return base.onSuccess(consumer);
  }

  public void testComplete() {
    base.testComplete();
  }

}
