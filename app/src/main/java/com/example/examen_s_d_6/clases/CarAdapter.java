package com.example.examen_s_d_6.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen_s_d_6.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<carrito> carList;
    public CarAdapter(Context mCtx, List<carrito> carList) {
        this.mCtx = mCtx;
        this.carList = carList;
    }
    //getting the context and product list with constructor
    public CarAdapter(Context mCtx, List<carrito> carList,  @NonNull RecyclerViewOnItemClickListener
            recyclerViewOnItemClickListener) {
        this.mCtx = mCtx;
        this.carList = carList;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_crear_pedido, null);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        //getting the product of the specified position
        carrito product = carList.get(position);

        //binding the data with the viewholder views
        holder.textViewNombre.setText(product.getNombre());
        holder.textViewCantidad.setText(String.valueOf(product.getCantidad()));
        holder.textViewTotal.setText(String.valueOf(product.getTotal()));
       /* holder.textViewPrice.setText("B/. "+String.valueOf(product.getPrecio()));
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImagen()));
        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recyclerViewOnItemClickListener != null) {
                    recyclerViewOnItemClickListener.onClick(view, position);
                }
            }
        });*/

    }


    @Override
    public int getItemCount() {
        return carList.size();
    }


    class CarViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre, textViewTotal, textViewCantidad;

        public CarViewHolder(View itemView) {
            super(itemView);

            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewTotal = itemView.findViewById(R.id.textViewTotal);
            textViewCantidad = itemView.findViewById(R.id.textViewCantidad);
        }
    }
}
