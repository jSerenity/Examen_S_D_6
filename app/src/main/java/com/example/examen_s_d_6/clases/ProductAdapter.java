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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<menu> productList;

    //getting the context and product list with constructor
    public ProductAdapter(Context mCtx, List<menu> productList,  @NonNull RecyclerViewOnItemClickListener
            recyclerViewOnItemClickListener) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_products, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        menu product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getNombre());
        holder.textViewShortDesc.setText(product.getDescripcion());
        holder.textViewRating.setRating(Float.parseFloat("4.5"));
        holder.textViewPrice.setText("B/. "+String.valueOf(product.getPrecio()));
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImagen()));
        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recyclerViewOnItemClickListener != null) {
                    recyclerViewOnItemClickListener.onClick(view, position);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        RatingBar textViewRating;
        TextView textViewTitle, textViewShortDesc, textViewPrice;
        ImageView imageView;
        ExtendedFloatingActionButton buttonAdd;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
            buttonAdd= itemView.findViewById(R.id.add);
        }
    }
}
