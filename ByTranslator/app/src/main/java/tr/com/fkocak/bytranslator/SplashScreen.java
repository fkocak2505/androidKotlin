package tr.com.fkocak.bytranslator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import tr.com.fkocak.bytranslator.common.UUIDGenerator;

public class SplashScreen extends AppCompatActivity {

    ImageView splashGif;
    SharedPreferences4Setting sharedPreferences4Setting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences4Setting = new SharedPreferences4Setting(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!checkHasAppKey()) startActivity(new Intent(getApplicationContext(), ShowApplicationIntro.class));
                //else startActivity(new Intent(getApplicationContext(), MainActivity.class));
                else startActivity(new Intent(getApplicationContext(), LoginDashboardActivity.class));
            }
        }, 3000);

        splashGif = (ImageView) findViewById(R.id.splashGif);
        Glide.with(getApplicationContext()).load(R.raw.witranslate).into(splashGif);

    }

    public boolean checkHasAppKey() {
        String appKey = new SharedPreferences4Setting(getApplicationContext()).getString1("APP_KEY");
        if (appKey.equals("NO_KEY")) {
            saveAppKey4SharedPref();
            return false;
        }
        return true;
    }

    public void saveAppKey4SharedPref() {
        String uuid = new UUIDGenerator().generateUUID();
        new SharedPreferences4Setting(getApplicationContext()).putString("APP_KEY", uuid);
    }
}
