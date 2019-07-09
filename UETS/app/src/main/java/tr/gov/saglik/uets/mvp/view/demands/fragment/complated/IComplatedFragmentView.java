package tr.gov.saglik.uets.mvp.view.demands.fragment.complated;

import android.view.View;

public interface IComplatedFragmentView {

    void initPendingApprovelFragmentComponent(View view);
    void fillPendingApprovelListViewData();
    void bindData2ListViewAdapter();

}
