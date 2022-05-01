package com.ccpv;

import com.ccpv.prototipo.entity.Prestamo;
import com.ccpv.prototipo.service.AnalyticsClient;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.micronaut.http.HttpRequest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Testcontainers
@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrestamosControllerTest implements TestPropertyProvider {

    @Inject
    @Client("/")
    private RxHttpClient client;

    @Inject
    AnalyticsClient analyticsClient;

    @Container
    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:13.3")
                    .withDatabaseName("test")
                    .withUsername("test")
                    .withPassword("test").
                    withExposedPorts(5432);

    @Test
    void testComplete() {
        HttpResponse<List<Prestamo>> usersResponse = client.toBlocking()
                .exchange(GET("/prestamo/getAll"), Argument.listOf(Prestamo.class));
        assertThat(usersResponse.code()).isEqualTo(200);
        assertThat(usersResponse.getBody().get()).hasSize(1);
    }

    @MockBean(AnalyticsClient.class)
    AnalyticsClient mathService() {
        return mock(AnalyticsClient.class);
    }

    @Override
    public Map<String, String> getProperties() {
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
