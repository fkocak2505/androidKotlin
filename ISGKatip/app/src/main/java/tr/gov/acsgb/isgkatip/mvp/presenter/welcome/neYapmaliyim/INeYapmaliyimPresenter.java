package tr.gov.acsgb.isgkatip.mvp.presenter.welcome.neYapmaliyim;

import android.content.Context;

import java.util.List;

import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.requestModel.FilterList4NeYapmaliyim;

public interface INeYapmaliyimPresenter {

    void neYapmaliyim(Context context, int page, int size, List<FilterList4NeYapmaliyim> filterLists);

}
