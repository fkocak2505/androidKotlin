package tr.gov.acsgb.isgkatip.mvp.view.welcome.news;

import android.view.View;

import java.util.List;

import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.responseModel.Content;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.responseModel.ResponseNewsData;

public interface INewsActivityView {

    void initNewsActivityComponent();

    void getNewsData(int page);

    void fillNewsListData(List<Content> listOfContent);

    void bindNewsList2Adapter();

    void clickNewsListViewItem();

    void goBack(View view);

    void showLoading();

    void hideLoading();

    void showError();

    void newsData(ResponseNewsData responseNewsData);


}
