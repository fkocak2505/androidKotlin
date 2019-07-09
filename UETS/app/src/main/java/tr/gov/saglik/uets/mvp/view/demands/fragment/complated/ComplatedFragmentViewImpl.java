package tr.gov.saglik.uets.mvp.view.demands.fragment.complated;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.demands.dataModel.PendingApprovelAndComplatedModelData;
import tr.gov.saglik.uets.mvp.model.demands.responseModel.ValueOfDemands;
import tr.gov.saglik.uets.mvp.view.demands.adapter.PendingApprovelAndComplatedListViewAdapter;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class ComplatedFragmentViewImpl extends Fragment implements IComplatedFragmentView {

    ListView complatedListView;
    List<PendingApprovelAndComplatedModelData> listOfComplatedData;

    /// Complated Demands
    Map<String, List<ValueOfDemands>> mapOfSeperateDemands;
    List<ValueOfDemands> complatedDemands = new ArrayList<>();

    //==============================================================================================

    /**
     * Fragment Constructor
     */
    //==============================================================================================
    public ComplatedFragmentViewImpl() {
        // Required empty public constructor
    }


    //==============================================================================================

    /**
     * Demands Fragment onCreate Method
     *
     * @param savedInstanceState
     */
    //==============================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //==============================================================================================

    /**
     * Fragment onCreateView function...
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    //==============================================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_complated, container, false);

        initPendingApprovelFragmentComponent(view);
        fillPendingApprovelListViewData();
        bindData2ListViewAdapter();

        return view;
    }

    //==============================================================================================

    /**
     * Init Pending Approve Fragment Component
     *
     * @param view
     */
    //==============================================================================================
    @Override
    public void initPendingApprovelFragmentComponent(View view) {
        complatedListView = (ListView) view.findViewById(R.id.complatedListView);

        mapOfSeperateDemands = UETSSingletonPattern.getInstance().getDemandsList();
        complatedDemands = mapOfSeperateDemands.get("Complated");

    }

    //==============================================================================================

    /**
     * Fill ListView Data..
     */
    //==============================================================================================
    @Override
    public void fillPendingApprovelListViewData() {
        listOfComplatedData = new ArrayList<>();


            for(ValueOfDemands itemOfComplatedValue : complatedDemands) {
                listOfComplatedData.add(new PendingApprovelAndComplatedModelData(itemOfComplatedValue.getDemandId(),
                        itemOfComplatedValue.getCreateDate(),
                        R.drawable.demands_complated_status,
                        itemOfComplatedValue.getStatementName(),
                        getResources().getColor(R.color.complatedTextColor),
                        itemOfComplatedValue.getMemberNameSurname(),
                        itemOfComplatedValue.getDemandCategoryName()));
            }


        /*listOfComplatedData.add(new PendingApprovelAndComplatedModelData(1, "25/06/2019", R.drawable.demands_complated_status, "Onaylandı", getResources().getColor(R.color.complatedTextColor), "Fatih Koçak", "Anesteziyoloji"));
        listOfComplatedData.add(new PendingApprovelAndComplatedModelData(1, "24/06/2019", R.drawable.demands_complated_status, "Onaylandı", getResources().getColor(R.color.complatedTextColor), "Tülay Çetinkaya", "Anesteziyoloji"));
        listOfComplatedData.add(new PendingApprovelAndComplatedModelData(1, "23/06/2019", R.drawable.demands_complated_status, "Onaylandı", getResources().getColor(R.color.complatedTextColor), "umut Özgüven", "Anesteziyoloji"));
        listOfComplatedData.add(new PendingApprovelAndComplatedModelData(1, "22/06/2019", R.drawable.demands_complated_status, "Onaylandı", getResources().getColor(R.color.complatedTextColor), "Mehmet Canıtez", "Anesteziyoloji"));
        listOfComplatedData.add(new PendingApprovelAndComplatedModelData(1, "21/06/2019", R.drawable.demands_complated_status, "Onaylandı", getResources().getColor(R.color.complatedTextColor), "Aynur Koçak", "Anesteziyoloji"));
        listOfComplatedData.add(new PendingApprovelAndComplatedModelData(1, "20/06/2019", R.drawable.demands_complated_status, "Onaylandı", getResources().getColor(R.color.complatedTextColor), "Demet Veske", "Anesteziyoloji"));*/


        bindData2ListViewAdapter();

    }

    //==============================================================================================

    /**
     * Bind ListView Adapter Data
     */
    //==============================================================================================
    @Override
    public void bindData2ListViewAdapter() {
        PendingApprovelAndComplatedListViewAdapter pendingApprovelAndComplatedListViewAdapter = new PendingApprovelAndComplatedListViewAdapter(getActivity().getApplicationContext(), listOfComplatedData);
        complatedListView.setAdapter(pendingApprovelAndComplatedListViewAdapter);
    }
}