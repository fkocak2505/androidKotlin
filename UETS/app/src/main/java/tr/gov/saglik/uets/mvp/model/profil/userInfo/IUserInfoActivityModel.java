package tr.gov.saglik.uets.mvp.model.profil.userInfo;

import tr.gov.saglik.uets.RequestResultListener;

public interface IUserInfoActivityModel {

    void initApiService();

    void setUserInfoData(String id);

    void getUserInfo(String id, RequestResultListener requestResultListener);

}
