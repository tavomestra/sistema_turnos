/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.services;

import com.ingmega.turnos.dto.ComercioRs;
import java.util.List;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
public interface ComercioService {
    
    /**
     * Obtener todos los registros de la tabla.
     * 
     * @return List
     */
    List<ComercioRs> getAll();
    
}
