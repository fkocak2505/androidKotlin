package tr.gov.saglik.uets.mvp.view.curriculum;

import android.app.Dialog;
import android.view.View;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.Response4CurriculumList;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.ValueOfCurriculum;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.filterList.ConditionList4CurriculumFilter;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.filterList.Response4CurriculumFilterList;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.filterList.ValueOfCurriculumFilterList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel.ConditionList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel.ResponseYetkinlikListFilterListData;

public interface ICurriculumActivityView {

    void initCurriculumActivityComponent();

    void getCurriculumList();

    void fillCurriculumListViewData(List<ValueOfCurriculum> valueOfCurriculumList);

    void bindCurriculumData2Adapter();

    void clickCurriculumListView();

    void clickFilter(View view);

    /// Filter Dialog Methods
    void openFilterDialog(List<ValueOfCurriculumFilterList> valueOfCurriculumFilterLists);

    void fillSearchCriteriaDetailListViewData(List<ConditionList4CurriculumFilter> listOfConditionsDetailData);

    void bindSearchCriteriaDetailData2Adapter();

    void handleSelectedItemsName();

    void setListViewsVisibilityConfig(boolean isRootVisible);

    void setDialogHeaderConfig(String closeOrBackTextTitle, String dialogTitle);

    void filterDialogConfig(Dialog dialog);

    void initFilterDialogComponent();

    void fillSearchCriteriaListViewData(List<ValueOfCurriculumFilterList> valueOfCurriculumFilterLists);

    void bindSearchCriteriaData2Adapter();

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void sendData2ActivityView(Response4CurriculumList response4CurriculumList);

    void sendCurriculumFilterListData2ActivityView(Response4CurriculumFilterList response4CurriculumFilterList);

    void clickGoBack();


}
