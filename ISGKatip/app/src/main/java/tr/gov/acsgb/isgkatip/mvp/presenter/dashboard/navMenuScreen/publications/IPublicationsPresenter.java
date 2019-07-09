package tr.gov.acsgb.isgkatip.mvp.presenter.dashboard.navMenuScreen.publications;

import android.content.Context;

import java.util.List;

import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.requestModel.FilterList4Publications;

public interface IPublicationsPresenter {

    void publications(Context context, int page, int size, List<FilterList4Publications> filterList4Publications);

}
