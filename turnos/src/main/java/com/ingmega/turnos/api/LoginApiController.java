/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.api;

import com.ingmega.turnos.dto.ComercioRs;
import com.ingmega.turnos.exception.ApiException;
import com.ingmega.turnos.services.LoginService;
import com.ingmega.turnos.dto.LoginRq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
@Api(tags = "login", value = "/login")
public class LoginApiController {

    private final LoginService loginService;

    public LoginApiController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Login de usuario",                        
            httpMethod = "POST")
    public void doLogin(HttpServletRequest request,
            @RequestBody LoginRq loginRq) throws ApiException {
        loginService.doLogin(loginRq.getUser(), loginRq.getPassword());
    }

}
