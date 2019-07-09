package tr.gov.acsgb.isgkatip.mvp.view.dashboard.navMenuScreen.publications;

import android.view.View;

import java.util.List;

import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.responseModel.Content4Publications;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.responseModel.ResponsePublicationsData;

public interface IPublicationsActivityView {

    void initPublicationsActivityComponent();

    void getPublicationsData(int page);

    void publicationsData(ResponsePublicationsData responsePublicationsData);

    void fillPublicationsListViewData(List<Content4Publications> listOfContent);

    void bindPublicationsListData2ListView();

    void clickPublicationsListViewItem();

    void showLoading();

    void hideLoading();

    void showError();

    void goBack(View view);

}
