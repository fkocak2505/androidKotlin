package tr.gov.acsgb.isgkatip.mvp.presenter.dashboard.navMenuScreen.publications;

import android.content.Context;

import java.util.List;

import retrofit2.Response;
import tr.gov.acsgb.isgkatip.RequestResultListener;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.IPublicationsActivityModel;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.requestModel.FilterList4Publications;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.responseModel.ResponsePublicationsData;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.navMenuScreen.publications.IPublicationsActivityView;

public class PublicationsPresenterImpl implements IPublicationsPresenter {

    private IPublicationsActivityModel iPublicationsActivityModel;
    private IPublicationsActivityView iPublicationsActivityView;

    //==============================================================================================
    public PublicationsPresenterImpl(IPublicationsActivityModel iPublicationsActivityModel, IPublicationsActivityView iPublicationsActivityView) {
        this.iPublicationsActivityModel = iPublicationsActivityModel;
        this.iPublicationsActivityView = iPublicationsActivityView;
    }


    @Override
    public void publications(Context context, int page, int size, List<FilterList4Publications> filterList4Publications) {
        iPublicationsActivityView.showLoading();
        iPublicationsActivityModel.getPublicationsData(context, page, size, filterList4Publications, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iPublicationsActivityView.hideLoading();
                iPublicationsActivityView.publicationsData((ResponsePublicationsData) response.body());
            }

            @Override
            public void onFail() {
                iPublicationsActivityView.showError();
            }
        });
    }
}
