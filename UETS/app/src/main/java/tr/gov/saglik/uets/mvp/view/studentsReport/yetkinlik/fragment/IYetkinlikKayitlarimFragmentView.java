package tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.fragment;

import android.view.View;

public interface IYetkinlikKayitlarimFragmentView {

    void initYetkinlikKayitlarimFragmentComponent(View view);
    void fillYetkinlikKayitlarimListViewData();
    void bindYetkinlikKayitlarimData2ListViewAdapter();

}
