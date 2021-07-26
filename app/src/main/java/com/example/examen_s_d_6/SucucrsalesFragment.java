package com.example.examen_s_d_6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.examen_s_d_6.clases.Sucursales;
import  com.example.examen_s_d_6.SucursalesAdapter;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SucucrsalesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SucucrsalesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listView;
    public SucucrsalesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SucucrsalesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SucucrsalesFragment newInstance(String param1, String param2) {
        SucucrsalesFragment fragment = new SucucrsalesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_sucucrsales, container, false);
        listView=layout.findViewById(R.id.listView);

        //create date
        ArrayList<Sucursales> arrayList= new ArrayList<>();
        arrayList.add(new Sucursales(R.drawable.tumbamuerto,"El Dorado","Dirección: Edif. Neptuno, local 7, Planta Baja, al lado de Plaza Aventura, Vía Ricardo J. Alfaro (Tumba Muerto)."));
        arrayList.add(new Sucursales(R.drawable.losandes,"PLAZA CAROLINA","Dirección: Edif. Los Álamos, local 10, Planta Baja, frente a la Sede de Cambio Democrático, Plaza Carolina, Ave. José Agustín Arango."));
        arrayList.add(new Sucursales(R.drawable.panamaoeste,"DAVID","Diagonal a la bomba Puma, ubicada frente al Hotel Nacional. Calle Dr. Pérez Balladares entre Ave. 2a Este y Ave 9 de Enero."));
        arrayList.add(new Sucursales(R.drawable.tumbamuerto,"CHITRE","Nos Mudamos!! Dirección: Calle Saturnino Rodríguez local 3963, a un costado de la ATTT frente a los estacionamientos de Rodelag"));

        //we make custom adapter

        SucursalesAdapter obj_adapter=new SucursalesAdapter(layout.getContext(),R.layout.layout_sucursales,arrayList);

        listView.setAdapter(obj_adapter);
        return layout;
    }
}