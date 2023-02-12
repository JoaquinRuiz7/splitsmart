package com.jota.splitsmart.context;

import static com.jota.splitsmart.context.ContextData.REPOSITORIES_PACKAGE;
import static java.lang.String.format;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = REPOSITORIES_PACKAGE)
public class DatabaseConfig {

    private static final String JDBC = "jdbc:postgresql://";

    @Value("${database.host}")
    private String host;
    @Value("${database.port}")
    private String port;
    @Value("${database.username}")
    private String username;
    @Value("${database.password}")
    private String password;
    @Value("${database.name}")
    private String databaseName;

    @Bean
    public DataSource getDataSource() {
        final DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        final String url = format("%s%s:%s/%s", JDBC, host, port, databaseName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}
