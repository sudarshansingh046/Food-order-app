package com.example.foody;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    ImageButton img,img1,img2,img3;
    ImageView img4;
    TextView t1,t2,t3,t4;
    DBHandler db;
    int count=1;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_details);
        final Intent i=getIntent();
        final String name=i.getStringExtra("name");
        final String price=i.getStringExtra("price");
        String rating=i.getStringExtra("rating");
        final float p=Float.valueOf(price);

        img=(ImageButton)findViewById(R.id.imageView12);
        img1=(ImageButton)findViewById(R.id.imageView5);
        img2=(ImageButton)findViewById(R.id.imageView10);
        img3=(ImageButton)findViewById(R.id.imageView11);
        img4=(ImageView)findViewById(R.id.imageView8);
        t1=(TextView)findViewById(R.id.textView13);
        t2=(TextView)findViewById(R.id.textView14);
        t3=(TextView)findViewById(R.id.textView11);
        t4=(TextView)findViewById(R.id.textView3);
        t3.setText(name);
        t2.setText(price);
        t1.setText("1");
        t4.setText(rating);
        final Bundle b=getIntent().getExtras();
        if(b !=null) {
            int image = b.getInt("image");
            img4.setImageResource(image);
        }
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DetailsActivity.this,ProfileActivity.class);
                startActivity(i);
            }
        });

            img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(count==1)
                    {
                        t1.setText(String.valueOf(1));
                        t2.setText(String.valueOf(count*p));
                    }
                    else {
                        count = count - 1;
                        t1.setText(String.valueOf(count));
                        t2.setText(String.valueOf(count*p));
                    }
                }
            });
            img3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(count==10)
                    {
                        t1.setText(String.valueOf(10));
                        t2.setText(String.valueOf(count*p));
                    }
                    else {
                        count = count + 1;
                        t1.setText(String.valueOf(count));
                        t2.setText(String.valueOf(count*p));
                    }
                }
            });
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name=i.getStringExtra("name");
                    Integer integer=i.getIntExtra("image",1);
                    String image=String.valueOf(integer);
                    String price=t2.getText().toString();
                    String quantity=t1.getText().toString();
                    db=new DBHandler(getApplicationContext());
                    db.insertRecord(name, price, image, quantity);

                    Intent i=new Intent(DetailsActivity.this, com.example.foody.cart.class);
                    i.putExtra("name",name);
                    i.putExtra("price",t2.getText());
                    i.putExtra("image",integer);
                    i.putExtra("quantity",t1.getText());
                    startActivity(i);
                    finish();

                }
            });


    }
}
