package tr.gov.saglik.uets.mvp.presenter.studentsReport.transport;

public interface INewTransportInfoActivityPresenter {

    void getNewTransportInfoTransferTypeData(String code);

    void getNewTransportInfoTransferReasonData(String code);

    void  getNewTransportInfoTransferProgram();

    void saveTransport(String transferTypeId, String transferReason, String programId, String content);

}
