package tr.gov.acsgb.isgkatip.mvp.model.welcome.news;

import android.content.Context;

import java.util.List;

import tr.gov.acsgb.isgkatip.RequestResultListener;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.requestModel.FilterList;

public interface INewsActivityModel {

    void initApiService(int page, int size, List<FilterList> filterLists);

    void getNewsData(Context context, int page, int size, List<FilterList> filterLists, RequestResultListener requestResultListener);

}
