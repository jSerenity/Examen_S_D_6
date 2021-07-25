package com.example.examen_s_d_6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.examen_s_d_6.clases.LastPedidosAdapter;
import com.example.examen_s_d_6.clases.ProductAdapter;
import com.example.examen_s_d_6.clases.RecyclerViewOnItemClickListener;
import com.example.examen_s_d_6.clases.menu;
import com.example.examen_s_d_6.clases.pedidos;
import com.example.examen_s_d_6.tablas.tablas;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LastPedidosActivity extends Fragment {
    List<pedidos> pedidosList;
    //the recyclerview
    RecyclerView recyclerView;
    AppSQLiteOpenHepler conn;
    private static final String TEXT = "text";

    public static LastPedidosActivity newInstance(String text) {
        LastPedidosActivity frag = new LastPedidosActivity();

        Bundle args = new Bundle();
        args.putString(TEXT, text);
        frag.setArguments(args);

        return frag;
    };
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.activity_last_pedidos, container, false);
        conn= new AppSQLiteOpenHepler(layout.getContext(),"db_restaurant",null,1);

        recyclerView = (RecyclerView) layout.findViewById(R.id.recyclerViewLastPedidos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(layout.getContext()));

        pedidosList= getLastPedidosList();

        //creating recyclerview adapter
        LastPedidosAdapter adapter = new LastPedidosAdapter(layout.getContext(), pedidosList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
        return layout;
    }

    private List<pedidos> getLastPedidosList() {
        SQLiteDatabase db = conn.getWritableDatabase();
        List<pedidos> result = new ArrayList<>();
        String query = "select "+ tablas.CAMPO_ID+", "+ tablas.CAMPO_FECHA+", "+tablas.CAMPO_TOTAL+ " FROM "+ tablas.TABLA_PEDIDOS+" ORDER BY "+ tablas.CAMPO_ID;
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()){
            do {
                // Passing values
                result.add(new pedidos(c.getInt(0),c.getString(1),c.getFloat(2)));
                // Do something Here with values
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return result;
    }
}