/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerarTurnosRq {

    private Integer idServicio;    
    
    private String fechaInicio;
    
    private String fechaFin;

}
