package tr.gov.saglik.uets.mvp.view.programs;

import android.app.Dialog;
import android.view.View;

public interface IProgramsActivityView {

    void initProgramsActivityComponent();

    void fillProgramsListViewData();

    void bindPrograms2Adapter();

    void clickProgramsListView();

    void clickFilter(View view);

    /// Filter Dialog Methods
    void openFilterDialog();

    void filterDialogConfig(Dialog dialog);

    void initFilterDialogComponent();

    void fillSearchCriteriaListViewData();

    void bindSearchCriteriaData2Adapter();

    void clickGoBack();
}
