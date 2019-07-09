package tr.gov.saglik.uets.mvp.model.studentReport.thesis;

import tr.gov.saglik.uets.RequestResultListener;

public interface IThesisActivityModel {

    void initApiService();

    void setParamsOfThesisData(String studentId);

    void getThesis(String studentId, RequestResultListener requestResultListener);

}
