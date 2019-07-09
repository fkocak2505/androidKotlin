package tr.gov.acsgb.isgkatip.mvp.presenter.welcome.nasilYaparim;

import android.content.Context;

import java.util.List;

import retrofit2.Response;
import tr.gov.acsgb.isgkatip.RequestResultListener;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.INasilYaparimActivityModel;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.requestModel.FilterList4NasilYaparim;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.responseModel.ResponseNasilYaparimData;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.nasilYaparim.INasilYaparimActivityView;

public class NasilYaparimPresenterImpl implements INasilYaparimPresenter {

    private INasilYaparimActivityModel iNasilYapariActivityModel;
    private INasilYaparimActivityView iNasilYapariActivityView;

    public NasilYaparimPresenterImpl(INasilYaparimActivityModel iNasilYapariActivityModel, INasilYaparimActivityView iNasilYapariActivityView) {
        this.iNasilYapariActivityModel = iNasilYapariActivityModel;
        this.iNasilYapariActivityView = iNasilYapariActivityView;
    }

    //==============================================================================================
    @Override
    public void nasilYaparim(Context context, int page, int size, List<FilterList4NasilYaparim> filterList4NasilYaparims) {
        iNasilYapariActivityView.showLoading();
        iNasilYapariActivityModel.getNasilYaparimData(context, page, size, filterList4NasilYaparims, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iNasilYapariActivityView.hideLoading();
                iNasilYapariActivityView.nasilYaparimData((ResponseNasilYaparimData) response.body());
            }

            @Override
            public void onFail() {
                iNasilYapariActivityView.showError();
            }
        });
    }
}
