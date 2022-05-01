package com.ccpv;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Testcontainers
@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrestamosTest implements TestPropertyProvider {

    @Container
    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:13.3")
                    .withDatabaseName("test")
                    .withUsername("test")
                    .withPassword("test").
                    withExposedPorts(5432);

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Override
    public Map<String, String> getProperties() {
        System.out.println("### properties retieved");
        Map<String, String> myMap = new HashMap() {{
            put("datasources.default.dialect", "POSTGRES");
            put("datasources.default.url", container.getJdbcUrl());
            put("datasources.default.driverClassName", container.getDriverClassName());
            put("datasources.default.username", container.getUsername());
            put("datasources.default.password", container.getPassword());
        }};
        return myMap;
    }

}
