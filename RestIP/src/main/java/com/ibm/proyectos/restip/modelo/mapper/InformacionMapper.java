package com.ibm.proyectos.restip.modelo.mapper;

import com.ibm.proyectos.restip.modelo.dto.InformacionDTO;
import com.ibm.proyectos.restip.modelo.entidades.Ip;
import com.ibm.proyectos.restip.modelo.entidades.Moneda;
import com.ibm.proyectos.restip.modelo.entidades.Pais;

public class InformacionMapper
{
    public static InformacionDTO informacionDTO(String ip, Pais pais, Moneda moneda){
InformacionDTO informacionDTO = new InformacionDTO();
informacionDTO.setIp(ip);
informacionDTO.setCodigo_pais(pais.getCurrency());
informacionDTO.setNombre_pais(pais.getCountry());
informacionDTO.setMoneda_base(moneda.getBase());
informacionDTO.setConversion_moneda_local(moneda.getRates().toString());

return informacionDTO;
    }

    public static InformacionDTO informacionBan(String ip) {
        InformacionDTO informacionDTO = new InformacionDTO();
        informacionDTO.setIp(ip);
        informacionDTO.setCodigo_pais(null);
        informacionDTO.setNombre_pais(null);
        informacionDTO.setMoneda_base(null);
        informacionDTO.setConversion_moneda_local(null);

        return informacionDTO;
    }

}
