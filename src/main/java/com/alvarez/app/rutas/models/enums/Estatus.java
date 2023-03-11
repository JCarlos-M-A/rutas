package com.alvarez.app.rutas.models.enums;

public enum Estatus {
    OK(1,"Mercancia ok"),
    AVERIADO(2, "Mercancia dañada"),
    EXTRAVIADO(3, "Mercancia extraviada");

    private Integer id;
    private String descripcion;

    Estatus(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static Estatus getFromString(String estatus){
        switch (estatus){
            case "ok":
                return OK;
            case "averiado":
                return AVERIADO;
            case "extraviado":
                return EXTRAVIADO;
            default:
                return null;
        }
    }

    public static Estatus getFromInteger(Integer estatus){
        switch (estatus){
            case 1:
                return OK;
            case 2:
                return AVERIADO;
            case 3:
                return EXTRAVIADO;
            default:
                return null;
        }
    }
}
