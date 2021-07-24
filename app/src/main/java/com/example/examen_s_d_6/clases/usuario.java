package com.example.examen_s_d_6.clases;

public class usuario {
    private Integer ID;
    private String nombre;
    private String email;
    private String phone;
    private String password;

    public usuario() {
    }

    public usuario(Integer ID, String nombre, String email, String phone, String password) {
        this.ID = ID;
        this.nombre = nombre;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
