package tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tr.gov.saglik.uets.R;

public class YetkinlikBilgileriFragment extends Fragment implements IYetkinlikBilgileriFragment {

    TextView yetkinlikAdi;
    TextView profession;
    TextView yetkinliginAlani;
    TextView yetkinlikDuzeyi;
    TextView focusable;
    TextView yetkinlikTuru;


    public YetkinlikBilgileriFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_yetkinlik_bilgileri, container, false);

        initYetkinlikBilgileriFragmentComponent(view);
        fillYetkinlikBilgileriData();


        return view;
    }


    @Override
    public void initYetkinlikBilgileriFragmentComponent(View view) {
        yetkinlikAdi = (TextView) view.findViewById(R.id.yetkinlikAdi);
        profession = (TextView) view.findViewById(R.id.profession);
        yetkinliginAlani = (TextView) view.findViewById(R.id.yetkinliginAlani);
        yetkinlikDuzeyi = (TextView) view.findViewById(R.id.yetkinlikDuzeyi);
        focusable = (TextView) view.findViewById(R.id.focusable);
        yetkinlikTuru = (TextView) view.findViewById(R.id.yetkinlikTuru);
    }

    @Override
    public void fillYetkinlikBilgileriData() {
        yetkinlikAdi.setText("Burr Hole");
        profession.setText("Beyin ve Sinir Cerrahisi");
        yetkinliginAlani.setText("Uzmanlık Dalı Yetkinliği");
        yetkinlikDuzeyi.setText("4");
        focusable.setText("Zorunlu");
        yetkinlikTuru.setText("Girişimsel Yetkinlik");
    }
}
