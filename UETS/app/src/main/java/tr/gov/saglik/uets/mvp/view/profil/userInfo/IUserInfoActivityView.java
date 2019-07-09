package tr.gov.saglik.uets.mvp.view.profil.userInfo;

import tr.gov.saglik.uets.mvp.model.profil.userInfo.responseModel.Response4UserInfoData;

public interface IUserInfoActivityView {

    void initUserInfoActivityComponent();

    void getUserInfoData();

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void sendData2ActivityView(Response4UserInfoData response4UserInfoData);

    void clickGoBack();

    void clickHomeScreen();

}
