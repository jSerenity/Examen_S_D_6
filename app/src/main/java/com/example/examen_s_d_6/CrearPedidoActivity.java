package com.example.examen_s_d_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examen_s_d_6.clases.CarAdapter;
import com.example.examen_s_d_6.clases.ProductAdapter;
import com.example.examen_s_d_6.clases.RecyclerViewOnItemClickListener;
import com.example.examen_s_d_6.clases.carrito;
import com.example.examen_s_d_6.clases.menu;
import com.example.examen_s_d_6.clases.pedidos;
import com.example.examen_s_d_6.tablas.tablas;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CrearPedidoActivity extends AppCompatActivity {
    List<carrito> carList;

    //the recyclerview
    RecyclerView recyclerView;
    ExtendedFloatingActionButton Boton;
    TextView textViewTotal;
    AppSQLiteOpenHepler conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pedido);
        conn= new AppSQLiteOpenHepler(this,"db_restaurant",null,1);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewTotal =(TextView) findViewById(R.id.textViewTotal);
        textViewTotal.setText("TOTAL: B/. "+String.format("%.2f",getCarSUM()));
        Boton =(ExtendedFloatingActionButton)findViewById(R.id.crearpedido);
        // add back arrow to toolbar
        Boton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                crearpedido();
            }
        });
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        int Id = getIntent().getIntExtra("VIEW_NAME",1);
        //initializing the productlist
        carList= getCarList();

        //creating recyclerview adapter
        CarAdapter adapter = new CarAdapter(this, carList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }

    private List<carrito> getCarList() {
        SQLiteDatabase db = conn.getWritableDatabase();
        List<carrito> result = new ArrayList<>();
        String query = "select COUNT("+ tablas.CAMPO_CANTIDAD+"), SUM("+ tablas.CAMPO_PRECIO+"), "+tablas.CAMPO_NOMBRE+ " FROM "+ tablas.TABLA_CARRITO+
                " GROUP BY "+ tablas.CAMPO_NOMBRE;
        try {
            Cursor c = db.rawQuery(query, null);
            if (c.moveToFirst()){
                do{
                    // Passing values
                    result.add(new carrito(c.getInt(0),c.getString(2),c.getFloat(1)));
                    // Do something Here with values
                    } while(c.moveToNext());
            }
            c.close();
        }catch (Exception e){
            String A =e.toString();
        }
        finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return result;
    }

    private  void crearpedido(){

            double suma =getCarSUM();

            if(suma>0){
                SQLiteDatabase db = conn.getWritableDatabase();
                try {
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                ContentValues values = new ContentValues();
                values.put(tablas.CAMPO_FECHA, currentDate+" "+currentTime);
                values.put(tablas.CAMPO_TOTAL, String.format("%.2f", suma));
                  /*  String insert="INSERT INTO " + tablas.TABLA_PEDIDOS + "( "+tablas.CAMPO_FECHA+ ","+tablas.CAMPO_TOTAL+") VALUES("
                            +"'"+currentDate+"', "+String.format("%.2f", suma)+")";
                   Cursor c = db.rawQuery(query, null);*/

                long id = db.insert(tablas.TABLA_PEDIDOS, tablas.CAMPO_ID,values);
                if(id>0){
                    Toast.makeText(getApplicationContext(),"Pedido #: "+id+" CREADO", Toast.LENGTH_SHORT).show();
                    deletecarrito();
                }else{
                    Toast.makeText(getApplicationContext(),"Pedido NO CREADO", Toast.LENGTH_SHORT).show();
                }


                }catch (Exception e){
                    String A =e.toString();
                }
                finally {
                    if (db != null && db.isOpen()) {
                        db.close();
                    }
                }

            }else{
                Toast.makeText(getApplicationContext(),"LISTA SIN ARTICULO ", Toast.LENGTH_SHORT).show();
            }
    }
    private double getCarSUM() {
        SQLiteDatabase db = conn.getWritableDatabase();
        double result = 0.0;
        String query = "select SUM("+ tablas.CAMPO_PRECIO+")  FROM "+ tablas.TABLA_CARRITO;
        try {
            Cursor c = db.rawQuery(query, null);
            if (c.moveToFirst()){
                    result=c.getFloat(0);
            }
            c.close();
        }catch (Exception e){
            String A =e.toString();
        }
        finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return result;
    }
    private double deletecarrito() {
        SQLiteDatabase db = conn.getWritableDatabase();
        double result = 0.0;
        String query = "DELETE FROM "+ tablas.TABLA_CARRITO;
        try {
            db.execSQL(query);
        }catch (Exception e){
            String A =e.toString();
        }
        finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return result;
    }
}