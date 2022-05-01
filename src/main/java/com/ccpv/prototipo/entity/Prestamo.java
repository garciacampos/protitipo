package com.ccpv.prototipo.entity;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Introspected
@Entity
@Table(name = "T_PRESTAMO")
@Getter @Setter @NoArgsConstructor
public class Prestamo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_prestamo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prestamo_seq")
    @SequenceGenerator(name = "prestamo_seq", sequenceName = "prestamo_seq", allocationSize = 1)
    private Long id;
    @Column(name = "folio")
    private String folio;
    @Column(name = "claveCorro")
    private String claveCorro;
    @Column(name = "isin")
    private String isin;
    @Column(name = "titulos")
    private int titulos;
    @Column(name = "tasa")
    private float tasa;
    @Column(name = "prima")
    private float prima;
    @Column(name = "importe")
    private float importe;
    @Column(name = "precio")
    private float precio;
    @Column(name = "importe_aforado")
    private float importeAforado;
    @Column(name = "precio_aforado")
    private float precioAforado;
    @Column(name = "aforo")
    private float aforo;
    @Column(name = "hora_concertacion")
    private Date horaConcertacion;
    @Column(name = "fecha_liquidacion")
    private Date fechaLiquidacion;
    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;
    @Column(name = "clave_prestamista")
    private String clavePrestamista;
    @Column(name = "clave_prestatario")
    private String clavePrestatario;

    public String getFolio() {
        return folio;
    }

    public Long getId() {
        return id;
    }
}
