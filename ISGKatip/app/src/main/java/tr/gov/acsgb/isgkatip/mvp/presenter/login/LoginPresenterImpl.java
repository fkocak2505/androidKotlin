package tr.gov.acsgb.isgkatip.mvp.presenter.login;

import retrofit2.Response;
import tr.gov.acsgb.isgkatip.RequestResultListener;
import tr.gov.acsgb.isgkatip.mvp.model.login.ILoginActivityModel;
import tr.gov.acsgb.isgkatip.mvp.view.login.ILoginActivityView;

public class LoginPresenterImpl implements ILoginPresenter {

    private ILoginActivityModel iLoginActivityModel;
    private ILoginActivityView iLoginActivityView;

    //==============================================================================================
    public LoginPresenterImpl(ILoginActivityModel iLoginActivityModel, ILoginActivityView iLoginActivityView) {
        this.iLoginActivityModel = iLoginActivityModel;
        this.iLoginActivityView = iLoginActivityView;
    }


    //==============================================================================================
    @Override
    public void login(String password, int rememberMe, String username) {
        iLoginActivityView.showLoading();
        iLoginActivityModel.doLogin(password, rememberMe, username, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iLoginActivityView.saveAuthKey(response.headers().get("Authorization"));
                iLoginActivityView.hideLoading();
                iLoginActivityView.goAnotherActivity();
            }

            @Override
            public void onFail() {
                iLoginActivityView.showError();
            }
        });
    }
}
