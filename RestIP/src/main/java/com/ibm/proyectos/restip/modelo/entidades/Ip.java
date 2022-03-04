package com.ibm.proyectos.restip.modelo.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ip")
public class Ip implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip",nullable = false)
    private String ip;

    @Size(min = 3, max = 100)
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;


    @Transient
    private Integer puerto;

    private static final long serialVersionUID = -6205993205306819539L;
}
