/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.repository;

import com.ingmega.turnos.entity.Turnos;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
@Repository
public interface TurnoRepository extends JpaRepository<Turnos, Integer> {
    
    
    @Query("select count(t) from Turnos t WHERE t.servicio.id =:idServicio and t.fechaTurno BETWEEN :fechaIni AND :fechaFin")
    Long tieneTurnos(int idServicio, LocalDate fechaIni, LocalDate fechaFin);

}
