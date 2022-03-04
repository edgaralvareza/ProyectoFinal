package com.ibm.proyectos.restip.modelo.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashMap;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pais implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String country;



    private String currency;


    private static final long serialVersionUID = -8518030995994248263L;
}
