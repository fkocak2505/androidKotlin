package tr.gov.acsgb.isgkatip.mvp.view.welcome.selfEvaluate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.WelcomeActivityViewImpl;

public class SelfEvaluateActivityViewImpl extends AppCompatActivity implements ISelfEvaluateActivityView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_evaluate);

        initSelEvaluateActivityComponent();

    }

    @Override
    public void initSelEvaluateActivityComponent() {
        getSupportActionBar().hide();
    }

    @Override
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
    }
}

