package com.ibm.proyectos.restip.excepciones;

public class NotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1374482287539463770L;

    public NotFoundException(String mensaje)
    {
        super(mensaje);
    }


}
