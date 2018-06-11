package com.spsvn.api.domain;

import com.spsvn.api.domain.type.Client;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by npkhanh on 6/8/2018.
 */
@Configuration
public class RoutingDataSourceConfiguration {

    @Bean(name = "clientADatasource")
    @Autowired
    DataSource clientADatasource(
            @Value("${client.a.datasource.url}") String url,
            @Value("${client.a.datasource.username}") String username,
            @Value("${client.a.datasource.password}") String password,
            @Value("${client.a.datasource.driver-class-name}") String driverClassName,
            @Value("${client.a.datasource.maximum-pool-size:-1}") int maxPoolSize,
            @Value("${client.a.datasource.minimum-idle:-1}") int minimumIdle,
            @Value("${client.a.datasource.idle-timeout:-1}") long idleTimeout,
            @Value("${client.a.datasource.max-lifetime:-1}") long maxLifetime,
            @Value("${client.a.datasource.connection-timeout:-1}") long connectionTimeout) {
        HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
        dataSource.setMaximumPoolSize(maxPoolSize);
        dataSource.setMinimumIdle(minimumIdle);
        dataSource.setIdleTimeout(idleTimeout);
        dataSource.setMaxLifetime(maxLifetime);
        dataSource.setConnectionTimeout(connectionTimeout);
        return dataSource;
    }

    @Bean(name = "clientBDatasource")
    @Autowired
    DataSource clientBDatasource(
            @Value("${client.b.datasource.url}") String url,
            @Value("${client.b.datasource.username}") String username,
            @Value("${client.b.datasource.password}") String password,
            @Value("${client.b.datasource.driver-class-name}") String driverClassName,
            @Value("${client.b.datasource.maximum-pool-size:-1}") int maxPoolSize,
            @Value("${client.b.datasource.minimum-idle:-1}") int minimumIdle,
            @Value("${client.b.datasource.idle-timeout:-1}") long idleTimeout,
            @Value("${client.b.datasource.max-lifetime:-1}") long maxLifetime,
            @Value("${client.b.datasource.connection-timeout:-1}") long connectionTimeout) {
        HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
        dataSource.setMaximumPoolSize(maxPoolSize);
        dataSource.setMinimumIdle(minimumIdle);
        dataSource.setIdleTimeout(idleTimeout);
        dataSource.setMaxLifetime(maxLifetime);
        dataSource.setConnectionTimeout(connectionTimeout);
        return dataSource;
    }

    @Bean
    @Primary
    @Autowired
    public DataSource clientDatasource(
            @Lazy @Qualifier("clientADatasource") DataSource clientADatasource,
            @Lazy @Qualifier("clientBDatasource") DataSource clientBDatasource) {
        Map<Object, Object> targetDataSources = new HashMap<>();

        targetDataSources.put(Client.CLIENT_A, clientADatasource);
        targetDataSources.put(Client.CLIENT_B, clientBDatasource);
        ClientDataSourceRouter clientRoutingDatasource = new ClientDataSourceRouter();
        clientRoutingDatasource.setTargetDataSources(targetDataSources);
        clientRoutingDatasource.setDefaultTargetDataSource(clientADatasource);
        return clientRoutingDatasource;
    }
}
