package tr.gov.acsgb.isgkatip.mvp.presenter.welcome.announcement;

import android.content.Context;

import java.util.List;

import retrofit2.Response;
import tr.gov.acsgb.isgkatip.RequestResultListener;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.IAnnouncementActivityModel;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.requestModel.FilterList4Announcement;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.responseModel.ResponseAnnouncementData;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.announcement.IAnnouncementActivityView;

public class AnnouncementPresenterImpl implements IAnnouncementPresenter {

    private IAnnouncementActivityModel iAnnouncementActivityModel;
    private IAnnouncementActivityView iAnnouncementActivityView;

    //==============================================================================================
    public AnnouncementPresenterImpl(IAnnouncementActivityModel iAnnouncementActivityModel, IAnnouncementActivityView iAnnouncementActivityView) {
        this.iAnnouncementActivityModel = iAnnouncementActivityModel;
        this.iAnnouncementActivityView = iAnnouncementActivityView;
    }

    //==============================================================================================
    @Override
    public void announcements(Context context, int page, int size, List<FilterList4Announcement> filterList4Announcements) {
        iAnnouncementActivityView.showLoading();
        iAnnouncementActivityModel.getAnnouncementsData(context, page, size, filterList4Announcements, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iAnnouncementActivityView.hideLoading();
                iAnnouncementActivityView.announcementData((ResponseAnnouncementData) response.body());
            }

            @Override
            public void onFail() {
                iAnnouncementActivityView.showError();
            }
        });
    }
}
