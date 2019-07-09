package tr.gov.saglik.uets.mvp.presenter.login;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.login.ILoginActivityModel;
import tr.gov.saglik.uets.mvp.model.login.responseModel.Response4Login;
import tr.gov.saglik.uets.mvp.model.login.responseModel.Response4LoginError;
import tr.gov.saglik.uets.mvp.view.login.ILoginActivityView;

public class LoginActivityPresenterImpl implements ILoginActivityPresenter {

    private ILoginActivityModel iLoginActivityModel;
    private ILoginActivityView iLoginActivityView;

    //==============================================================================================

    /**
     * Presenter Constructor 4 Login
     * @param iLoginActivityModel
     * @param iLoginActivityView
     */
    //==============================================================================================
    public LoginActivityPresenterImpl(ILoginActivityModel iLoginActivityModel, ILoginActivityView iLoginActivityView) {
        this.iLoginActivityModel = iLoginActivityModel;
        this.iLoginActivityView = iLoginActivityView;
    }

    //==============================================================================================

    /**
     * Presenter Request 4 Login 2 Model
     * @param userName
     * @param password
     */
    //==============================================================================================
    @Override
    public void doLogin(String userName, String password) {
        iLoginActivityView.showLoading();
        iLoginActivityModel.doLogin(userName, password, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iLoginActivityView.hideLoading();
                iLoginActivityView.goDemandsActivity((Response4Login) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {
                Gson gson = new Gson();
                Response4LoginError message = gson.fromJson(response.errorBody().charStream(), Response4LoginError.class);

                iLoginActivityView.hideLoading();
                iLoginActivityView.errorLogin(message);

            }

            @Override
            public void onFail() {
                iLoginActivityView.hideLoading();
            }
        });
    }
}
