package tr.gov.saglik.uets.mvp.view.studentsReport.programInfo;

import tr.gov.saglik.uets.mvp.model.studentReport.programInfo.responseModel.Response4ProgramInfo;
import tr.gov.saglik.uets.mvp.model.studentReport.programInfo.responseModel.ValueOfProgramInfo;

public interface IProgramInfoActivityView {

    void initProgramInfoActivityComponent();

    void getProgramInfoData();

    void fillProgramInfoData(ValueOfProgramInfo valueOfProgramInfo);

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void sendData2ActivityView(Response4ProgramInfo response4ProgramInfo);

    void clickGoBack();

    void clickHomeScreen();

}
