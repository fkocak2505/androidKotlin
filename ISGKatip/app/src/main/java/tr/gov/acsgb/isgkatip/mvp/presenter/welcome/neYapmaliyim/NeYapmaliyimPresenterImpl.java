package tr.gov.acsgb.isgkatip.mvp.presenter.welcome.neYapmaliyim;

import android.content.Context;

import java.util.List;

import retrofit2.Response;
import tr.gov.acsgb.isgkatip.RequestResultListener;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.INeYapmaliyimActivityModel;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.requestModel.FilterList4NeYapmaliyim;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.responseModel.ResponseNeYapmaliyimData;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.neYapmaliyim.INeYapmaliyimActivityView;

public class NeYapmaliyimPresenterImpl implements INeYapmaliyimPresenter {

    private INeYapmaliyimActivityModel iNeYapmaliyimActivityModel;
    private INeYapmaliyimActivityView iNeYapmaliyimActivityView;

    public NeYapmaliyimPresenterImpl(INeYapmaliyimActivityModel iNeYapmaliyimActivityModel, INeYapmaliyimActivityView iNeYapmaliyimActivityView) {
        this.iNeYapmaliyimActivityModel = iNeYapmaliyimActivityModel;
        this.iNeYapmaliyimActivityView = iNeYapmaliyimActivityView;
    }

    //==============================================================================================
    @Override
    public void neYapmaliyim(Context context, int page, int size, List<FilterList4NeYapmaliyim> filterLists) {
        iNeYapmaliyimActivityView.showLoading();
        iNeYapmaliyimActivityModel.getNeYapmaliyimData(context, page, size, filterLists, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iNeYapmaliyimActivityView.hideLoading();
                iNeYapmaliyimActivityView.neYapmaliyimData((ResponseNeYapmaliyimData) response.body());
            }

            @Override
            public void onFail() {
                iNeYapmaliyimActivityView.showError();
            }
        });
    }
}
