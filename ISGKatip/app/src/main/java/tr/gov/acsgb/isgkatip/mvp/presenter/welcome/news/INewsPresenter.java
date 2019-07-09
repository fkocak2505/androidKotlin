package tr.gov.acsgb.isgkatip.mvp.presenter.welcome.news;

import android.content.Context;

import java.util.List;

import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.requestModel.FilterList;

public interface INewsPresenter {

    void news(Context context,int page, int size, List<FilterList> filterLists);

}
