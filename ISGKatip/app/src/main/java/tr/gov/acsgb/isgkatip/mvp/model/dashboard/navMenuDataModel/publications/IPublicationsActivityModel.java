package tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications;

import android.content.Context;

import java.util.List;

import tr.gov.acsgb.isgkatip.RequestResultListener;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.requestModel.FilterList4Publications;

public interface IPublicationsActivityModel {

    void initApiService(int page, int size, List<FilterList4Publications> filterList4Publications);

    void getPublicationsData(Context context, int page, int size, List<FilterList4Publications> filterList4Publications, RequestResultListener requestResultListener);

}
