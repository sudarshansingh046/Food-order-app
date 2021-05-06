package com.example.foody;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class profiledis extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    Button btn;
    profiledata p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiledis);
        t1 = (TextView) findViewById(R.id.textView3);
        t2 = (TextView) findViewById(R.id.textView4);
        t3 = (TextView) findViewById(R.id.textView5);
        t4 = (TextView) findViewById(R.id.textView6);
        btn = (Button) findViewById(R.id.button);
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
                System.out.println(cursor.getString(3));
            db.close();
        }
        catch (Exception e)
        {
System.out.println(e);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profiledis.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}
