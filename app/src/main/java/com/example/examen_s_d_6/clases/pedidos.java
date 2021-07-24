package com.example.examen_s_d_6.clases;

public class pedidos {
    private Integer ID;
    private String  fecha;

    public pedidos() {
    }
    public pedidos(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
