package tr.gov.acsgb.isgkatip.mvp.view.dashboard.navMenuScreen.publications;

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
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.PublicationsActivityModelImpl;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.dataModel.PublicationsDataModel;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.requestModel.FilterList4Publications;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.responseModel.Content4Publications;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.responseModel.ResponsePublicationsData;
import tr.gov.acsgb.isgkatip.mvp.presenter.dashboard.navMenuScreen.publications.PublicationsPresenterImpl;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.DashboardActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.navMenuScreen.publications.adapter.PublicationsListViewAdapter;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.navMenuScreen.publications.detail.PublicationsDetailActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.news.detail.NewsDetailActivityViewImpl;

public class PublicationsActivityViewImpl extends AppCompatActivity implements IPublicationsActivityView {

    /// Component
    ListView publicationsListView;
    AVLoadingIndicatorView avLoadingIndicatorView;

    /// Data
    List<PublicationsDataModel> listOfPublicationsData;

    // Request
    PublicationsPresenterImpl publicationsPresenter;

    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications);

        initPublicationsActivityComponent();

        getPublicationsData(0);

        clickPublicationsListViewItem();
    }

    //==============================================================================================
    @Override
    public void initPublicationsActivityComponent() {
        getSupportActionBar().hide();

        publicationsListView = (ListView) findViewById(R.id.publicationsListView);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);

        publicationsPresenter = new PublicationsPresenterImpl(new PublicationsActivityModelImpl(), this);
    }

    //==============================================================================================
    @Override
    public void getPublicationsData(int page) {
        List<FilterList4Publications> filterList4Publications = new ArrayList<>();
        filterList4Publications.add(new FilterList4Publications("dataTpId", "755"));
        publicationsPresenter.publications(getApplicationContext(), page, 20, filterList4Publications);
    }

    //==============================================================================================
    @Override
    public void publicationsData(ResponsePublicationsData responsePublicationsData) {
        fillPublicationsListViewData(responsePublicationsData.getResultList4Publications().getContent4Publications());
    }

    //==============================================================================================
    @Override
    public void fillPublicationsListViewData(List<Content4Publications> listOfContent) {
        listOfPublicationsData = new ArrayList<>();

        for (Content4Publications content4Publications : listOfContent) {
            listOfPublicationsData.add(new PublicationsDataModel(content4Publications.getUrl(),
                    "30/04/2019",
                    content4Publications.getTitle(),
                    content4Publications.getId(),
                    content4Publications.getBody()));
        }

        bindPublicationsListData2ListView();

    }

    //==============================================================================================
    @Override
    public void bindPublicationsListData2ListView() {
        PublicationsListViewAdapter publicationsListViewAdapter = new PublicationsListViewAdapter(getApplicationContext(), listOfPublicationsData);
        publicationsListView.setAdapter(publicationsListViewAdapter);
    }

    //==============================================================================================
    @Override
    public void clickPublicationsListViewItem() {
        publicationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ISGKatipSingleton.getInstance().setPublicationsDataModel(listOfPublicationsData.get(position));
                startActivity(new Intent(getApplicationContext(), PublicationsDetailActivityViewImpl.class));
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
        Toast.makeText(this, "Error Publications Data..", Toast.LENGTH_SHORT).show();
    }

    //==============================================================================================
    @Override
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), DashboardActivityViewImpl.class));
    }

    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), DashboardActivityViewImpl.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
