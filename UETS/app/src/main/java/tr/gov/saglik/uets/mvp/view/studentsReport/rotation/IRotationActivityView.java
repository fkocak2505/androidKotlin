package tr.gov.saglik.uets.mvp.view.studentsReport.rotation;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.studentReport.rotation.responseModel.DataOfRotation;
import tr.gov.saglik.uets.mvp.model.studentReport.rotation.responseModel.Response4RotationList;

public interface IRotationActivityView {

    void initRotationActivityComponent();

    void getRotationList();

    void fillRotationListData(List<DataOfRotation> dataOfRotationList);

    void bindRotation2ListViewAdapter();

    void clickRotationListViewItem();

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void sendData2ActivityView(Response4RotationList response4RotationList);

    void clickGoBack();

    void clickHomeScreen();


}
