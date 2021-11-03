package de.jotschi.vertx.loom.env;

import org.testcontainers.containers.PostgreSQLContainer;

import de.jotschi.vertx.loom.option.DatabaseOptions;

/**
 * Preconfigured {@link PocPostgreSQLContainer}
 */
public class PocPostgreSQLContainer extends  PostgreSQLContainer<PocPostgreSQLContainer> {

	public static final String DEFAULT_IMAGE = "postgres:13.2";

	public PocPostgreSQLContainer() {
		super(DEFAULT_IMAGE);
		withDatabaseName("postgres");
		withUsername("sa");
		withPassword("sa");
	}

	public int getPort() {
		return getFirstMappedPort();
	}

	public DatabaseOptions getOptions() {
		DatabaseOptions options = new DatabaseOptions();
		options.setPort(getPort());
		options.setHost(getContainerIpAddress());
		options.setUsername(getUsername());
		options.setPassword(getPassword());
		options.setDatabaseName(getDatabaseName());
		return options;
	}
}
