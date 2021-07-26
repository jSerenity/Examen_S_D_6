package com.example.examen_s_d_6;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.examen_s_d_6.tablas.tablas;
import com.example.examen_s_d_6.tablas.imagenes;

public class AppSQLiteOpenHepler extends SQLiteOpenHelper {


    public AppSQLiteOpenHepler(Context context, String DATABASE_NAME, SQLiteDatabase.CursorFactory factory, int DATABASE_VERSION) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(tablas.CREAR_TABLA_USUARIOS);
        db.execSQL(tablas.CREAR_TABLA_MENU);
        db.execSQL(tablas.CREAR_TABLA_CARRITO);
       // db.execSQL(tablas.CREAR_TABLA_CATE);
        db.execSQL(tablas.CREAR_TABLA_PEDIDO);
        //creatData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + tablas.CREAR_TABLA_USUARIOS);
            db.execSQL("DROP TABLE IF EXISTS " + tablas.CREAR_TABLA_MENU);
            db.execSQL("DROP TABLE IF EXISTS " + tablas.CREAR_TABLA_CARRITO);
            //db.execSQL("DROP TABLE IF EXISTS " + tablas.CREAR_TABLA_CATE);
            db.execSQL("DROP TABLE IF EXISTS " + tablas.CREAR_TABLA_PEDIDO);
            onCreate(db);
        } catch (SQLException e) {
            // exepciones
        }
    }

    private void creatData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(tablas.CAMPO_NOMBRE, "Camarones con coco");
        values.put(tablas.CAMPO_PRECIO, 10.99);
        values.put(tablas.CAMPO_DECS, "Crujientes camarones con coco al horno... fáciles y ¡de limpieza rápida!");
        values.put(tablas.CAMPO_TYPO, "ALMUERZO");
        values.put(tablas.CAMPO_IMG, imagenes.camarones);
        long id = db.insert(tablas.TABLA_MENU, tablas.CAMPO_ID, values);
    }
}