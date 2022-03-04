package com.ibm.proyectos.restip.modelo.repositorios;

import com.ibm.proyectos.restip.modelo.entidades.Ip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IpRepository extends PagingAndSortingRepository<Ip, Long>
{

    @Query("select p from Ip p where p.ip = ?1")
    public Optional<Ip> findIpbyIpContains(String ip);
}
