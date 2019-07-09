package tr.gov.acsgb.isgkatip.mvp.presenter.welcome.announcement;

import android.content.Context;

import java.util.List;

import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.requestModel.FilterList4Announcement;

public interface IAnnouncementPresenter {

    void announcements(Context context, int page, int size, List<FilterList4Announcement> filterList4Announcements);

}
