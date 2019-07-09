package tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.saveOrUpdateOrDeleteYetkinlik;

import android.app.Dialog;
import android.view.View;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.requestModel.SaveParams;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4EducatorList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4InstitutionData;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4TeamMemberList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.ValueOfEducatorList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.ValueOfInstitution;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.ValueOfTeamMemberList;

public interface IYetkinlikSaveUpdateDeleteActivityView {

    void initiliazeYetkinlikSaveUpdateDeleteActivity();

    void clickInstituationEdtTxt();

    void clickEducatorEdtTxt();

    void clickTeamMemberEdtTxt();

    void openFilterDialog4Institution(List<ValueOfInstitution> valueOfInstitutionList);

    void openFilterDialog4EducatorList(List<ValueOfEducatorList> valueOfEducatorLists);

    void openFilterDialog4TeamMemberList(List<ValueOfTeamMemberList> valueOfTeamMemberLists);

    void filterDialogConfig(Dialog dialog);

    void filterDialogConfig4Educator(Dialog dialog);

    void filterDialogConfig4TeamMember(Dialog dialog);

    void initFilterDialogComponent();

    void initFilterDialogComponent4Educator();

    void initFilterDialogComponent4TeamMember();

    void fillSearchCriteriaListViewData(List<ValueOfInstitution> valueOfInstitutionsList);

    void fillEducatorListViewData(List<ValueOfEducatorList> valueOfEducatorLists);

    void fillTeamMemberListViewData(List<ValueOfTeamMemberList> valueOfTeamMemberLists);

    void bindSearchCriteriaData2Adapter();

    void bindSearchCriteriaData2Adapter4Educator();

    void bindSearchCriteriaData2Adapter4TeamMember();

    void handleTeamMemberData(List<ValueOfTeamMemberList> valueOfTeamMemberLists);

    void fillAllComponent();

    void saveYetkinlik(View view);

    void updateYetkinlik(View view);

    void deleteYetkinlik(View view);

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void sendData2ActivityView(Response4InstitutionData response4InstitutionData);

    void sendData2ActivityViewEducatorList(Response4EducatorList response4EducatorList);

    void sendData2ActivityViewTeamMemberList(Response4TeamMemberList response4TeamMemberList);

    void ezDialogConfig(String titleText);

    void sendYetkinlikData2API(SaveParams saveParams);

    void deleteYetkinlikData2API(int yetkinlikId);

    void goBackActivity(String titleText, String explainText);

    void clickHomeScreen();

    void clickGoBack();

}
