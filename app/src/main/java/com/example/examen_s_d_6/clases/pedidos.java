package com.example.examen_s_d_6.clases;

public class pedidos {
    private Integer ID;
    private String  fecha;
    private float total;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public pedidos(Integer ID, String fecha, float total) {
        this.ID = ID;
        this.fecha = fecha;
        this.total = total;
    }

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
