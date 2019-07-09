package tr.gov.saglik.uets.mvp.model.announcements.detail;

import tr.gov.saglik.uets.RequestResultListener;

public interface IAnnouncementsDetailActivityModel {

    void initApiService();

    void getAnnouncementsDetailData(int id, RequestResultListener requestResultListener);

}
