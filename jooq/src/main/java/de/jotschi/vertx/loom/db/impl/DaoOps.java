package de.jotschi.vertx.loom.db.impl;

import static de.jotschi.vertx.loom.db.jooq.tables.User.USER;

import java.util.UUID;
import java.util.function.Function;

import io.github.jklingsporn.vertx.jooq.rx.reactivepg.ReactiveRXQueryExecutor;
import de.jotschi.vertx.loom.db.jooq.tables.pojos.User;
import de.jotschi.vertx.loom.db.jooq.tables.records.UserRecord;
import io.reactivex.Single;

/**
 * Group related operations. 
 */
public final class DaoOps {

	private DaoOps() {
	}

	public static Single<User> insertUser(ReactiveRXQueryExecutor<UserRecord, User, UUID> tx, User userPojo, Function<Object, UUID> keyConverter) {
		return tx.insertReturning(ctx -> {
			return ctx
				.insertInto(USER)
				.set(ctx.newRecord(USER, userPojo))
				.returning(USER.getPrimaryKey().getFieldsArray());
		}, keyConverter).map(pk -> userPojo.setUuid(pk));
	}
}
