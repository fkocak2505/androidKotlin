package tr.gov.saglik.uets.mvp.view.welcome;

import android.view.View;

public interface IWelcomeActivityView {

    void initWelcomeActivityComponent();

    void setWelcomeGridMenuData();

    void goLogin(View view);

    void goRegister(View view);

    void showLoading();

    void hideLoading(String messages);

}
