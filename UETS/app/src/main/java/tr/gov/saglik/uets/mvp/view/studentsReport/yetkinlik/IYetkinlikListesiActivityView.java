package tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik;

import android.app.Dialog;
import android.view.View;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel.ConditionList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel.ResponseYetkinlikListFilterListData;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel.ValueOfYetkinlikListFilterData;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.responseModel.ResponseYetkinlikList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.responseModel.ValueOfYetkinlikList;

public interface IYetkinlikListesiActivityView {

    void initYetkinlikBilgileriActivityComponent();

    void getYetkinlikListByMember();

    void fillYetkinlikBilgileriListData(List<ValueOfYetkinlikList> listOfYetkinlikListValue);

    void bindYetkinlikBilgileri2ListViewAdapter();

    void clickYetkinlikBilgileriListViewItem();

    void clickFilterButton4Yetkinlik(View view);

    void openFilterDialog4Yetkinlik(List<ValueOfYetkinlikListFilterData> listOfValueOfYetkinlikListFilterList);

    void handleSelectedItemsName();

    void setListViewsVisibilityConfig(boolean isRootVisible);

    void setDialogHeaderConfig(String closeOrBackTextTitle, String dialogTitle);

    void openFilterDialog4SelectedYetkinlikDetail(List<ConditionList> listOfConditionFilterList);

    void filterDialogConfig(Dialog dialog);

    void initFilterDialogComponent();

    void fillSearchCriteriaListViewData(List<ValueOfYetkinlikListFilterData> listOfValueOfYetkinlikListFilterList);

    void fillSearchCriteriaDetailListViewData(List<ConditionList> listOfConditionsDetailData);

    void bindSearchCriteriaData2Adapter();

    void bindSearchCriteriaDetailData2Adapter();

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void sendData2ActivityView(ResponseYetkinlikListFilterListData responseYetkinlikListFilterListData);

    void sendYetkinlikListData2Activity(ResponseYetkinlikList responseYetkinlikList);

    void clickGoBack();

    void clickHomeScreen();


}
