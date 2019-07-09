package tr.gov.acsgb.isgkatip.mvp.view.welcome.announcement;

import android.view.View;

import java.util.List;

import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.responseModel.Content4Announcement;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.responseModel.ResponseAnnouncementData;

public interface IAnnouncementActivityView {

    void initAnnouncementActivityComponent();

    void getAnnouncementsData(int page);

    void fillAnnouncementsListData(List<Content4Announcement> listOfContentAnnouncements);

    void bindAnnouncementsList2Adapter();

    void clickAnnouncementsListViewItem();

    void showLoading();

    void hideLoading();

    void showError();

    void announcementData(ResponseAnnouncementData responseAnnouncementData);

    void goBack(View view);

}
