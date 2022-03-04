package com.ibm.proyectos.restip.excepciones.handler;

import com.ibm.proyectos.restip.excepciones.BadRequestException;
import com.ibm.proyectos.restip.excepciones.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class RESTIpException {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> noExisteIpException(NotFoundException exception)
    {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("error", exception.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> formatoInvalidoException(BadRequestException exception)
    {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("error", exception.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }
}
