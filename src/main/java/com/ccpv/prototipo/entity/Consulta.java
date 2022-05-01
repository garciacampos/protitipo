package com.ccpv.prototipo.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor()
public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;
    private String consulta;
    private int registros;

}
