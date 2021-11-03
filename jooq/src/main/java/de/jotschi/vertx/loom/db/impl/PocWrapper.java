package de.jotschi.vertx.loom.db.impl;

public interface PocWrapper<T> {

	/**
	 * Return the wrapped jooq POJO
	 * 
	 * @return
	 */
	T getDelegate();

}
