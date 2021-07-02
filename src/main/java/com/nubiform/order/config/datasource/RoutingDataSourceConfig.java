package com.nubiform.order.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.nubiform.order.constant.DataSourceType.READ_ONLY;
import static com.nubiform.order.constant.DataSourceType.WRITE;

@Configuration
public class RoutingDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.read-only")
    public DataSourceProperty readOnlyDataSourceProperty() {
        return new DataSourceProperty();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.write")
    public DataSourceProperty writeDataSourceProperty() {
        return new DataSourceProperty();
    }

    public DataSource createDataSource(DataSourceProperty dataSourceProperty) {
        return DataSourceBuilder.create().type(HikariDataSource.class)
                .url(dataSourceProperty.getUrl())
                .username(dataSourceProperty.getUserName())
                .password(dataSourceProperty.getPassword())
                .build();
    }

    @Bean
    public DataSource routingDataSource(DataSourceProperty readOnlyDataSourceProperty, DataSourceProperty writeDataSourceProperty) {
        DataSource readOnlyDataSource = createDataSource(readOnlyDataSourceProperty);
        DataSource writeDataSource = createDataSource(writeDataSourceProperty);

        RoutingDataSource routingDataSource = new RoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(READ_ONLY, readOnlyDataSource);
        dataSourceMap.put(WRITE, writeDataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(readOnlyDataSource);

        return routingDataSource;
    }

    @Primary
    @Bean
    @DependsOn({"routingDataSource"})
    public DataSource dataSource(DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
}
