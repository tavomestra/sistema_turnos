/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.services.impl;

import com.ingmega.turnos.dto.ComercioRs;
import com.ingmega.turnos.repository.ComercioRepository;
import com.ingmega.turnos.services.ComercioService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
@Service
public class ComercioServiceImpl implements ComercioService {

    private final ComercioRepository comercioRepository;

    public ComercioServiceImpl(ComercioRepository comercioRepository) {
        this.comercioRepository = comercioRepository;
    }
    
    
    @Override
    public List<ComercioRs> getAll() {
              
        return this.comercioRepository.findAll().stream()
                .map(x -> {
                    return ComercioRs.builder()
                            .id(x.getId())
                            .nombre(x.getNombre())
                            .aforoMaximo(x.getAforoMaximo())
                            .build();
                }).collect(Collectors.toList());
    }

}
