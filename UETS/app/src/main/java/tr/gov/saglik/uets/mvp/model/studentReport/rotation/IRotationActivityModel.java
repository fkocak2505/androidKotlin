package tr.gov.saglik.uets.mvp.model.studentReport.rotation;

import tr.gov.saglik.uets.RequestResultListener;

public interface IRotationActivityModel {

    void initApiService();

    void setRotationList(int studentId);

    void setRotationDetailParams(String rotationId);

    void getRotationList(int studentId, RequestResultListener requestResultListener);

    void getRotationDetailList(String rotationId, RequestResultListener requestResultListener);

}
