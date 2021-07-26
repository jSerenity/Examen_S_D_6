package com.example.examen_s_d_6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.examen_s_d_6.clases.Sucursales;

import java.util.ArrayList;

public class SucursalesAdapter extends ArrayAdapter<Sucursales> {
    private Context mContext;
    private int mResource;
    //I WNATTO USE ARRAYlIST
    public SucursalesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Sucursales> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mResource=resource;


    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater. from(mContext);
        convertView=layoutInflater.from(getContext()).inflate(mResource,parent,  false );

        ImageView imageView=convertView.findViewById(R.id.image);
        TextView txtName=convertView.findViewById(R.id.txtName);

        TextView txtSub= convertView.findViewById(R.id.txtSub);

        imageView.setImageResource(getItem(position).getImage());
        txtName.setText(getItem(position).getName());
        txtSub.setText(getItem(position).getDes());
        return convertView;
    }
}
