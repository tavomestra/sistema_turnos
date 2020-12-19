/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.api;

import com.ingmega.turnos.services.TurnosService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ingmega.turnos.dto.TurnosRs;
import com.ingmega.turnos.dto.GenerarTurnosRq;
import com.ingmega.turnos.exception.ApiException;
import com.ingmega.turnos.exception.BadRequestException;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/turnos")
public class TurnosApiController {

    private final TurnosService turnosService;

    public TurnosApiController(TurnosService turnosService) {
        this.turnosService = turnosService;
    }

    @PostMapping("generar-turnos")
    public ResponseEntity<List<TurnosRs>> generarTurnos(HttpServletRequest request,
            @RequestBody  GenerarTurnosRq generarTurnosRq) throws ApiException {

        try {
            return ResponseEntity.ok(turnosService
                    .crearTurnos(generarTurnosRq.getIdServicio(),
                            generarTurnosRq.getFechaInicio(),
                            generarTurnosRq.getFechaFin()));
        } catch (BadRequestException e) {
            throw e;
        }

    }

}
