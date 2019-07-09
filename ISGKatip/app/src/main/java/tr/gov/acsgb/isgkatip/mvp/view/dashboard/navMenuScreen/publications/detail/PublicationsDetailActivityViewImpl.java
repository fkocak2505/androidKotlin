package tr.gov.acsgb.isgkatip.mvp.view.dashboard.navMenuScreen.publications.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.common.ISGKatipSingleton;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.dataModel.PublicationsDataModel;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.DashboardActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.navMenuScreen.publications.PublicationsActivityViewImpl;

public class PublicationsDetailActivityViewImpl extends AppCompatActivity implements IPublicationsDetailActivityView {

    // Component
    TextView publicationsTitle;
    ImageView publicationsImage;
    TextView publicationsDetail;

    // News Data
    PublicationsDataModel publicationsDataModel;

    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications_detail);

        initPublicationsDetailActivityComponent();

        setPublicationsDetailData();

    }

    //==============================================================================================
    @Override
    public void initPublicationsDetailActivityComponent() {
        getSupportActionBar().hide();

        publicationsTitle = (TextView) findViewById(R.id.publicationsTitle);
        publicationsImage = (ImageView) findViewById(R.id.publicationsImage);
        publicationsDetail = (TextView) findViewById(R.id.publicationsDetail);

        publicationsDataModel = ISGKatipSingleton.getInstance().getPublicationsDataModel();
    }

    //==============================================================================================
    @Override
    public void setPublicationsDetailData() {
        publicationsTitle.setText(publicationsDataModel.getPublicationsTitle());
        Picasso.get()
                .load(publicationsDataModel.getPublicationsImage())
                .resize(350, 200)
                .into(publicationsImage);
        publicationsDetail.setText(publicationsDataModel.getPublicationsDetail());
    }

    //==============================================================================================
    @Override
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), PublicationsActivityViewImpl.class));
    }

    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), PublicationsActivityViewImpl.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
