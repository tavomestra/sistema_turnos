/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.api;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ingmega.turnos.dto.ServiciosRs;
import com.ingmega.turnos.services.ServicioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/servicio")
@Api(tags = "servicio", value = "/servicio")
public class ServicioApiController {

    @Autowired
    ServicioService servicioService;

    @GetMapping("get-by-comercio/{comercioId}")
    @ApiOperation(value = "Obtener los servicios de un comercio", 
            notes = "Es necesario pasar el id del comercio",
            response = ServiciosRs.class,
            responseContainer = "List",
            httpMethod = "GET")
    public ResponseEntity<List<ServiciosRs>> getByComercio(HttpServletRequest request,
            @PathVariable Integer comercioId) {
        return ResponseEntity.ok(servicioService.getServiceByComercio(comercioId));
    }

}
