package tr.gov.acsgb.isgkatip.mvp.view.login;

import android.view.View;

public interface ILoginActivityView {

    void initLoginActivityComponent();

    void callNumber(View view);

    void requestStoragePermission();

    void clickLoginButton(View view);

    void goBack(View view);

    void showLoading();

    void hideLoading();

    void saveAuthKey(String authKey);

    void showError();

    void goAnotherActivity();

}
