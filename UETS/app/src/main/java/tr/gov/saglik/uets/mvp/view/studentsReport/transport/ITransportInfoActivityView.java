package tr.gov.saglik.uets.mvp.view.studentsReport.transport;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4TransportInfoData;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.ValueOfTransportData;

public interface ITransportInfoActivityView {

    void initTransportInfoActivityComponent();

    void getTransportData();

    void fillTransportInfoListViewData(List<ValueOfTransportData> valueOfTransportDataList);

    void bindTransportInfoData2Adapter();

    void sendData2Acvtivity(Response4TransportInfoData response4TransportInfoData);

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void clickAddTransportInfo();

    void clickGoBack();

    void clickHomeScreen();

}
