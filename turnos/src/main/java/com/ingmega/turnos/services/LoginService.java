/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.services;

import com.ingmega.turnos.exception.ApiException;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
public interface LoginService {
    
    /**
     * Si credenciales son correctas permite ingresa, de lo contrario genera
     * una excepcion.
     * @param user
     * @param password
     * @throws ApiException 
     */
    void doLogin(String user, String password) throws ApiException;
    
}
