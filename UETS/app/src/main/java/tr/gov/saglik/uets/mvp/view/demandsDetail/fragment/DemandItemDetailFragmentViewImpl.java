package tr.gov.saglik.uets.mvp.view.demandsDetail.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tr.gov.saglik.uets.R;

public class DemandItemDetailFragmentViewImpl extends Fragment {

    public DemandItemDetailFragmentViewImpl() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_demands_detail,container,false);


        return view;
    }

}
