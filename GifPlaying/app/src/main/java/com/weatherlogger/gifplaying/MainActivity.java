package com.weatherlogger.gifplaying;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView gifPlaying = findViewById(R.id.gifLab);

        Glide.with(getApplicationContext()).load(R.drawable.loading).into(gifPlaying);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                gifPlaying.setVisibility(View.INVISIBLE);
            }
        }, 10000);
    }
}
