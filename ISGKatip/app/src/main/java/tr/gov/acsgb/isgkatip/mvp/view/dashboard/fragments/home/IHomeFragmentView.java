package tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.home;

import android.view.View;

import java.util.List;

import tr.gov.acsgb.isgkatip.mvp.model.common.GridViewDataModel;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.homeFragment.dataModel.AddServiceListDataModel;

public interface IHomeFragmentView {

    void initHomeFragmentComponent(View view);

    void bindGirdViewData2Adapter();

    List<GridViewDataModel> setGridData();

    List<AddServiceListDataModel> getGridData();

    void clickGridListItem();

    void goMenuItemActivity(int position);

}
