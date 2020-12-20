/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.services.impl;

import com.ingmega.turnos.dto.ServiciosRs;
import com.ingmega.turnos.repository.ServicioReporsitory;
import com.ingmega.turnos.services.ServicioService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioReporsitory servicioReporsitory;

    public ServicioServiceImpl(ServicioReporsitory servicioReporsitory) {
        this.servicioReporsitory = servicioReporsitory;
    }
    
    @Override
    public List<ServiciosRs> getServiceByComercio(Integer comercioId) {
        var servicios =  servicioReporsitory.findByComercioId(comercioId);
        return servicios.stream().map( x -> {
            return ServiciosRs.builder().id(x.getId()).nombre(x.getNombre()).build();
        }).collect(Collectors.toList());
    }
    
}
