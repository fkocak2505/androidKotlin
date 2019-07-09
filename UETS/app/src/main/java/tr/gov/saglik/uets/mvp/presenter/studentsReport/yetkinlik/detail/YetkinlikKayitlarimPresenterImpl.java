package tr.gov.saglik.uets.mvp.presenter.studentsReport.yetkinlik.detail;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.detail.IYetkinlikKayitlarimActivityModel;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.detail.responseModel.ResponseYetkinlikKayitlarim;
import tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.detail.IYetkinlikKayitlarimActivityView;

public class YetkinlikKayitlarimPresenterImpl implements IYetkinlikKayitlarimPresenter {

    private IYetkinlikKayitlarimActivityModel iYetkinlikKayitlarimActivityModel;
    private IYetkinlikKayitlarimActivityView iYetkinlikKayitlarimActivityView;

    public YetkinlikKayitlarimPresenterImpl(IYetkinlikKayitlarimActivityModel iYetkinlikKayitlarimActivityModel, IYetkinlikKayitlarimActivityView iYetkinlikKayitlarimActivityView) {
        this.iYetkinlikKayitlarimActivityModel = iYetkinlikKayitlarimActivityModel;
        this.iYetkinlikKayitlarimActivityView = iYetkinlikKayitlarimActivityView;
    }

    @Override
    public void yetkinlikKayitlarim(int memberId) {
        iYetkinlikKayitlarimActivityView.showLoading();
        iYetkinlikKayitlarimActivityModel.getYetkinlikKayitlarim(memberId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iYetkinlikKayitlarimActivityView.hideLoading();
                iYetkinlikKayitlarimActivityView.sendYetkinlikKayitlarimData2Activity((ResponseYetkinlikKayitlarim) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {
                iYetkinlikKayitlarimActivityView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iYetkinlikKayitlarimActivityView.hideLoading();
                iYetkinlikKayitlarimActivityView.showError();
            }
        });
    }
}
