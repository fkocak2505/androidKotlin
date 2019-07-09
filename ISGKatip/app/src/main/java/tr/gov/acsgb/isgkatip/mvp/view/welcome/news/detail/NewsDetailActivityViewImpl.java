package tr.gov.acsgb.isgkatip.mvp.view.welcome.news.detail;

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
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.dataModel.NewsDataModel;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.news.NewsActivityViewImpl;

public class NewsDetailActivityViewImpl extends AppCompatActivity implements INewsDetailActivityView {

    // Component
    TextView newsTitle;
    ImageView newsImage;
    TextView newsDetail;

    // News Data
    NewsDataModel newsDataModel;

    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        initNewsDetailActivityComponent();

        setNewsDetailData();

    }

    //==============================================================================================
    @Override
    public void initNewsDetailActivityComponent() {
        getSupportActionBar().hide();

        newsTitle = (TextView) findViewById(R.id.newsTitle);
        newsImage = (ImageView) findViewById(R.id.newsImage);
        newsDetail = (TextView) findViewById(R.id.newsDetail);

        newsDataModel = ISGKatipSingleton.getInstance().getNewsDataModel();
    }

    //==============================================================================================
    @Override
    public void setNewsDetailData() {
        newsTitle.setText(newsDataModel.getNewsTitle());
        Picasso.get()
                .load(newsDataModel.getNewsImage())
                .resize(350,200)
                .into(newsImage);
        newsDetail.setText(newsDataModel.getNewsDetail());
    }

    //==============================================================================================
    @Override
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), NewsActivityViewImpl.class));
    }

    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), NewsActivityViewImpl.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
