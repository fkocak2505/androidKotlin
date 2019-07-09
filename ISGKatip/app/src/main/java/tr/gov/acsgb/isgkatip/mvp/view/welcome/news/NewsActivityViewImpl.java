package tr.gov.acsgb.isgkatip.mvp.view.welcome.news;

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
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.NewsActivityModelImpl;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.dataModel.NewsDataModel;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.requestModel.FilterList;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.responseModel.Content;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.responseModel.ResponseNewsData;
import tr.gov.acsgb.isgkatip.mvp.presenter.welcome.news.NewsPresenterImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.news.adapter.NewsListViewAdapter;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.news.detail.NewsDetailActivityViewImpl;

public class NewsActivityViewImpl extends AppCompatActivity implements INewsActivityView {

    /// Component≥
    ListView newsListView;
    AVLoadingIndicatorView avLoadingIndicatorView;

    /// Data
    List<NewsDataModel> listOfNewsData;

    // Request
    NewsPresenterImpl newsPresenter;

    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initNewsActivityComponent();

        getNewsData(0);

        clickNewsListViewItem();

    }

    //==============================================================================================
    @Override
    public void initNewsActivityComponent() {
        getSupportActionBar().hide();
        newsListView = (ListView) findViewById(R.id.newsListView);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);

        newsPresenter = new NewsPresenterImpl(new NewsActivityModelImpl(), this);
    }

    //==============================================================================================
    @Override
    public void getNewsData(int page) {
        List<FilterList> filterLists = new ArrayList<>();
        filterLists.add(new FilterList("dataTpId","750"));
        newsPresenter.news( getApplicationContext(),page,20, filterLists);
    }

    //==============================================================================================
    @Override
    public void fillNewsListData(List<Content> contents) {
        listOfNewsData = new ArrayList<>();

        for(Content content : contents){
            listOfNewsData.add(new NewsDataModel(content.getUrl(),
                    "29/04/2019",
                    content.getTitle(),
                    content.getId(),
                    content.getBody()));
        }

        bindNewsList2Adapter();

    }

    //==============================================================================================
    @Override
    public void bindNewsList2Adapter() {
        NewsListViewAdapter newsListViewAdapter = new NewsListViewAdapter(getApplicationContext(),listOfNewsData);
        newsListView.setAdapter(newsListViewAdapter);
    }

    //==============================================================================================
    @Override
    public void clickNewsListViewItem() {
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ISGKatipSingleton.getInstance().setNewsDataModel(listOfNewsData.get(position));
                startActivity(new Intent(getApplicationContext(), NewsDetailActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================
    @Override
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
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
        Toast.makeText(this, "Error News Data", Toast.LENGTH_SHORT).show();
    }

    //==============================================================================================
    @Override
    public void newsData(ResponseNewsData responseNewsData) {
        fillNewsListData(responseNewsData.getResultList().getContent());
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
