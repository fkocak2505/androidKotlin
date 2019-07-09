package tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.home.addService;

import android.view.View;
import android.widget.Switch;

import java.util.List;

import tr.gov.acsgb.isgkatip.mvp.model.dashboard.homeFragment.dataModel.AddServiceListDataModel;

public interface IAddServiceActivityView {

    void initAddServiceActivityComponent();

    void bindServiceListView2Adapter();

    List<AddServiceListDataModel> fillServiceData();

    List<AddServiceListDataModel> getServiceData();

    void serviceListViewItemClickListener();

    void newServiceListData(View view, int position);

    void saveGridData2CSharedPreferences(Switch switchButton, boolean isOpen, int position);

    int getOpenServiceCount();

    void goBack(View view);

}
