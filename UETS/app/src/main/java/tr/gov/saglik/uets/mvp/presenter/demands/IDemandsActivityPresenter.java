package tr.gov.saglik.uets.mvp.presenter.demands;

public interface IDemandsActivityPresenter {

    void demandsList(int memberId);

    void taskOperationListByDemandId(int demandId);

    void operationTask(int demandId, int memberId, int taskOperationId);

}
