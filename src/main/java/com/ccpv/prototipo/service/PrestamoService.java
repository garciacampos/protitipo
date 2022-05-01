package com.ccpv.prototipo.service;

import com.ccpv.prototipo.entity.Prestamo;

public interface PrestamoService {

    Iterable<Prestamo> getAllPrestamos();

    Prestamo getByFolio(String folio);
}
