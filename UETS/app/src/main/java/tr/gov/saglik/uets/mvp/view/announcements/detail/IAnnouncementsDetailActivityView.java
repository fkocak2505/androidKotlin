package tr.gov.saglik.uets.mvp.view.announcements.detail;

import tr.gov.saglik.uets.mvp.model.announcements.responseModel.Response4AnnouncementsDetail;
import tr.gov.saglik.uets.mvp.model.announcements.responseModel.ValueOfAnnouncementsItem;

public interface IAnnouncementsDetailActivityView {

    void initAnnouncementsDetailComponent();

    void getAnnounncementsDetailData();

    void fillAnnouncementsData(ValueOfAnnouncementsItem valueOfAnnouncementsItem);

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void sendData2ActivityView(Response4AnnouncementsDetail response4AnnouncementsDetail);

    void clickGoBack();

    void clickHomeScreen();


}
