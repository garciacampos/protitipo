CREATE TABLE public.T_PRESTAMO (
    id_prestamo bigint NOT NULL,
    folio VARCHAR(100),
    clave_corro VARCHAR(100),
    isin VARCHAR(100),
    titulos INT,
    tasa FLOAT8,
    prima FLOAT8,
    importe FLOAT8,
    precio FLOAT8,
    importe_aforado FLOAT8,
    precio_aforado FLOAT8,
    aforo FLOAT8,
    hora_concertacion TIMESTAMP,
    fecha_liquidacion DATE,
    fecha_vencimiento DATE,
    clave_prestamista VARCHAR(100),
    clave_prestatario VARCHAR(100)
);
CREATE SEQUENCE public.prestamo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
