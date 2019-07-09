package tr.gov.saglik.uets.mvp.view.studentsReport.transport;

import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4NewTransportTransferType;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4SaveTransport;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4TransferProgram;

public interface INewTransportInfoActivityView {

    void initNewTransportActivityComponent();

    void getDataOfTransferType();

    void chooseTransferTypeOnSpinner();

    void chooseTransferReasonOnSpinner();

    void chooseTransferProgramOnSpinner();

    void clickSaveTransport();

    void sendData2Acvtivity(Response4NewTransportTransferType response4NewTransportTransferType);

    void sendData2Acvtivity4TransferProgram(Response4TransferProgram response4TransferProgram);

    void sendData2Acvtivity4TransferReason(Response4NewTransportTransferType response4NewTransportTransferReason);

    void sendData2AcvtivitySaveTransport(Response4SaveTransport response4SaveTransport);

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void clickHomeScreen();

    void clickGoBack();

}
