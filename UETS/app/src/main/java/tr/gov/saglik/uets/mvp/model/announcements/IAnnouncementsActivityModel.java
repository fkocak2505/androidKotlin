package tr.gov.saglik.uets.mvp.model.announcements;

import tr.gov.saglik.uets.RequestResultListener;

public interface IAnnouncementsActivityModel {

    void initApiService();

    void setAnnouncementsListParams();

    void getAnnouncementList(RequestResultListener requestResultListener);

}
