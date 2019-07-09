package tr.gov.saglik.uets.mvp.model.demands;

import tr.gov.saglik.uets.RequestResultListener;

public interface IDemandsActivityModel {

    void initApiService();

    void getDemandsList(int memberId, RequestResultListener requestResultListener);

    void getTaskOperationListByDemandId(int demandId, RequestResultListener requestResultListener);

    void operationTask(int demandId, int memberId, int taskOperationId, RequestResultListener requestResultListener);

    void setDemandsParam(int memberId);

    void setDemandTaskOperationListByDemandId(int demandId);

    void setOperationTaskParams(int demandId, int memberId, int taskOperationId);

}
