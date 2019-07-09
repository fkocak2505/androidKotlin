package tr.gov.acsgb.isgkatip.mvp.view.welcome;

import android.view.View;

public interface IWelcomeActivityView {

    void initWelcomeActivityComponent();

    void fillWelcomeGridViewData();

    void bindWelcomeGridData2Adapter();

    void clickWelcomeGridViewItem();

    void callNumber(View view);

    void requestCallPhonePermission();

    void clickLoginButton(View view);

    void goSelectedActivity(int position);


}
