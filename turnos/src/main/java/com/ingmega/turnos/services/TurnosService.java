/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.services;

import java.util.List;
import com.ingmega.turnos.dto.TurnosRs;
import com.ingmega.turnos.exception.ApiException;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
public interface TurnosService {
    
    List<TurnosRs> crearTurnos(int id, String fechaInicio, String fechaFin) throws ApiException;
    
}
