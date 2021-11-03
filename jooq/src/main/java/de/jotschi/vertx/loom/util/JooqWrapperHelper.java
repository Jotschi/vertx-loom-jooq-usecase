package de.jotschi.vertx.loom.util;

import java.lang.reflect.Constructor;
import java.util.Optional;

import de.jotschi.vertx.loom.db.PocElement;
import de.jotschi.vertx.loom.db.impl.PocWrapper;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public final class JooqWrapperHelper {

  private JooqWrapperHelper() {
  }

  public static <T, R> Maybe<T> wrap(Single<Optional<R>> jooq, Class<T> clazz) {
    Maybe<T> r = jooq.flatMapMaybe(e -> {
      if (e.isEmpty()) {
        return Maybe.empty();
      } else {
        R element = e.get();
        Constructor<T> constructor = clazz.getConstructor(element.getClass());
        T wrapper = constructor.newInstance(element);
        return Maybe.just(wrapper);
      }
    });
    return r;
  }

  public static <T, R> T wrap(R jooq, Class<T> clazz) {
    try {
      Constructor<T> constructor = clazz.getConstructor(jooq.getClass());
      T wrapper = constructor.newInstance(jooq);
      return wrapper;
    } catch (Exception e) {
      throw new RuntimeException("Unable to wrap element {" + jooq.getClass().getName() + "}", e);
    }
  }

  public static <T> T unwrap(PocElement element) {
    if (PocWrapper.class.isInstance(element)) {
      @SuppressWarnings("unchecked")
      PocWrapper<T> wrapper = PocWrapper.class.cast(element);
      return wrapper.getDelegate();
    } else {
      throw new RuntimeException("Could not cast element {" + element.getClass() + "} to {" + PocWrapper.class.getSimpleName() + "}");
    }
  }
}
