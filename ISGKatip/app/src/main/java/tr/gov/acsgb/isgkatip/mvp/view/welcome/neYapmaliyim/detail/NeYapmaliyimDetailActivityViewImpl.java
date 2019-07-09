package tr.gov.acsgb.isgkatip.mvp.view.welcome.neYapmaliyim.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.common.ISGKatipSingleton;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.responseModel.ContentNeYapmaliyim;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.neYapmaliyim.NeYapmaliyimActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.news.NewsActivityViewImpl;

public class NeYapmaliyimDetailActivityViewImpl extends AppCompatActivity implements INeYapmaliyimDetailActivityView {

    // Component
    TextView neYapmaliyimDetailTitle;
    TextView neYapmaliyimDetailText;

    // Data
    ContentNeYapmaliyim contentNeYapmaliyimDetail;


    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ne_yapmaliyim_detail);

        initNeYapmaliyimDetailActivityComponent();

        fillNeYapmaliyimActivityDetailData();

    }

    //==============================================================================================
    @Override
    public void initNeYapmaliyimDetailActivityComponent() {
        getSupportActionBar().hide();

        neYapmaliyimDetailTitle = (TextView) findViewById(R.id.neYapmaliyimDetailTitle);
        neYapmaliyimDetailText = (TextView) findViewById(R.id.neYapmaliyimDetailText);

        contentNeYapmaliyimDetail = ISGKatipSingleton.getInstance().getContentNeYapmaliyim();

    }

    //==============================================================================================
    @Override
    public void fillNeYapmaliyimActivityDetailData() {
        neYapmaliyimDetailTitle.setText(contentNeYapmaliyimDetail.getTitle());
        neYapmaliyimDetailText.setText(contentNeYapmaliyimDetail.getBody());
    }

    //==============================================================================================
    @Override
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), NeYapmaliyimActivityViewImpl.class));
    }

    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), NeYapmaliyimActivityViewImpl.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
