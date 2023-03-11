package com.alvarez.app.rutas.models;

import java.time.LocalDate;

public class Direccion {
    private Long id;
    private String calle;
    private String numero;
    private String colonia;
    private String ciudad;
    private String estaodo;
    private LocalDate cp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstaodo() {
        return estaodo;
    }

    public void setEstaodo(String estaodo) {
        this.estaodo = estaodo;
    }

    public LocalDate getCp() {
        return cp;
    }

    public void setCp(LocalDate cp) {
        this.cp = cp;
    }
}
