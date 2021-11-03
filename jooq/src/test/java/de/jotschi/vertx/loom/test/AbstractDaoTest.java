package de.jotschi.vertx.loom.test;

import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.junit.Before;
import org.junit.Rule;

import de.jotschi.vertx.loom.db.PocGroupDao;
import de.jotschi.vertx.loom.db.PocUserDao;
import de.jotschi.vertx.loom.db.flyway.FlywayHelper;
import de.jotschi.vertx.loom.db.impl.PocGroupDaoImpl;
import de.jotschi.vertx.loom.db.impl.PocUserDaoImpl;
import de.jotschi.vertx.loom.db.jooq.tables.daos.UserDao;
import de.jotschi.vertx.loom.db.jooq.tables.daos.UserGroupDao;
import de.jotschi.vertx.loom.env.PocPostgreSQLContainer;
import de.jotschi.vertx.loom.option.DatabaseOptions;
import io.vertx.core.Vertx;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.reactivex.sqlclient.SqlClient;
import io.vertx.sqlclient.PoolOptions;

public class AbstractDaoTest {

	public static Vertx vertx = Vertx.vertx();

	@Rule
	public PocPostgreSQLContainer container = new PocPostgreSQLContainer();

	private SqlClient sqlClient;

	@Before
	public void setupClient() {
		System.out.println("---");
		System.out.println(container.getContainerIpAddress() + ":" + container.getPort());
		System.out.println("JDBCUrl: " + container.getJdbcUrl());
		System.out.println("Username: " + container.getUsername());
		System.out.println("Password: " + container.getPassword());
		System.out.println("---");
		FlywayHelper.migrate(container.getOptions());
		this.sqlClient = setupSQLClient(vertx, container.getOptions());
	}

	private SqlClient setupSQLClient(Vertx vertx, DatabaseOptions dbOptions) {
		String host = dbOptions.getHost();
		int port = dbOptions.getPort();
		String username = dbOptions.getUsername();
		String password = dbOptions.getPassword();
		String database = dbOptions.getDatabaseName();

		PgConnectOptions config = new PgConnectOptions()
			.setHost(host)
			.setPort(port)
			.setUser(username)
			.setPassword(password)
			.setDatabase(database);

		PgPool client = PgPool.pool(vertx, config, new PoolOptions().setMaxSize(32));
		return new io.vertx.reactivex.sqlclient.Pool(client);
	}

	private Configuration jooqConfiguration() {
		Configuration configuration = new DefaultConfiguration();
		return configuration.set(SQLDialect.POSTGRES);
	}

	public PocUserDao userDao() {
		UserDao userDao = new UserDao(jooqConfiguration(), sqlClient);
		return new PocUserDaoImpl(userDao);
	}

	public PocGroupDao groupDao() {
		Configuration config = jooqConfiguration();

		UserDao userDao = new UserDao(config, sqlClient);
		UserGroupDao userGroupDao = new UserGroupDao(config, sqlClient);

		return new PocGroupDaoImpl(config, sqlClient, userDao, userGroupDao);
	}

}
