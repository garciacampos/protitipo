package com.ccpv.prototipo.repository;

import com.ccpv.prototipo.entity.Prestamo;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PrestamoRepository extends CrudRepository<Prestamo, Long> {

    @Executable
    Prestamo findByFolio(String folio);
}