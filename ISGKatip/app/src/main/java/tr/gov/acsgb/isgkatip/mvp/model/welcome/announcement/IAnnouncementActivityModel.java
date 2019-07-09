package tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement;

import android.content.Context;

import java.util.List;

import tr.gov.acsgb.isgkatip.RequestResultListener;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.requestModel.FilterList4Announcement;

public interface IAnnouncementActivityModel {

    void initApiService(int page, int size, List<FilterList4Announcement> filterList4Announcements);

    void getAnnouncementsData(Context context, int page, int size, List<FilterList4Announcement> filterList4Announcements, RequestResultListener requestResultListener);

}
