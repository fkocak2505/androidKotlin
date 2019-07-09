package tr.gov.saglik.uets.mvp.view.demands.fragment.pendingApprovel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.demands.dataModel.PendingApprovelAndComplatedModelData;
import tr.gov.saglik.uets.mvp.model.demands.responseModel.ValueOfDemands;
import tr.gov.saglik.uets.mvp.view.demands.adapter.PendingApprovelAndComplatedListViewAdapter;
import tr.gov.saglik.uets.mvp.view.demandsDetail.DemandsDetailActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class PendingApprovelFragmentViewImpl extends Fragment implements IPendingApprovelFragmentView {

    ListView pendingApprovelListView;
    List<PendingApprovelAndComplatedModelData> listOfPendingApprovelData;

    /// Complated Demands
    Map<String ,List<ValueOfDemands>> mapOfSeperateDemands;
    List<ValueOfDemands> assignmentDemands = new ArrayList<>();


    //==============================================================================================

    /**
     * Fragment Constructor..
     */
    //==============================================================================================
    public PendingApprovelFragmentViewImpl() {
        // Required empty public constructor
    }

    //==============================================================================================

    /**
     * Fragment OnCreate Method..
     * @param savedInstanceState
     */
    //==============================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //==============================================================================================

    /**
     * Fragment onCreateView function
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    //==============================================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pending_approvel, container, false);

        initPendingApprovelFragmentComponent(view);
        fillPendingApprovelListViewData();
        bindData2ListViewAdapter();
        clickPendingApprovalItem();

        return view;
    }

    //==============================================================================================

    /**
     * Init Fragment Component's
     * @param view
     */
    //==============================================================================================
    @Override
    public void initPendingApprovelFragmentComponent(View view) {
        pendingApprovelListView = (ListView) view.findViewById(R.id.pendingApprovelListView);

        mapOfSeperateDemands = UETSSingletonPattern.getInstance().getDemandsList();
        assignmentDemands = mapOfSeperateDemands.get("Assignment");

    }

    //==============================================================================================

    /**
     * Fill ListView Data..
     */
    //==============================================================================================
    @Override
    public void fillPendingApprovelListViewData() {
        listOfPendingApprovelData = new ArrayList<>();

        if(assignmentDemands.size() != 0){
            for(ValueOfDemands itemOfApprovelValue : assignmentDemands){
                listOfPendingApprovelData.add(new PendingApprovelAndComplatedModelData(itemOfApprovelValue.getDemandId(),
                        itemOfApprovelValue.getCreateDate(),
                        R.drawable.demands_pending_approvel_status,
                        itemOfApprovelValue.getStatementName(),
                        getResources().getColor(R.color.pendingApprovelTextColor),
                        itemOfApprovelValue.getMemberNameSurname(),
                        itemOfApprovelValue.getDemandCategoryName()));
            }
        } else {
            listOfPendingApprovelData.add(new PendingApprovelAndComplatedModelData(1, "25/06/2019", R.drawable.demands_pending_approvel_status, "Onaylama Bekliyor", getResources().getColor(R.color.pendingApprovelTextColor), "Fatih Koçak", "Anesteziyoloji"));
            listOfPendingApprovelData.add(new PendingApprovelAndComplatedModelData(2, "24/06/2019", R.drawable.demands_pending_approvel_status, "Onaylama Bekliyor", getResources().getColor(R.color.pendingApprovelTextColor), "Umut Özgüven", "Anesteziyoloji"));
            listOfPendingApprovelData.add(new PendingApprovelAndComplatedModelData(3, "23/06/2019", R.drawable.demands_pending_approvel_status, "Onaylama Bekliyor", getResources().getColor(R.color.pendingApprovelTextColor), "Tülay Çetinkaya", "Anesteziyoloji"));
            listOfPendingApprovelData.add(new PendingApprovelAndComplatedModelData(4, "22/06/2019", R.drawable.demands_pending_approvel_status, "Onaylama Bekliyor", getResources().getColor(R.color.pendingApprovelTextColor), "Mehmet Canıtez", "Anesteziyoloji"));
            listOfPendingApprovelData.add(new PendingApprovelAndComplatedModelData(3, "23/06/2019", R.drawable.demands_pending_approvel_status, "Onaylama Bekliyor", getResources().getColor(R.color.pendingApprovelTextColor), "Aynur Koçak", "Anesteziyoloji"));
            listOfPendingApprovelData.add(new PendingApprovelAndComplatedModelData(4, "22/06/2019", R.drawable.demands_pending_approvel_status, "Onaylama Bekliyor", getResources().getColor(R.color.pendingApprovelTextColor), "Demet Veske", "Anesteziyoloji"));
        }



        bindData2ListViewAdapter();

    }

    //==============================================================================================

    /**
     * Bind ListView Data...
     */
    //==============================================================================================
    @Override
    public void bindData2ListViewAdapter() {
        PendingApprovelAndComplatedListViewAdapter pendingApprovelAndComplatedListViewAdapter = new PendingApprovelAndComplatedListViewAdapter(getActivity().getApplicationContext(), listOfPendingApprovelData);
        pendingApprovelListView.setAdapter(pendingApprovelAndComplatedListViewAdapter);
    }

    //==============================================================================================

    /**
     * Click ListView Item...
     */
    //==============================================================================================
    @Override
    public void clickPendingApprovalItem() {
        pendingApprovelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UETSSingletonPattern.getInstance().setDemandId(listOfPendingApprovelData.get(position).getDemandId());
                startActivity(new Intent(getActivity().getApplicationContext(), DemandsDetailActivityViewImpl.class));
            }
        });
    }
}
