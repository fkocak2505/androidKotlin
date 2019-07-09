package tr.gov.saglik.uets.mvp.presenter.announcements.detail;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.announcements.detail.IAnnouncementsDetailActivityModel;
import tr.gov.saglik.uets.mvp.model.announcements.responseModel.Response4AnnouncementsDetail;
import tr.gov.saglik.uets.mvp.view.announcements.detail.IAnnouncementsDetailActivityView;

public class AnnouncementsDetailActivityPresenterImpl implements IAnnouncementsDetailActivityPresenter {

    private IAnnouncementsDetailActivityModel iAnnouncementsDetailActivityModel;
    private IAnnouncementsDetailActivityView iAnnouncementsDetailActivityView;

    public AnnouncementsDetailActivityPresenterImpl(IAnnouncementsDetailActivityModel iAnnouncementsDetailActivityModel, IAnnouncementsDetailActivityView iAnnouncementsDetailActivityView) {
        this.iAnnouncementsDetailActivityModel = iAnnouncementsDetailActivityModel;
        this.iAnnouncementsDetailActivityView = iAnnouncementsDetailActivityView;
    }

    @Override
    public void getAnnouncementsDetail(int id) {
        iAnnouncementsDetailActivityView.showLoading();
        iAnnouncementsDetailActivityModel.getAnnouncementsDetailData(id, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iAnnouncementsDetailActivityView.hideLoading();
                iAnnouncementsDetailActivityView.sendData2ActivityView((Response4AnnouncementsDetail) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {
                iAnnouncementsDetailActivityView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iAnnouncementsDetailActivityView.showError("Erorr when Fetching Announcements Detail Data..");
            }
        });
    }
}
