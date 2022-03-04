package com.ibm.proyectos.restip.controladores;

import com.ibm.proyectos.restip.excepciones.BadRequestException;
import com.ibm.proyectos.restip.excepciones.NotFoundException;
import com.ibm.proyectos.restip.modelo.dto.InformacionDTO;
import com.ibm.proyectos.restip.modelo.entidades.Ip;
import com.ibm.proyectos.restip.modelo.entidades.Moneda;
import com.ibm.proyectos.restip.modelo.entidades.Pais;
import com.ibm.proyectos.restip.modelo.mapper.InformacionMapper;
import com.ibm.proyectos.restip.modelo.servicios.IpDAO;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping
public class IpController
{
    private final static Logger Logger = LoggerFactory.getLogger(IpController.class);

    @Autowired
    private IpDAO ipDAO;

    @Autowired
    private Environment environment;

    @Value("${server.port}")
	private Integer puerto;

    @Autowired
    private CircuitBreakerFactory circuitBreaker;

    /**
     * Método para consultar información de IP y su moneda local respecto al EURO
     * @param ip Parámetro que envía el usuario
     * @return Retorna un objeto de tipo InformacionDTO
     */
    @GetMapping(value="/ip/consultarinfo/ip/{ip}")
    public ResponseEntity<?> ConsultarInfoIp(@PathVariable String ip) throws InterruptedException{
        Optional<Ip> ips=null;
        String mensaje;

        InformacionDTO informacionDTO=new InformacionDTO();
        InformacionDTO informacionDTO1=new InformacionDTO();
        try {
            ips = ipDAO.findIpbyIpContains(ip);

        }

        catch (Exception s){

        }
        finally {

            if (!ips.isPresent()) {
                mensaje="LA IP:"+ip+" Ha sido consultada con éxito";
                 //informacionDTO=ipDAO.recopilarInfo(ip,mensaje);
                return circuitBreaker.create("ipcorrecta")
                        .run(()-> new ResponseEntity<InformacionDTO>(ipDAO.recopilarInfo(ip,mensaje),HttpStatus.OK)  , e->ConsultarAlternativo(ip));
            }
            else {
                mensaje="LA IP:"+ip+" se encuentra baneada del sistema";
                informacionDTO1= InformacionMapper.informacionBan(ip);
                informacionDTO1.setMensaje("LA IP:"+ip+" Se encuentra BANEADA y no puede ser consultada");
                return new ResponseEntity<InformacionDTO>(informacionDTO1, HttpStatus.OK);
            }
        }

    }

    /**
     * Método alternativo para consultar la información de IP y su moneda locar respecto al euro
     * @param ip Parámetro que envía el usuario
     * @return Retorna un objeto de tipo InformacionDTO
     */
    public ResponseEntity<?> ConsultarAlternativo(@PathVariable String ip){
        Optional<Ip> ips=null;
     String mensaje="LA IP:"+ip+" Ha sido consultada con éxito";
                InformacionDTO informacionDTO=ipDAO.recopilarInfo(ip, mensaje);
                return new ResponseEntity<InformacionDTO>(informacionDTO,HttpStatus.OK);
    }


    /**
     * Endpoint para consultar las IP baneadas
     *
     * @return
     */
    @GetMapping("/ip/banlist")
    public ResponseEntity<?> ObtenerIpBaneadas(){

        List<Ip> Ips= ipDAO.buscarTodos();
        return new ResponseEntity<List<Ip>>(Ips,HttpStatus.OK);

    }

}
