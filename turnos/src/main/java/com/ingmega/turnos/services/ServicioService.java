/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.services;

import java.util.List;
import com.ingmega.turnos.dto.ServiciosRs;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
public interface ServicioService {
    
    
    /**
     * Obterner los servicios de un comercio por el id.
     * @param comercioId
     * @return List
     */
    List<ServiciosRs> getServiceByComercio(Integer comercioId);
    
}
