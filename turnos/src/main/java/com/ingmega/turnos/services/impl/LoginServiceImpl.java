/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.services.impl;

import com.ingmega.turnos.exception.ApiException;
import com.ingmega.turnos.exception.BadRequestException;
import com.ingmega.turnos.exception.ErrorRq;
import com.ingmega.turnos.exception.MessageExceptions;
import com.ingmega.turnos.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public void doLogin(String user, String password) throws ApiException {
        if (!(user.equalsIgnoreCase("admin") && password.equalsIgnoreCase("123456"))) {
            throw new BadRequestException(
                    ErrorRq.getErrorRq(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            MessageExceptions.LOGIN_WRONG, HttpStatus.BAD_REQUEST.value()));
        }
    }

}
