package tr.gov.acsgb.isgkatip.mvp.view.welcome.announcement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.common.ISGKatipSingleton;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.AnnouncementActivityModelImpl;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.dataModel.AnnouncementDataModel;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.requestModel.FilterList4Announcement;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.responseModel.Content4Announcement;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.responseModel.ResponseAnnouncementData;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.dataModel.NewsDataModel;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.requestModel.FilterList;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.responseModel.Content;
import tr.gov.acsgb.isgkatip.mvp.presenter.welcome.announcement.AnnouncementPresenterImpl;
import tr.gov.acsgb.isgkatip.mvp.presenter.welcome.news.NewsPresenterImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.announcement.adapter.AnnouncementsListViewAdapter;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.announcement.detail.AnnouncementsDetailActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.news.detail.NewsDetailActivityViewImpl;

public class AnnouncementActivityViewImpl extends AppCompatActivity implements IAnnouncementActivityView {

    /// Component≥
    ListView announcementsListView;
    AVLoadingIndicatorView avLoadingIndicatorView;

    /// Data
    List<AnnouncementDataModel> listOfAnnouncementData;

    // Request
    AnnouncementPresenterImpl announcementPresenter;

    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        initAnnouncementActivityComponent();

        getAnnouncementsData(0);

        clickAnnouncementsListViewItem();

    }

    //==============================================================================================
    @Override
    public void initAnnouncementActivityComponent() {
        getSupportActionBar().hide();

        announcementsListView = (ListView) findViewById(R.id.announcementListView);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);

        announcementPresenter = new AnnouncementPresenterImpl(new AnnouncementActivityModelImpl(), this);

    }

    //==============================================================================================
    @Override
    public void getAnnouncementsData(int page) {
        List<FilterList4Announcement> filterList4Announcements = new ArrayList<>();
        filterList4Announcements.add(new FilterList4Announcement("dataTpId", "751"));
        announcementPresenter.announcements(getApplicationContext(), page, 20, filterList4Announcements);
    }

    //==============================================================================================
    @Override
    public void fillAnnouncementsListData(List<Content4Announcement> listOfContentAnnouncements) {
        listOfAnnouncementData = new ArrayList<>();

        for(Content4Announcement content4Announcement : listOfContentAnnouncements){
            listOfAnnouncementData.add(new AnnouncementDataModel(content4Announcement.getUrl(),
                    "30/04/2019",
                    content4Announcement.getTitle(),
                    content4Announcement.getId(),
                    content4Announcement.getBody()));
        }

        bindAnnouncementsList2Adapter();

    }

    //==============================================================================================
    @Override
    public void bindAnnouncementsList2Adapter() {
        AnnouncementsListViewAdapter announcementsListViewAdapter = new AnnouncementsListViewAdapter(getApplicationContext(), listOfAnnouncementData);
        announcementsListView.setAdapter(announcementsListViewAdapter);
    }

    //==============================================================================================
    @Override
    public void clickAnnouncementsListViewItem() {
        announcementsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ISGKatipSingleton.getInstance().setAnnouncementDataModel(listOfAnnouncementData.get(position));
                startActivity(new Intent(getApplicationContext(), AnnouncementsDetailActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================
    @Override
    public void showLoading() {
        avLoadingIndicatorView.smoothToShow();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==============================================================================================
    @Override
    public void hideLoading() {
        avLoadingIndicatorView.smoothToHide();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==============================================================================================
    @Override
    public void showError() {
        Toast.makeText(this, "Error Announcements Data", Toast.LENGTH_SHORT).show();
    }

    //==============================================================================================
    @Override
    public void announcementData(ResponseAnnouncementData responseAnnouncementData) {
        fillAnnouncementsListData(responseAnnouncementData.getResultList4Announcement().getContent4Announcements());
    }

    //==============================================================================================
    @Override
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
    }

    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
