package tr.gov.acsgb.isgkatip.mvp.presenter.welcome.news;

import android.content.Context;

import java.util.List;

import retrofit2.Response;
import tr.gov.acsgb.isgkatip.RequestResultListener;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.INewsActivityModel;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.requestModel.FilterList;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.responseModel.ResponseNewsData;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.news.INewsActivityView;

public class NewsPresenterImpl implements INewsPresenter {

    private INewsActivityModel iNewsActivityModel;
    private INewsActivityView iNewsActivityView;

    //==============================================================================================
    public NewsPresenterImpl(INewsActivityModel iNewsActivityModel, INewsActivityView iNewsActivityView) {
        this.iNewsActivityModel = iNewsActivityModel;
        this.iNewsActivityView = iNewsActivityView;
    }

    //==============================================================================================
    @Override
    public void news(Context context, int page, int size, List<FilterList> filterLists) {
        iNewsActivityView.showLoading();
        iNewsActivityModel.getNewsData(context, page, size, filterLists, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iNewsActivityView.hideLoading();
                iNewsActivityView.newsData((ResponseNewsData) response.body());
            }

            @Override
            public void onFail() {
                iNewsActivityView.showError();
            }
        });

    }
}
