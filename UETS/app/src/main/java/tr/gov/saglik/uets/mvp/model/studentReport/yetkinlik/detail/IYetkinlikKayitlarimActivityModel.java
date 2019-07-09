package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.detail;

import tr.gov.saglik.uets.RequestResultListener;

public interface IYetkinlikKayitlarimActivityModel {

    void initApiService(int memberId);

    void getYetkinlikKayitlarim(int memberId, RequestResultListener requestResultListener);

}
