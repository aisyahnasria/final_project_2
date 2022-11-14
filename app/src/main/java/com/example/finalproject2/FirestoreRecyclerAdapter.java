package com.example.finalproject2;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FirestoreRecyclerAdapter extends RecyclerView.Adapter<FirestoreRecyclerAdapter.ViewHolder> {
    private ArrayList<Products> productsArrayList;
    private Context context;
    public static final String ITEM_EXTRA = "product";

    public FirestoreRecyclerAdapter (ArrayList<Products> productsArrayList, Context context){
        this.productsArrayList = productsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FirestoreRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.product_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Products products = productsArrayList.get(position);
        holder.productName.setText(products.getName());
        holder.productQty.setText(String.valueOf(products.getQty()));
        Picasso.get().load(products.getImage()).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productName;
        private TextView productQty;
        private ImageView productImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.pName);
            productQty = itemView.findViewById(R.id.pQty);
            productImage = itemView.findViewById(R.id.imageView3);

        }
    }


}
