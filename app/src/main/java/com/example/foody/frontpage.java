package com.example.foody;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class frontpage extends AppCompatActivity {
    ImageView img,img1;
    ProgressBar simpleProgressBar;
    int progress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frontpage);
        simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        img=(ImageView)findViewById(R.id.img);
        img.setImageResource(R.drawable.popularfood1);
        setProgressValue(progress);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(frontpage.this,MainActivity.class));
                finish();
            }
        },10000);


    }

    private void setProgressValue(final int progress) {

        // set the progress
        simpleProgressBar.setProgress(progress);
        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progress + 10);
            }
        });
        thread.start();
    }

}
