package tr.gov.saglik.uets.mvp.view.announcements.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.announcements.detail.AnnouncementsDetailActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.announcements.responseModel.Response4AnnouncementsDetail;
import tr.gov.saglik.uets.mvp.model.announcements.responseModel.ValueOfAnnouncementsItem;
import tr.gov.saglik.uets.mvp.presenter.announcements.detail.AnnouncementsDetailActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.announcements.AnnouncementsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class AnnouncementsDetailActivityViewImpl extends AppCompatActivity implements IAnnouncementsDetailActivityView {

    /// Component
    TextView announcementsItemTitle;
    TextView announcementDetail;
    TextView announcementDetailCreateDate;
    AVLoadingIndicatorView avLoadingIndicatorView;
    ImageView homeScreen;
    ImageView goBack;

    /// Data
    ValueOfAnnouncementsItem valueOfAnnouncementsItem;
    
    /// Presenter
    AnnouncementsDetailActivityPresenterImpl announcementsDetailActivityPresenter;


    //==============================================================================================

    /**
     * onCreate Method..
     *
     * @param savedInstanceState
     */
    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements_detail);

        initAnnouncementsDetailComponent();

        getAnnounncementsDetailData();
        clickGoBack();
        //clickHomeScreen();
    }

    //==============================================================================================

    /**
     * Init Announcement Detail Data..
     */
    //==============================================================================================
    @Override
    public void initAnnouncementsDetailComponent() {
        getSupportActionBar().hide();

        announcementsItemTitle = (TextView) findViewById(R.id.announcementTitle);
        announcementDetail = (TextView) findViewById(R.id.announcementDetail);
        announcementDetailCreateDate = (TextView) findViewById(R.id.createDate);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);
        goBack = (ImageView) findViewById(R.id.goBack);
        //homeScreen = (ImageView) findViewById(R.id.homeScreen);

        announcementsDetailActivityPresenter = new AnnouncementsDetailActivityPresenterImpl(new AnnouncementsDetailActivityModelImpl(), this);
    }

    //==============================================================================================

    /**
     * Requeset Announcements Data...
     */
    //==============================================================================================
    @Override
    public void getAnnounncementsDetailData() {
        announcementsDetailActivityPresenter.getAnnouncementsDetail(UETSSingletonPattern.getInstance().getAnnouncementsId());
    }

    //==============================================================================================

    /**
     * Fill Title And Detail When Come to Data..
     *
     * @param valueOfAnnouncementsItem
     */
    //==============================================================================================
    @Override
    public void fillAnnouncementsData(ValueOfAnnouncementsItem valueOfAnnouncementsItem) {
        announcementsItemTitle.setText(valueOfAnnouncementsItem.getAnnouncement().getTitle());
        announcementDetail.setText(valueOfAnnouncementsItem.getAnnouncement().getDescription());
        announcementDetailCreateDate.setText(valueOfAnnouncementsItem.getAnnouncement().getCreateDate());

    }

    //==============================================================================================

    /**
     * Show Loading When request to API
     */
    //==============================================================================================
    @Override
    public void showLoading() {
        avLoadingIndicatorView.smoothToShow();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==============================================================================================

    /**
     * Hide Loading When come to data from API
     */
    //==============================================================================================
    @Override
    public void hideLoading() {
        avLoadingIndicatorView.smoothToHide();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==============================================================================================

    /**
     * Show Error When getting Error from API
     */
    //==============================================================================================
    @Override
    public void showError(String errorMsg) {
        new EZDialog.Builder(this)
                .setTitle("Hata..!")
                .setMessage(errorMsg)
                .setPositiveBtnText("Tamam")
                .setCancelableOnTouchOutside(true)
                .showTitleDivider(true)
                .setHeaderColor(getResources().getColor(R.color.loginActivityLoginbtnFirstColor))
                .setTitleTextColor(getResources().getColor(R.color.welcomeActivityUserNameColor))
                .OnPositiveClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {
                        startActivity(new Intent(getApplicationContext(), AnnouncementsActivityViewImpl.class));
                    }
                })
                .build();
    }

    //==============================================================================================

    /**
     * Handle Announcements Detail Data...
     *
     * @param response4AnnouncementsDetail
     */
    //==============================================================================================
    @Override
    public void sendData2ActivityView(Response4AnnouncementsDetail response4AnnouncementsDetail) {
        valueOfAnnouncementsItem = response4AnnouncementsDetail.getValue();
        fillAnnouncementsData(valueOfAnnouncementsItem);
    }

    //==============================================================================================

    /**
     * Go Back Before Activity
     */
    //==============================================================================================
    @Override
    public void clickGoBack() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AnnouncementsActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================

    /**
     * Go Back Before Activity
     */
    //==============================================================================================
    @Override
    public void clickHomeScreen() {
        homeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DemandsActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================

    /**
     * Go Back Before With Hardware Button
     *
     * @param keyCode
     * @param event
     * @return
     */
    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                startActivity(new Intent(getApplicationContext(), AnnouncementsActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

