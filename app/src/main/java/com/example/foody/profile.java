package com.example.foody;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class profile extends AppCompatActivity {
    ImageView img;
    EditText t1,t2,t3,t4;
    Button btn1,btn2;
    profiledata p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        getIntent();
        t1=(EditText)findViewById(R.id.textView3);
        t2=(EditText)findViewById(R.id.textView4);
        t3=(EditText)findViewById(R.id.textView5);
        t4=(EditText)findViewById(R.id.textView6);
        btn1=(Button)findViewById(R.id.button2);
        btn2=(Button)findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=t1.getText().toString();
                String email=t2.getText().toString();
                String mobile=t3.getText().toString();
                String address=t4.getText().toString();
                if(name.matches(""))
                {
                    Toast.makeText(profile.this, "You did not enter a name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(email.matches(""))
                {
                    Toast.makeText(profile.this, "You did not enter a email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mobile.matches(""))
                {
                    Toast.makeText(profile.this, "You did not enter a mobile no.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(address.matches(""))
                {
                    Toast.makeText(profile.this, "You did not enter a address", Toast.LENGTH_SHORT).show();
                    return;
                }
                profiledata p=new profiledata(profile.this);
                p.insertRecord(name,email,mobile,address);
                Intent i=new Intent(profile.this,ProfileActivity.class);

                startActivity(i);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profile.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}