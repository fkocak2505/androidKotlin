package tr.gov.saglik.uets.mvp.model.studentReport.transport;

import tr.gov.saglik.uets.RequestResultListener;

public interface ITransportInfoActivityModel {

    void initApiService();

    void getTransportInfoData(int studenId, RequestResultListener requestResultListener);

    void setTransportInfoParam(int studenId);

}
