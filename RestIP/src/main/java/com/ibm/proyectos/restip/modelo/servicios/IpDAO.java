package com.ibm.proyectos.restip.modelo.servicios;

import com.ibm.proyectos.restip.modelo.dto.InformacionDTO;
import com.ibm.proyectos.restip.modelo.entidades.Ip;
import com.ibm.proyectos.restip.modelo.entidades.Moneda;
import com.ibm.proyectos.restip.modelo.entidades.Pais;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;

public interface IpDAO
{

    public Optional<Ip> findIpbyIpContains(String ip);
    public List<Ip> buscarTodos();
    public Boolean validarIp(String ip);
    public InformacionDTO recopilarInfo(String ip, String mensaje);




}
