package com.example.examen_s_d_6.tablas;

public class tablas {

    public static final  String TABLA_MENU="menu";
    public static final  String CAMPO_ID="id";
    public static final  String CAMPO_NOMBRE="nombre";
    public static final  String CAMPO_PRECIO="precio";
    public static final  String CAMPO_IMG="imagen";
    public static final  String CAMPO_DECS="descripcion";
    public static final  String CAMPO_TYPO="tipo";

    public static final String CREAR_TABLA_MENU="CREATE TABLE " + ""+TABLA_MENU+" " +
            "("+CAMPO_ID+" " +"INTEGER primary key autoincrement, "
            +CAMPO_NOMBRE+" TEXT, "
            +CAMPO_PRECIO+ " REAL, "
            +CAMPO_IMG+ " BLOB, "
            +CAMPO_DECS+ " TEXT, "
            +CAMPO_TYPO+ " TEXT)";

    public static final  String TABLA_CATE="categoria";
    public static final  String CAMPO_ID_CATE="id";
    public static final  String CAMPO_NOMBRE_CATE="nombre";

    public static final String CREAR_TABLA_CATE="CREATE TABLE " + ""+TABLA_CATE+" " +
            "("+CAMPO_ID_CATE+" " +"INTEGER primary key autoincrement, "
               +CAMPO_NOMBRE_CATE+" TEXT) ";

    public static final  String TABLA_USUARIOS="usuarios";
    public static final  String CAMPO_ID_U="id";
    public static final  String CAMPO_NOMBRE_U="nombre";
    public static final  String CAMPO_EMAIL="email";
    public static final  String CAMPO_PHONE="phone";
    public static final  String CAMPO_PASSWORD="password";

    public static final String CREAR_TABLA_USUARIOS="CREATE TABLE " + ""+TABLA_USUARIOS+" " +
            "("+CAMPO_ID_U+" " +"INTEGER primary key autoincrement, "
            +CAMPO_NOMBRE_U+" TEXT, "
            +CAMPO_EMAIL+ " TEXT, "
            +CAMPO_PHONE+ " TEXT, "
            + CAMPO_PASSWORD+" TEXT)";

    public static final String TABLA_PEDIDOS="pedidos";
    public static final String CAMPO_FECHA="fecha";

    public static final String CREAR_TABLA_PEDIDO="CREATE TABLE " + ""+TABLA_PEDIDOS+" "+
            "("+CAMPO_ID+" " +"INTEGER primary key autoincrement, "+CAMPO_FECHA+" TEXT)";


    public static final String TABLA_CARRITO="carrito";
    public static final String ID_MENU="idMenu";
    public static final String CAMPO_CANTIDAD="cantidad";

    public static final String CREAR_TABLA_CARRITO="CREATE TABLE " + ""+TABLA_CARRITO+" "+
            "("+CAMPO_ID+" "+"INTEGER primary key autoincrement, "
            +ID_MENU+" " +"INTEGER, "
            +CAMPO_NOMBRE+" " +"TEXT, "
            +CAMPO_CANTIDAD+" " +"INTEGER) ";
}

