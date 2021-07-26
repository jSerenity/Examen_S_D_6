package com.example.examen_s_d_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.examen_s_d_6.clases.CarAdapter;
import com.example.examen_s_d_6.clases.ProductAdapter;
import com.example.examen_s_d_6.clases.RecyclerViewOnItemClickListener;
import com.example.examen_s_d_6.clases.carrito;
import com.example.examen_s_d_6.clases.menu;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

public class CrearPedidoActivity extends AppCompatActivity {
    List<carrito> carList;

    //the recyclerview
    RecyclerView recyclerView;
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

        // add back arrow to toolbar
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

    }
}