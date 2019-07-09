package tr.gov.acsgb.isgkatip.mvp.view.welcome.nasilYaparim.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.common.ISGKatipSingleton;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.responseModel.ContentNasilYaparim;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.nasilYaparim.NasilYaparimActivityViewImpl;

public class NasilYaparimDetailActivityViewImpl extends AppCompatActivity implements INasilYaparimDetailActivityView {

    // Component
    TextView nasilYaparimDetailTitle;
    TextView nasilYaparimDetailText;

    // Data
    ContentNasilYaparim contentNasilYaparim;

    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasil_yaparim_detail);

        initNasilYaparimDetailActivityComponent();

        fillNasilYaparimActivityDetailData();
    }

    //==============================================================================================
    @Override
    public void initNasilYaparimDetailActivityComponent() {
        getSupportActionBar().hide();

        nasilYaparimDetailTitle = (TextView) findViewById(R.id.nasilYaparimDetailTitle);
        nasilYaparimDetailText = (TextView) findViewById(R.id.nasilYaparimDetailText);

        contentNasilYaparim = ISGKatipSingleton.getInstance().getContentNasilYaparim();
    }

    //==============================================================================================
    @Override
    public void fillNasilYaparimActivityDetailData() {
        nasilYaparimDetailTitle.setText(contentNasilYaparim.getTitle());
        nasilYaparimDetailText.setText(contentNasilYaparim.getBody());
    }

    //==============================================================================================
    @Override
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), NasilYaparimActivityViewImpl.class));
    }

    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), NasilYaparimActivityViewImpl.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
