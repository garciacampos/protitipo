package com.ccpv.prototipo;

import com.ccpv.prototipo.entity.Prestamo;
import com.ccpv.prototipo.service.PrestamoService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.reactivex.Single;

import javax.inject.Inject;

@Controller("/prestamo")
public class PrestamosController {

    @Inject
    PrestamoService prestamoService;

    /**
     *
     * @return
     */
    @Get("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Single<Iterable<Prestamo>> getAll(){
        return Single.just(prestamoService.getAllPrestamos());
    }

    /**
     *
     * @param folio
     * @return
     */
    @Get("/find/{folio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Single<Prestamo> getByFolio(String folio){
        return Single.just(prestamoService.getByFolio(folio));
    }
}
