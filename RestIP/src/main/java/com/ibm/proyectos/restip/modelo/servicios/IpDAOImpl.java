package com.ibm.proyectos.restip.modelo.servicios;

import com.ibm.proyectos.restip.excepciones.BadRequestException;
import com.ibm.proyectos.restip.excepciones.NotFoundException;
import com.ibm.proyectos.restip.modelo.dto.InformacionDTO;
import com.ibm.proyectos.restip.modelo.entidades.Ip;
import com.ibm.proyectos.restip.modelo.entidades.Moneda;
import com.ibm.proyectos.restip.modelo.entidades.Pais;
import com.ibm.proyectos.restip.modelo.mapper.InformacionMapper;
import com.ibm.proyectos.restip.modelo.repositorios.IpRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.EntityResponse;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class IpDAOImpl implements IpDAO
{
    private final static Logger logger = LoggerFactory.getLogger(IpDAOImpl.class);

    @Autowired
    private IpRepository ipRepository;


    private IpDAO ipDAO;

    @Autowired
    private RestTemplate clienteRest;


    private InformacionDTO informacionDTO;
    private Pais paises;

    @Autowired
    private Environment environment;

    @Override
    @Transactional(readOnly = true)
    public Optional<Ip> findIpbyIpContains(String ip) {
        Optional<Ip> ips= null;
        ips = ipRepository.findIpbyIpContains(ip);
        return ips;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Ip> buscarTodos() {
        return (List<Ip>) ipRepository.findAll();

    }

    @Override
    public Boolean validarIp(String ip) {
        String[] tokens = ip.split("\\.");
        if (tokens.length != 4) {
            return false;
        }
        for (String str : tokens) {
            int i = Integer.parseInt(str);
            if ((i < 0) || (i > 255)) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public InformacionDTO recopilarInfo(String ip, String mensaje)  {

        Boolean check= validarIp(ip);
        try {
            if (check.equals(Boolean.FALSE))
                throw new BadRequestException(String.format("La ip:  %s ingresada no posee el formato correcto ", ip));
        }

        catch (Exception e){
            logger.info("mensaje: " + e.getMessage() + " Causa: " + e.getCause());
            throw e;
        }



        String access_key= "1778fd81df8b97d81668705d53292c22";

        String field="country_name,country_code";
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("access_key",access_key);

        Pais paises = clienteRest.getForObject("http://ip-api.com/json/"+ip+"?fields=country,currency",Pais.class);


         access_key= "6f78f21cec-7006b34da5-r805ak";


        pathVariables = new HashMap<String, String>();
        pathVariables.put("access_key",access_key);

        String codigo=paises.getCurrency();

        Moneda monedas = clienteRest.getForObject("http://api.exchangeratesapi.io/v1/latest?access_key=bc34342f08aeb4e8060b5819927e021f&symbols="+codigo,Moneda.class);

        InformacionDTO informacionMapeado= new InformacionDTO();

        informacionMapeado=InformacionMapper.informacionDTO(ip,paises,monedas);
        informacionMapeado.setMensaje(mensaje);
        informacionMapeado.setPuerto(Integer.parseInt(environment.getProperty("local.server.port")));

        return informacionMapeado;
    }



}
