package tr.gov.saglik.uets.mvp.model.studentReport.transport;

import tr.gov.saglik.uets.RequestResultListener;

public interface INewTransportInfoActivityModel {

    void initApiService();

    void setParamOfNewTransportTransferTypeData(String code);

    void setParamOfSaveTransport(String transferTypeId, String transferReasonId, String programId, String content);

    void getNewTransportTypeData(String code, RequestResultListener requestResultListener);

    void getNewTransportProgramData(RequestResultListener requestResultListener);

    void saveTransport(String transferTypeId, String transferReasonId, String programId, String content, RequestResultListener requestResultListener);

}
