package com.ibm.proyectos.restip.modelo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

@Setter
@Getter
public class InformacionDTO implements Serializable
{
    private String ip;
    private String codigo_pais;
    private String nombre_pais;
    private String moneda_base;
    private String conversion_moneda_local;

    @Transient
    private String mensaje;

    @Transient
    private Integer puerto;

    private static final long serialVersionUID = 1940482549396484155L;

}
