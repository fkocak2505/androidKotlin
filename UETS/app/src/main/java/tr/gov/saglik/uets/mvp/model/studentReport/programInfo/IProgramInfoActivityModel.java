package tr.gov.saglik.uets.mvp.model.studentReport.programInfo;

import tr.gov.saglik.uets.RequestResultListener;

public interface IProgramInfoActivityModel {

    void initApiService();

    void setProgramInfoParam(int memberId);

    void getProgramInfo(int memberId, RequestResultListener requestResultListener);


}
