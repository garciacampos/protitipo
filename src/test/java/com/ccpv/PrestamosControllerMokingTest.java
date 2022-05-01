package com.ccpv;


import com.ccpv.prototipo.entity.Prestamo;
import com.ccpv.prototipo.service.AnalyticsClient;
import com.ccpv.prototipo.service.PrestamoService;
import com.ccpv.prototipo.service.PrestamoServiceImpl;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.micronaut.http.HttpRequest.GET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Testcontainers
@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrestamosControllerMokingTest implements TestPropertyProvider {

    @Inject
    @Client("/")
    private RxHttpClient client;

    @Container
    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:13.3")
                    .withDatabaseName("test")
                    .withUsername("test")
                    .withPassword("test").
                    withExposedPorts(5432);

    @Inject
    PrestamoService prestamoService;

    @Inject
    AnalyticsClient analyticsClient;

    @Test()
    public void testWithMock() {
        when( prestamoService.getAllPrestamos() )
                .then(invocation -> new ArrayList<Prestamo>());

        HttpResponse<List<Prestamo>> usersResponse = client.toBlocking()
                .exchange(GET("/prestamo/getAll"), Argument.listOf(Prestamo.class));
        assertThat(usersResponse.code()).isEqualTo(200);
        assertThat(usersResponse.getBody().get()).hasSize(0);

        Mockito.verify(prestamoService).getAllPrestamos();
    }

    @MockBean(PrestamoServiceImpl.class)
    PrestamoService prestamoService() {
        return mock(PrestamoService.class);
    }

    @MockBean(AnalyticsClient.class)
    AnalyticsClient mathService() {
        return mock(AnalyticsClient.class);
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
