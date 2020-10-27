package com.weatherlogger.sentry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import io.sentry.core.Sentry;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] suit = {
                "spades",
                "hearts",
                "diamonds",
                "clubs"
        };

        try {
            Toast.makeText(getApplicationContext(), suit[5], Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Sentry.captureException(e);
        }
    }

    /*public void aaa(){
        try {

            throw new Exception("This is a test. awfawfawf");
        } catch (Exception e) {


            Sentry.captureException(e);
        }
    }*/

}
