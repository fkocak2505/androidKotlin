package tr.gov.acsgb.isgkatip.mvp.model.login;

import tr.gov.acsgb.isgkatip.RequestResultListener;

public interface ILoginActivityModel {
    void initApiService(String password, int rememberMe, String userName);

    void doLogin(String password, int rememberMe, String userName, RequestResultListener requestResultListener);

}
