package tr.gov.saglik.uets.mvp.presenter.announcements;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.announcements.IAnnouncementsActivityModel;
import tr.gov.saglik.uets.mvp.model.announcements.responseModel.Response4Announcements;
import tr.gov.saglik.uets.mvp.view.announcements.IAnnouncementActivityView;

public class AnnouncementsActivityPresenterImpl implements IAnnouncementsActivityPresenter {

    private IAnnouncementsActivityModel iAnnouncementsActivityModel;
    private IAnnouncementActivityView iAnnouncementActivityView;

    //==============================================================================================

    /**
     * Presenter Constructor
     * @param iAnnouncementsActivityModel
     * @param iAnnouncementActivityView
     */
    //==============================================================================================
    public AnnouncementsActivityPresenterImpl(IAnnouncementsActivityModel iAnnouncementsActivityModel, IAnnouncementActivityView iAnnouncementActivityView) {
        this.iAnnouncementsActivityModel = iAnnouncementsActivityModel;
        this.iAnnouncementActivityView = iAnnouncementActivityView;
    }

    //==============================================================================================

    /**
     * Presenter Request 4 Announcement List 2 Model..
     */
    //==============================================================================================
    @Override
    public void getAnnouncementsList() {
        iAnnouncementActivityView.showLoading();
        iAnnouncementsActivityModel.getAnnouncementList(new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iAnnouncementActivityView.hideLoading();
                iAnnouncementActivityView.sendData2ActivityView((Response4Announcements) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {
                iAnnouncementActivityView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iAnnouncementActivityView.showError("Error when fetching Announcements List Data");
            }
        });
    }
}
