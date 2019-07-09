package tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim;

import android.content.Context;

import java.util.List;

import tr.gov.acsgb.isgkatip.RequestResultListener;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.requestModel.FilterList4NeYapmaliyim;

public interface INeYapmaliyimActivityModel {

    void initApiService(int page, int size, List<FilterList4NeYapmaliyim> filterLists);

    void getNeYapmaliyimData(Context context, int page, int size, List<FilterList4NeYapmaliyim> filterLists, RequestResultListener requestResultListener);

}
