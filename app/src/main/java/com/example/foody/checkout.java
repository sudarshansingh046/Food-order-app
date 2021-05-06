package com.example.foody;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody.adapter.CartAdapter;
import com.example.foody.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class checkout extends AppCompatActivity {
    RecyclerView CartRecycler;
    CartAdapter cartAdapter;
    List<Cart> CartList;
    TextView t1,t2,t3,t4,t5;
    Button btn;
    profiledata p;
    String out="";
    String ad="";
    Float total= Float.valueOf(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);
        getIntent();
        t1 = (TextView) findViewById(R.id.textView3);
        t2 = (TextView) findViewById(R.id.textView4);
        t3 = (TextView) findViewById(R.id.textView5);
        t4 = (TextView) findViewById(R.id.textView6);
        t5=(TextView)findViewById(R.id.total);
        btn = (Button) findViewById(R.id.payment);


        CartList=new ArrayList<>();
        try {

            String query = "SELECT * FROM record";
            profiledata p=new profiledata(this);
            SQLiteDatabase db = p.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToLast();
            t1.setText(cursor.getString(0));
            t2.setText(cursor.getString(1));
            t3.setText(cursor.getString(2));
            t4.setText(cursor.getString(3));
            ad="NAME-"+cursor.getString(0)+"\nEMAIL-"+cursor.getString(1)+"\nMOBILE-"+cursor.getString(2)+"\nAddress-"+cursor.getString(3)+"\n";
            db.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

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
                        out=out+cursor.getString(0)+"   "+cursor.getString(1)+"   "+cursor.getString(3)+"\n";
                        total=total+Float.parseFloat(cursor.getString(1));

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
        t5.setText(String.valueOf(total));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String toNumber = "+91 70141 74424";
                toNumber = toNumber.replace("+", "").replace(" ", "");

                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.putExtra("jid", toNumber + "@s.whatsapp.net");
                sendIntent.putExtra(Intent.EXTRA_TEXT, ad+out+"\n total  "+total);
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }
    private void setCartRecycler(List<Cart> CartList) {

        CartRecycler = findViewById(R.id.cart);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        CartRecycler.setLayoutManager(layoutManager);
        cartAdapter = new CartAdapter(this, CartList);
        CartRecycler.setAdapter(cartAdapter);

    }
}
