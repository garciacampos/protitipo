package com.ccpv.prototipo.service;

import com.ccpv.prototipo.entity.Prestamo;
import com.ccpv.prototipo.repository.PrestamoRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PrestamoServiceImpl implements PrestamoService {

    @Inject
    PrestamoRepository prestamoRepository;

    /**
     *
     * @return
     */
    @Override
    public Iterable<Prestamo> getAllPrestamos(){
        return prestamoRepository.findAll();
    }

    /**
     *
     * @param folio
     * @return
     */
    @Override
    public Prestamo getByFolio(String folio){
        return prestamoRepository.findByFolio(folio);
    }

}
