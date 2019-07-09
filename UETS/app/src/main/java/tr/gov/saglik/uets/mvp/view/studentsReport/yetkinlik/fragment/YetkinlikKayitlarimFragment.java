package tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.YetkinlikKayitlarimDataModel;
import tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.fragment.adapter.YetkinlikKayitlarimListViewAdapter;

public class YetkinlikKayitlarimFragment extends Fragment implements IYetkinlikKayitlarimFragmentView {

    ListView yetkinlikKayitlarimListView;
    List<YetkinlikKayitlarimDataModel> listOfYetkinlikKayitlarim;

    public YetkinlikKayitlarimFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_yetkinlik_kayitlarim, container, false);

        initYetkinlikKayitlarimFragmentComponent(view);
        fillYetkinlikKayitlarimListViewData();
        bindYetkinlikKayitlarimData2ListViewAdapter();

        return view;
    }


    @Override
    public void initYetkinlikKayitlarimFragmentComponent(View view) {
        yetkinlikKayitlarimListView = (ListView) view.findViewById(R.id.yetkinlikKayitlarimListView);
    }

    @Override
    public void fillYetkinlikKayitlarimListViewData() {
        listOfYetkinlikKayitlarim = new ArrayList<>();
    }

    @Override
    public void bindYetkinlikKayitlarimData2ListViewAdapter() {
        YetkinlikKayitlarimListViewAdapter yetkinlikKayitlarimListViewAdapter = new YetkinlikKayitlarimListViewAdapter(getActivity().getApplicationContext(), listOfYetkinlikKayitlarim);
        yetkinlikKayitlarimListView.setAdapter(yetkinlikKayitlarimListViewAdapter);
    }
}
