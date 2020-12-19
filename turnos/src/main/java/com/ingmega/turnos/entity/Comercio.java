/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingmega.turnos.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
@Entity
@Table(name = "COMERCIOS")
public class Comercio implements Serializable {

    @Id
    @Column(name = "ID_COMERCIO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "NOM_COMERCIO", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "AFORO_MAXIMO", nullable = true)
    private Integer aforoMaximo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAforoMaximo() {
        return aforoMaximo;
    }

    public void setAforoMaximo(Integer aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }
    
    
    
}
