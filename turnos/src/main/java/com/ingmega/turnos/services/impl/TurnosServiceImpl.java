/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.services.impl;

import com.ingmega.turnos.dto.TurnosRs;
import com.ingmega.turnos.exception.ApiException;
import com.ingmega.turnos.exception.BadRequestException;
import com.ingmega.turnos.exception.ErrorRq;
import com.ingmega.turnos.exception.MessageExceptions;
import com.ingmega.turnos.repository.TurnoRepository;
import com.ingmega.turnos.services.TurnosService;
import com.ingmega.turnos.utils.DateUtil;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
@Service
public class TurnosServiceImpl implements TurnosService {

    private final EntityManager entityManager;
    private final TurnoRepository turnoRepository;

    public TurnosServiceImpl(EntityManager entityManager,
            TurnoRepository turnoRepository) {
        this.entityManager = entityManager;
        this.turnoRepository = turnoRepository;
    }

    @Override
    public List<TurnosRs> crearTurnos(int id, String fechaInicio, String fechaFin) throws ApiException {

        var tieneTurno = turnoRepository
                .tieneTurnos(id, DateUtil.stringToLocalDate(fechaInicio),
                        DateUtil.stringToLocalDate(fechaFin)) > 0L;
        if (tieneTurno) {
            throw new BadRequestException(
                    ErrorRq.getErrorRq(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            MessageExceptions.CUPOS_CREADOS, HttpStatus.BAD_REQUEST.value()));
        }

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("CREAR_TURNOS")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Class.class, ParameterMode.REF_CURSOR)
                .setParameter(1, id)
                .setParameter(2, fechaInicio)
                .setParameter(3, fechaFin);

        query.execute();
        List<Object[]> resultSet = query.getResultList();

        return resultSet.stream().map(x -> {
            return TurnosRs.builder()
                    .idTurno(((BigDecimal) x[0]).intValue())
                    .nombreCormecio((String) x[1])
                    .nombreServicio((String) x[2])
                    .fechaTurno(DateUtil.localDateToString(((Timestamp) x[3]).toLocalDateTime()))
                    .horaInicio(DateUtil.convertTime((String) x[4]))
                    .horaFin(DateUtil.convertTime((String) x[5]))
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<TurnosRs> findTurnos(int idServicio, String fechaInicio, String fechaFin) {
       var turnos = this.turnoRepository
               .findTurnos(idServicio, DateUtil.stringToLocalDate(fechaInicio), 
                       DateUtil.stringToLocalDate(fechaFin));
       
         return turnos.stream().map(x -> {
            return TurnosRs.builder()
                    .idTurno(x.getId())
                    .nombreCormecio(x.getServicio().getComercio().getNombre())
                    .nombreServicio(x.getServicio().getNombre())
                    .fechaTurno(DateUtil.localDateToString(x.getFechaTurno()))
                    .horaInicio(DateUtil.convertTime(x.getHoraInicio()))
                    .horaFin(DateUtil.convertTime(x.getHoraFin()))
                    .build();
        }).collect(Collectors.toList());
    }

}
