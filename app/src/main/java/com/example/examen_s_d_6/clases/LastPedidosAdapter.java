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

public class LastPedidosAdapter extends RecyclerView.Adapter<LastPedidosAdapter.LastPedidosViewHolder> {

    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<pedidos> pedidosList;

    //getting the context and product list with constructor
    public LastPedidosAdapter(Context mCtx, List<pedidos> pedidosList) {
        this.mCtx = mCtx;
        this.pedidosList = pedidosList;
    }
    public LastPedidosAdapter(Context mCtx, List<pedidos> pedidosList,  @NonNull RecyclerViewOnItemClickListener
            recyclerViewOnItemClickListener) {
        this.mCtx = mCtx;
        this.pedidosList = pedidosList;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public LastPedidosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_products, null);
        return new LastPedidosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LastPedidosViewHolder holder, int position) {
        //getting the product of the specified position
        pedidos product = pedidosList.get(position);

        //binding the data with the viewholder views
        holder.textViewNumero.setText(product.getID());
        holder.textViewFecha.setText(product.getFecha());
        holder.textViewTotal.setText("B/. "+String.valueOf(product.getTotal()));  

      /*  holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
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
        return pedidosList.size();
    }


    class LastPedidosViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumero, textViewFecha, textViewTotal;

        public LastPedidosViewHolder(View itemView) {
            super(itemView);

            textViewNumero = itemView.findViewById(R.id.textViewNumero);
            textViewFecha = itemView.findViewById(R.id.textViewFecha);
            textViewTotal = itemView.findViewById(R.id.textViewTotalPedido);

        }
    }
}
