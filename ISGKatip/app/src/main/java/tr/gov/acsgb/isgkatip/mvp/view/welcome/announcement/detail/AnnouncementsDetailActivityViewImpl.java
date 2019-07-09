package tr.gov.acsgb.isgkatip.mvp.view.welcome.announcement.detail;

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
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.dataModel.AnnouncementDataModel;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.announcement.AnnouncementActivityViewImpl;

public class AnnouncementsDetailActivityViewImpl extends AppCompatActivity implements IAnnouncementsDetailActivityView {

    // Component
    TextView announcementTitle;
    ImageView announcementImage;
    TextView announcementDetail;

    // Announcement Data
    AnnouncementDataModel announcementDataModel;

    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_detail);

        initAnnouncementsDetailActivityComponent();

        setAnnouncementsDetailData();
    }

    //==============================================================================================
    @Override
    public void initAnnouncementsDetailActivityComponent() {
        getSupportActionBar().hide();

        announcementTitle = (TextView) findViewById(R.id.announcementTitle);
        announcementImage = (ImageView) findViewById(R.id.announcementImage);
        announcementDetail = (TextView) findViewById(R.id.announcementDetail);

        announcementDataModel = ISGKatipSingleton.getInstance().getAnnouncementDataModel();
    }

    //==============================================================================================
    @Override
    public void setAnnouncementsDetailData() {
        announcementTitle.setText(announcementDataModel.getAnnouncementTitle());
        Picasso.get()
                .load(announcementDataModel.getAnnouncementImage())
                .resize(350,200)
                .into(announcementImage);
        announcementDetail.setText(announcementDataModel.getAnnouncementDetail());
    }

    //==============================================================================================
    @Override
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), AnnouncementActivityViewImpl.class));
    }

    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), AnnouncementActivityViewImpl.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
