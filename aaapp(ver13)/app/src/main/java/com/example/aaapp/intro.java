package com.example.aaapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class intro extends Font {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(intro.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);



    }
}
