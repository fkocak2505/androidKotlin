package tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.messages;

import android.view.View;

public interface IMessagesFragmentView {

    void initMessagesFragmentComponent(View view);

    void clickFabNewButton();

    void fillMessagesListData();

    void bindMessagesListData2Adapter();

}
