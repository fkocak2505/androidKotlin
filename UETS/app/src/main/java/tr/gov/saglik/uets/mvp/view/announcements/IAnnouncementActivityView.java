package tr.gov.saglik.uets.mvp.view.announcements;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.announcements.responseModel.Response4Announcements;
import tr.gov.saglik.uets.mvp.model.announcements.responseModel.ValueOfAnnouncements;

public interface IAnnouncementActivityView {
    void initAnnouncementActivityComponent();

    void getAnnounncementsList();

    void clickAnnouncementsListItem();

    void fillAnnouncemenetListViewData(List<ValueOfAnnouncements> listOfAnnouncementsValue);

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void sendData2ActivityView(Response4Announcements response4Announcements);

    void clickGoBack();
}
