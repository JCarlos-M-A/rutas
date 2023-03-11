package com.alvarez.app.rutas.models.enums;

public enum Marcas {

    VOLVO("Volvo"),
    FORD("Ford"),
    ALLIANCE("Alliance"),
    MERCEDDES_BENZ("Mercedes Benz"),
    MAZDA("Mazda"),
    DINA("Dina");

    //Atributos
    private String descripcion;

    //Constructor
    Marcas(String descripcion) {
        this.descripcion = descripcion;
    }

    //Geters y setters

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //METODO QUE RECIBE UN STRING Y DEVUELVE UNA MARCA
    public static Marcas getFromString(String estatus){
        switch (estatus){
            case "Volvo":
                return VOLVO;
            case "Ford":
                return FORD;
            case "Alliance":
                return ALLIANCE;
            case "Mercedes Benz":
                return MERCEDDES_BENZ;
            case "Mazda":
                return MAZDA;
            case "Dina":
                return DINA;
            default:
                return null;
        }
    }
}
