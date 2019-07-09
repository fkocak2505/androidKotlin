package tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.home.addService;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.homeFragment.dataModel.AddServiceListDataModel;
import tr.gov.acsgb.isgkatip.mvp.view.common.GenericClass;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.DashboardActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.home.addService.adapter.AddServiceListViewAdapter;

public class AddServiceActivityViewImpl extends AppCompatActivity implements IAddServiceActivityView {

    ListView serviceListView;
    ImageView goBack;

    AddServiceListViewAdapter serviceAdapter;
    List<AddServiceListDataModel> serviceListDataModelList;

    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        initAddServiceActivityComponent();
        bindServiceListView2Adapter();

        serviceListViewItemClickListener();

    }

    //==============================================================================================
    @Override
    public void initAddServiceActivityComponent() {
        getSupportActionBar().hide();

        serviceListView = (ListView) findViewById(R.id.serviceListView);
        goBack = (ImageView) findViewById(R.id.goBack);
    }

    //==============================================================================================
    @Override
    public void bindServiceListView2Adapter() {
        serviceAdapter = new AddServiceListViewAdapter(this, fillServiceData(), getOpenServiceCount());
        serviceListView.setAdapter(serviceAdapter);
    }

    //==============================================================================================
    @Override
    public List<AddServiceListDataModel> fillServiceData() {
        serviceListDataModelList = getServiceData();
        if (serviceListDataModelList.size() == 0) {
            serviceListDataModelList = new ArrayList<>();
            serviceListDataModelList.add(new AddServiceListDataModel(R.drawable.home_tab_appeals, "Başvurular", true));
            serviceListDataModelList.add(new AddServiceListDataModel(R.drawable.home_tab_contract, "Sözleşmeler", true));
            serviceListDataModelList.add(new AddServiceListDataModel(R.drawable.home_tab_approvel_process, "Onay Süreci", true));
            serviceListDataModelList.add(new AddServiceListDataModel(R.drawable.home_tab_isg_service, "ISG Hizmetleri", true));
            serviceListDataModelList.add(new AddServiceListDataModel(R.drawable.home_tab_education, "Eğitim", true));
            serviceListDataModelList.add(new AddServiceListDataModel(R.drawable.home_tab_plus, "Fatih", false));
            serviceListDataModelList.add(new AddServiceListDataModel(R.drawable.home_tab_plus, "Koçak", false));
            serviceListDataModelList.add(new AddServiceListDataModel(R.drawable.home_tab_plus, "Deneme1", false));
            serviceListDataModelList.add(new AddServiceListDataModel(R.drawable.home_tab_plus, "Deneme2", false));
            serviceListDataModelList.add(new AddServiceListDataModel(R.drawable.home_tab_plus, "Deneme3", false));
            serviceListDataModelList.add(new AddServiceListDataModel(R.drawable.home_tab_plus, "Deneme4", false));
            serviceListDataModelList.add(new AddServiceListDataModel(R.drawable.home_tab_plus, "Deneme5", false));
            serviceListDataModelList.add(new AddServiceListDataModel(R.drawable.home_tab_plus, "Deneme6", false));
            serviceListDataModelList.add(new AddServiceListDataModel(R.drawable.home_tab_plus, "Deneme7", false));
        }
        return serviceListDataModelList;
    }

    //==============================================================================================
    @Override
    public List<AddServiceListDataModel> getServiceData() {
        GenericClass<AddServiceListDataModel> genericClass = new GenericClass<>(getApplicationContext());
        return genericClass.returnList(getApplicationContext().getResources().getString(R.string.keyOfServiceListData), AddServiceListDataModel[].class);
    }

    //==============================================================================================
    @Override
    public void serviceListViewItemClickListener() {
        serviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newServiceListData(view, position);
            }
        });
    }

    //==============================================================================================
    @Override
    public void newServiceListData(View view, int position) {
        Switch switchButton = (Switch) view.findViewById(R.id.serviceIsOpen);
        boolean isOpen = switchButton.isChecked();
        if (isOpen) {
            saveGridData2CSharedPreferences(switchButton, isOpen, position);
        } else {
            if (getOpenServiceCount() >= 5)
                Toast.makeText(this, "En fazla 5 hizmeti aktif edebilirsiniz." + serviceListDataModelList.get(position).getServiceItemTitle() +
                        " hizmetini aktif hale getirmek için bir adet servisi kapatın..", Toast.LENGTH_SHORT).show();
            else {
                saveGridData2CSharedPreferences(switchButton, isOpen, position);
            }
        }
    }

    //==============================================================================================
    @Override
    public void saveGridData2CSharedPreferences(Switch switchButton, boolean isOpen, int position) {
        switchButton.setChecked(!isOpen);
        serviceListDataModelList.get(position).setServiceIsOpen(!isOpen);

        GenericClass<AddServiceListDataModel> genericClass = new GenericClass<>(getApplicationContext(), serviceListDataModelList);
        genericClass.saveList(getResources().getString(R.string.keyOfServiceListData));
    }

    //==============================================================================================
    @Override
    public int getOpenServiceCount() {
        int count = 0;
        for (AddServiceListDataModel itemOfServiceList : serviceListDataModelList) {
            if (itemOfServiceList.isServiceIsOpen()) count++;
        }

        return count;
    }

    //==============================================================================================
    @Override
    public void goBack(View view) {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DashboardActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), DashboardActivityViewImpl.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
