package tr.gov.saglik.uets.mvp.presenter.studentsReport.transport;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.INewTransportInfoActivityModel;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4NewTransportTransferType;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4SaveTransport;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4TransferProgram;
import tr.gov.saglik.uets.mvp.view.studentsReport.transport.INewTransportInfoActivityView;

public class NewTransportInfoActivityPresenterImpl implements INewTransportInfoActivityPresenter {

    private INewTransportInfoActivityModel iNewTransportInfoActivityModel;
    private INewTransportInfoActivityView iNewTransportInfoActivityView;

    public NewTransportInfoActivityPresenterImpl(INewTransportInfoActivityModel iNewTransportInfoActivityModel, INewTransportInfoActivityView iNewTransportInfoActivityView) {
        this.iNewTransportInfoActivityModel = iNewTransportInfoActivityModel;
        this.iNewTransportInfoActivityView = iNewTransportInfoActivityView;
    }

    @Override
    public void getNewTransportInfoTransferTypeData(String code) {
        iNewTransportInfoActivityView.showLoading();
        iNewTransportInfoActivityModel.getNewTransportTypeData(code, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iNewTransportInfoActivityView.hideLoading();
                iNewTransportInfoActivityView.sendData2Acvtivity((Response4NewTransportTransferType) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iNewTransportInfoActivityView.hideLoading();
                iNewTransportInfoActivityView.showError("Error When Fetching data New Transport Transfer Type..");
            }
        });
    }

    @Override
    public void getNewTransportInfoTransferReasonData(String code) {
        iNewTransportInfoActivityView.showLoading();
        iNewTransportInfoActivityModel.getNewTransportTypeData(code, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iNewTransportInfoActivityView.hideLoading();
                iNewTransportInfoActivityView.sendData2Acvtivity4TransferReason((Response4NewTransportTransferType) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iNewTransportInfoActivityView.hideLoading();
                iNewTransportInfoActivityView.showError("Error When Fetching data New Transport Transfer Reason..");
            }
        });
    }

    @Override
    public void getNewTransportInfoTransferProgram() {
        iNewTransportInfoActivityView.showLoading();
        iNewTransportInfoActivityModel.getNewTransportProgramData(new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iNewTransportInfoActivityView.hideLoading();
                iNewTransportInfoActivityView.sendData2Acvtivity4TransferProgram((Response4TransferProgram) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iNewTransportInfoActivityView.hideLoading();
                iNewTransportInfoActivityView.showError("Error When Fetching data New Transport Transfer Program Data..");
            }
        });
    }

    @Override
    public void saveTransport(String transferTypeId, String transferReason, String programId, String content) {
        iNewTransportInfoActivityView.showLoading();
        iNewTransportInfoActivityModel.saveTransport(transferTypeId, transferReason, programId, content, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iNewTransportInfoActivityView.hideLoading();
                iNewTransportInfoActivityView.sendData2AcvtivitySaveTransport((Response4SaveTransport) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iNewTransportInfoActivityView.hideLoading();
                iNewTransportInfoActivityView.showError("Error When Save Transport....");
            }
        });
    }
}
