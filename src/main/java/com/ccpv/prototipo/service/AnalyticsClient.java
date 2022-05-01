package com.ccpv.prototipo.service;

import com.ccpv.prototipo.entity.Consulta;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

@Client("${SIDECAR_URL:`http://localhost:8081`}")
public interface AnalyticsClient {

    @Post("/analytics/agregar/")
    void agregarconsultaAnalytics(@Body Consulta consulta);

}
