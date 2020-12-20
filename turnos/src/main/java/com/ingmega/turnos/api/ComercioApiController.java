/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.api;

import com.ingmega.turnos.dto.ComercioRs;
import com.ingmega.turnos.dto.ServiciosRs;
import com.ingmega.turnos.services.ComercioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/comercio")
@Api(tags = "comercio", value = "/comercio")
public class ComercioApiController {

    private final ComercioService comercioService;

    public ComercioApiController(ComercioService comercioService) {
        this.comercioService = comercioService;
    }

    @GetMapping("get-all")
    @ApiOperation(value = "Obtener todos los comercios",
            response = ComercioRs.class,
            responseContainer = "List",
            httpMethod = "GET")
    public ResponseEntity<List<ComercioRs>> getAll(HttpServletRequest request) {
        return ResponseEntity.ok(comercioService.getAll());
    }

}
