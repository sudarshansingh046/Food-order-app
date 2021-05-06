package com.example.foody.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody.DBHandler;
import com.example.foody.R;
import com.example.foody.cart;
import com.example.foody.model.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>
{
    Context context;
    List<Cart> CartList;
    DBHandler db;
    public CartAdapter(Context context, List<Cart> CartList) {
        this.context = context;
        this.CartList = CartList;

    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cartitem, parent, false);
        return new CartAdapter.CartViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, final int position) {


        holder.name.setText(CartList.get(position).getName());
        holder.price.setText(CartList.get(position).getPrice());
        holder.foodImage.setImageResource(CartList.get(position).getImageUrl());
        holder.quantity.setText(CartList.get(position).getQuantity());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=new DBHandler(context);
                db.deleteRecord(String.valueOf(CartList.get(position).getImageUrl()));
                Intent i=new Intent(context, cart.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
            }
        });

    }
    @Override
    public int getItemCount() {
        return CartList.size();
    }


    public static final class CartViewHolder extends RecyclerView.ViewHolder {


        ImageView foodImage;
        TextView price, name,quantity;
        ImageButton delete;
        DBHandler db;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            foodImage = itemView.findViewById(R.id.food_image);
            quantity=itemView.findViewById(R.id.quantity);
            delete=itemView.findViewById(R.id.delete);



        }


    }
}
