package tr.gov.saglik.uets.mvp.model.login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.login.responseModel.Response4Login;

public class LoginActivityModelImpl implements ILoginActivityModel {

    private APIService apiService;

    //==============================================================================================

    /**
     * Init Retrofir API Service
     */
    //==============================================================================================
    public void initApiService() {
        apiService = ApiUtils.getAPIService();
    }

    //==============================================================================================

    /**
     * Model Request 4 Login
     * @param userName
     * @param password
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void doLogin(String userName, String password, final RequestResultListener requestResultListener) {

        initApiService();

        apiService.token("password", userName, password).enqueue(new Callback<Response4Login>() {
            @Override
            public void onResponse(Call<Response4Login> call, Response<Response4Login> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onUnSuccess(response);
                }
            }

            @Override
            public void onFailure(Call<Response4Login> call, Throwable t) {
                requestResultListener.onFail();
            }
        });


    }
}
