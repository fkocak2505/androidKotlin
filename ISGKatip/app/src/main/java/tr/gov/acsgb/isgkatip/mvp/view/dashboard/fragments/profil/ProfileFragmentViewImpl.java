package tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.profil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tr.gov.acsgb.isgkatip.R;

public class ProfileFragmentViewImpl extends Fragment {

    //==============================================================================================
    public ProfileFragmentViewImpl() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false);
    }
}
