package com.example.foody;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody.adapter.CartAdapter;
import com.example.foody.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class cart extends AppCompatActivity {
    RecyclerView CartRecycler;
    CartAdapter cartAdapter;
    List<Cart> CartList;
    DBHandler db;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        Intent i=getIntent();
        btn=(Button)findViewById(R.id.chechout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(cart.this,checkout.class);
                startActivity(i);
            }
        });
        CartList=new ArrayList<>();

        try{
            DBHandler db=new DBHandler(this);
            SQLiteDatabase sb=db.getReadableDatabase();
            if(sb!=null)
            {
                Cursor cursor=sb.rawQuery("SELECT * FROM record",null);
                if(cursor.getCount()==0)
                {
                    Toast.makeText(this,"no item is selected",Toast.LENGTH_LONG).show();
                }
                else
                {

                    while (cursor.moveToNext())
                    {
                        CartList.add(new Cart(cursor.getString(0),cursor.getString(1),Integer.parseInt(cursor.getString(2)),cursor.getString(3)));

                    }
                    setCartRecycler(CartList);


                }
            }
            else
            {
                Toast.makeText(this,"database is null",Toast.LENGTH_LONG).show();
            }

        }
        catch (Exception e)
        {
            System.out.println(e);

        }

    }
    private void setCartRecycler(List<Cart> CartList) {

        CartRecycler = findViewById(R.id.cart);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        CartRecycler.setLayoutManager(layoutManager);
        cartAdapter = new CartAdapter(this, CartList);
        CartRecycler.setAdapter(cartAdapter);

    }
}
