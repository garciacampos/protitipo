package com.ccpv.prototipo.filter;

import com.ccpv.prototipo.entity.Consulta;
import com.ccpv.prototipo.entity.Prestamo;
import com.ccpv.prototipo.service.AnalyticsClient;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

//@Filter("/prestamo/getAll")
public class PrestamosFilter implements HttpServerFilter {

    Logger logger = LoggerFactory.getLogger("com.ccpv.prototipo.filter.PrestamosFilter");

    @Inject
    AnalyticsClient analyticsClient;

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        return Flowable
                .fromPublisher(chain.proceed(request))
                .flatMap(response ->
                        Flowable.fromCallable(() -> {
                            Optional<List> prestamos =  response.getBody(List.class);
                            int size = prestamos.get().size();
                            logger.info("prestamos:"+size);
                            Consulta consulta = new Consulta("Prestamos", size);
                            analyticsClient.agregarconsultaAnalytics(consulta);
                            return response;
                        })
                );
    }
}
