package com.example.Profile.domain.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class TableMetricsConfig {
    private final EntityManager entityManager;
    private final MeterRegistry registry;

    @PostConstruct
    public void registerMetrics() {
        List<String> entities = Arrays.asList("AuthCredsEntity", "UserProfileEntity");

        entities.forEach(table ->
                Gauge.builder("db.table.rows",
                                () -> getTableCount(table))
                        .tag("table", table.toLowerCase())
                        .description("Number of records in " + table)
                        .register(registry)
        );
    }

    private long getTableCount(String entityName) {
        try {
            return entityManager
                    .createQuery("SELECT COUNT(e) FROM " + entityName + " e", Long.class)
                    .getSingleResult();
        } catch (Exception e) {
            return 0L;
        }
    }
}