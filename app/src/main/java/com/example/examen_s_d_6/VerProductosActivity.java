package com.example.examen_s_d_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.examen_s_d_6.clases.ProductAdapter;
import com.example.examen_s_d_6.clases.RecyclerViewOnItemClickListener;
import com.example.examen_s_d_6.clases.menu;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import com.example.examen_s_d_6.tablas.tablas;

public class VerProductosActivity extends AppCompatActivity {
    List<menu> productList;
    ExtendedFloatingActionButton addproduct;
    //the recyclerview
    RecyclerView recyclerView;
    AppSQLiteOpenHepler conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_productos);
        conn= new AppSQLiteOpenHepler(this,"db_restaurant",null,1);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        int Id = getIntent().getIntExtra("VIEW_NAME",1);
        //initializing the productlist
        productList= CreateMenuList(Id);

        //creating recyclerview adapter
        ProductAdapter adapter = new ProductAdapter(this, productList, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {

                menu arti = (menu) productList.get(position);

                if(addCar(arti)>0){
                    Toast.makeText(v.getContext(), "1 combo: "+arti.getNombre()+" agregado",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(v.getContext(), "no fue agregado",Toast.LENGTH_SHORT).show();
                }


            }
        });
        ExtendedFloatingActionButton buttonCrearpedido = (ExtendedFloatingActionButton) this.findViewById(R.id.crearpedidoVer);
        buttonCrearpedido.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(), getString(R.string.crearpedido),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(VerProductosActivity.this, CrearPedidoActivity.class);
                startActivity(intent);
            }
        });
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private List<menu> CreateMenuList(int id){
        List<menu> productosname = new ArrayList<>();
        if(id==1){
            productosname.add(
                    new menu(
                            1,
                            "sándwich de queso",
                            "pan tostado, con el queso bien derretido!",
                            2.90,
                            R.drawable.pan,"DESAYUNO"));

            productosname.add(
                    new menu(
                            2,
                            "Quesadillas",
                            "Pequeña pila de piezas quesadillas de tortilla de trigo cocida rellena con cebolla, tomate y hierbas en el papel de cera en el fondo blanco",
                            3.50,
                            R.drawable.queso,
                            "DESAYUNO"));
        }else
        if(id==2){
            productosname.add(
                    new menu(
                            3,
                            "Camarones con coco",
                            "Crujientes camarones con coco al horno... fáciles y ¡de limpieza rápida!",
                            10.30,
                            R.drawable.camarones,"ALMUERZO"));

            productosname.add(
                    new menu(
                            4,
                            "Carnes a la parrilla y verduras",
                            "Carne tierna a las brasas con verduras fresas",
                            8.50,
                            R.drawable.carne,
                            "ALMUERZO"));
        }else
        if(id==3){
            productosname.add(
                    new menu(
                            5,
                            "Fresh vegetable",
                            "Ensalada de verduras frescas y aceitunas en un plato!",
                            7.30,
                            R.drawable.ensalada,"ALMUERZO"));

            productosname.add(
                    new menu(
                            6,
                            "filete de pollo a la parrilla",
                            "Pollo a las brasas con verduras fresas",
                            6.50,
                            R.drawable.pollo,
                            "ALMUERZO"));
        }else
        if(id==4){
            productosname.add(
                    new menu(
                            7,
                            "Bizcochitos angelicales",
                            "Bizcochitos angelicales de vainilla!",
                            3.30,
                            R.drawable.bisco,"ALMUERZO"));

            productosname.add(
                    new menu(
                            8,
                            "Pumpkin Flan Cake",
                            "Descubre el Pastel de Flan de Calabaza",
                            1.50,
                            R.drawable.pastel,
                            "ALMUERZO"));
        }

        return productosname;
    }

    private long addCar(menu item)
    {   SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        long result=0;
        try {
            values.put(tablas.ID_MENU, item.getID());
            values.put(tablas.CAMPO_NOMBRE, item.getNombre().toString());
            values.put(tablas.CAMPO_CANTIDAD, 1);
            values.put(tablas.CAMPO_PRECIO, item.getPrecio());
            result= db.insert(tablas.TABLA_CARRITO, tablas.CAMPO_ID,values);
        }catch (Exception e){
            String A =e.toString();
        }
        db.close();
        return result;
    }
}