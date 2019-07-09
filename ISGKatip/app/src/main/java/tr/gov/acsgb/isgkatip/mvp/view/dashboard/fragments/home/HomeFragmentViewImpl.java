package tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.common.GridViewDataModel;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.homeFragment.dataModel.AddServiceListDataModel;
import tr.gov.acsgb.isgkatip.mvp.view.common.GenericClass;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.home.adapter.HomeFragmentGridViewAdapter;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.home.addService.AddServiceActivityViewImpl;

public class HomeFragmentViewImpl extends Fragment implements IHomeFragmentView {

    GridView grid;
    GridView grid1;
    List<GridViewDataModel> listOfGridData;


    //==============================================================================================
    public HomeFragmentViewImpl() {
        // Required empty public constructor
    }

    //==============================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //==============================================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initHomeFragmentComponent(view);
        bindGirdViewData2Adapter();
        clickGridListItem();

        return view;
    }

    //==============================================================================================
    @Override
    public void initHomeFragmentComponent(View view) {
        grid = (GridView) view.findViewById(R.id.grid);
        grid1 = (GridView) view.findViewById(R.id.grid1);
    }

    //==============================================================================================
    @Override
    public void bindGirdViewData2Adapter() {
        HomeFragmentGridViewAdapter homeFragmentGridViewAdapter = new HomeFragmentGridViewAdapter(getActivity().getApplicationContext(), setGridData());
        grid.setAdapter(homeFragmentGridViewAdapter);
        grid1.setAdapter(homeFragmentGridViewAdapter);
    }

    //==============================================================================================
    @Override
    public List<GridViewDataModel> setGridData() {
        listOfGridData = new ArrayList<>();

        List<AddServiceListDataModel> serviceListDataModelList = getGridData();

        if (serviceListDataModelList.size() == 0) {
            listOfGridData.add(new GridViewDataModel(R.drawable.home_tab_appeals, "Başvurular"));
            listOfGridData.add(new GridViewDataModel(R.drawable.home_tab_contract, "Sözleşmeler"));
            listOfGridData.add(new GridViewDataModel(R.drawable.home_tab_approvel_process, "Onay Süreci"));
            listOfGridData.add(new GridViewDataModel(R.drawable.home_tab_isg_service, "ISG Hizmetleri"));
            listOfGridData.add(new GridViewDataModel(R.drawable.home_tab_education, "Eğitim"));
        } else {
            for (AddServiceListDataModel serviceListDataItem : serviceListDataModelList) {
                if (serviceListDataItem.isServiceIsOpen())
                    listOfGridData.add(new GridViewDataModel(serviceListDataItem.getServiceItemIcon(),
                            serviceListDataItem.getServiceItemTitle()));
            }
        }

        listOfGridData.add(new GridViewDataModel(R.drawable.home_tab_plus, "EKLE"));

        return listOfGridData;
    }

    //==============================================================================================
    @Override
    public List<AddServiceListDataModel> getGridData() {
        GenericClass<AddServiceListDataModel> genericClass = new GenericClass<>(getActivity().getApplicationContext());
        return genericClass.returnList(getActivity().getApplicationContext().getResources().getString(R.string.keyOfServiceListData), AddServiceListDataModel[].class);
    }

    //==============================================================================================
    @Override
    public void clickGridListItem() {
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goMenuItemActivity(position);
            }
        });
    }

    //==============================================================================================
    @Override
    public void goMenuItemActivity(int position) {
        String title = listOfGridData.get(position).getItemTitle();
        switch (title) {
            case "EKLE":
                startActivity(new Intent(getActivity().getApplicationContext(), AddServiceActivityViewImpl.class));
                break;
            default:
                Toast.makeText(getActivity().getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
        }
    }
}
