package tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.notificaion;

import android.view.View;

public interface INotificationFragmentView {

    void initNotificationFragmentComponent(View view);
    void fillNotificationListData();
    void bindNotificationListData2Adapter();

}
