package com.ibm.proyectos.restip.excepciones;

public class BadRequestException extends RuntimeException
{
    private static final long serialVersionUID = 579317910260713211L;

    public BadRequestException(String mensaje)
    {
        super(mensaje);
    }


}
