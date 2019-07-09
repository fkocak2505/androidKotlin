package tr.gov.saglik.uets.mvp.view.announcements;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.announcements.AnnouncementsActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.announcements.dataModel.AnnouncementsDataModel;
import tr.gov.saglik.uets.mvp.model.announcements.responseModel.Response4Announcements;
import tr.gov.saglik.uets.mvp.model.announcements.responseModel.ValueOfAnnouncements;
import tr.gov.saglik.uets.mvp.presenter.announcements.AnnouncementsActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.announcements.adapter.AnnouncementsListViewAdapter;
import tr.gov.saglik.uets.mvp.view.announcements.detail.AnnouncementsDetailActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class AnnouncementsActivityViewImpl extends AppCompatActivity implements IAnnouncementActivityView {

    /// Component
    ListView announcementsListView;
    AVLoadingIndicatorView avLoadingIndicatorView;
    ImageView goBack;

    /// Data Model
    List<AnnouncementsDataModel> listOfAnnouncementData;
    List<ValueOfAnnouncements> valueOfAnnouncement;

    /// Announcements ListView Adapter
    AnnouncementsListViewAdapter announcementsListViewAdapter;

    /// Presenter Impl
    AnnouncementsActivityPresenterImpl announcementsActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        initAnnouncementActivityComponent();

        getAnnounncementsList();
        clickAnnouncementsListItem();
        clickGoBack();
    }

    //==============================================================================================

    /**
     * Init Announcements Activity Component
     */
    //==============================================================================================
    @Override
    public void initAnnouncementActivityComponent() {
        getSupportActionBar().hide();

        announcementsListView = (ListView) findViewById(R.id.announcementsListView);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);
        goBack = (ImageView) findViewById(R.id.goBack);

        announcementsActivityPresenter = new AnnouncementsActivityPresenterImpl(new AnnouncementsActivityModelImpl(), this);

    }

    //==============================================================================================

    /**
     * Request 4 Announcements Lists
     */
    //==============================================================================================
    @Override
    public void getAnnounncementsList() {
        announcementsActivityPresenter.getAnnouncementsList();
    }

    //==============================================================================================

    /**
     * Click ListView Item...
     */
    //==============================================================================================
    @Override
    public void clickAnnouncementsListItem() {
        announcementsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = listOfAnnouncementData.get(position).getAnnouncementsItemTitle();
                for (ValueOfAnnouncements itemOfAnnouncemements : valueOfAnnouncement) {
                    if (itemOfAnnouncemements.getTitle().equals(title))
                        UETSSingletonPattern.getInstance().setAnnouncementsId(itemOfAnnouncemements.getId());
                }

                startActivity(new Intent(getApplicationContext(), AnnouncementsDetailActivityViewImpl.class));

            }
        });
    }

    //==============================================================================================

    /**
     * Fill ListView with Announcement Data..
     *
     * @param listOfAnnouncementsValue
     */
    //==============================================================================================
    @Override
    public void fillAnnouncemenetListViewData(List<ValueOfAnnouncements> listOfAnnouncementsValue) {
        listOfAnnouncementData = new ArrayList<>();

        for (ValueOfAnnouncements itemOfAnnouncements : listOfAnnouncementsValue) {
            listOfAnnouncementData.add(new AnnouncementsDataModel(R.drawable.decisions_icon, itemOfAnnouncements.getTitle(), itemOfAnnouncements.getCreateDate()));
        }


        announcementsListViewAdapter = new AnnouncementsListViewAdapter(getApplicationContext(), listOfAnnouncementData);
        announcementsListView.setAdapter(announcementsListViewAdapter);

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
                        startActivity(new Intent(getApplicationContext(), DemandsActivityViewImpl.class));
                    }
                })
                .build();
    }

    //==============================================================================================

    /**
     * Handle Announcements Data..
     *
     * @param response4Announcements
     */
    //==============================================================================================
    @Override
    public void sendData2ActivityView(Response4Announcements response4Announcements) {
        valueOfAnnouncement = response4Announcements.getValue();
        fillAnnouncemenetListViewData(valueOfAnnouncement);
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
                if (UETSSingletonPattern.getInstance().getActivityname().equals("WELCOME"))
                    startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
                else if (UETSSingletonPattern.getInstance().getActivityname().equals("NAV_MENU"))
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
                if (UETSSingletonPattern.getInstance().getActivityname().equals("WELCOME"))
                    startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
                else if (UETSSingletonPattern.getInstance().getActivityname().equals("NAV_MENU"))
                    startActivity(new Intent(getApplicationContext(), DemandsActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
