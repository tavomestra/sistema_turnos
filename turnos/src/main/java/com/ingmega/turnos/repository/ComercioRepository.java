/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.repository;

import com.ingmega.turnos.entity.Comercio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Integer> {

    @Procedure(name = "CREAR_TURNOS")
    List<Object[]> callProcedure(@Param("ID_SERVICIO") int id,
             @Param("FECHA_INICIO") String fechaInicio,
             @Param("FECHA_FIN") String fechaFin);

}
