package tr.gov.acsgb.isgkatip.mvp.model.login;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.acsgb.isgkatip.RequestResultListener;
import tr.gov.acsgb.isgkatip.api.ApiUtils;
import tr.gov.acsgb.isgkatip.api.interfaced.APIService;
import tr.gov.acsgb.isgkatip.mvp.model.login.requestModel.LoginParam;
import tr.gov.acsgb.isgkatip.mvp.model.login.responseModel.ResponseLoginData;

public class LoginActivityModelImpl implements ILoginActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    // Login Request Param
    LoginParam loginParam;


    //==============================================================================================
    @Override
    public void initApiService(String password, int rememberMe, String userName) {
        apiService = ApiUtils.getAPIService();

        loginParam = new LoginParam();
        loginParam.setPassword(password);
        loginParam.setRememberMe(rememberMe);
        loginParam.setUsername(userName);
    }

    //==============================================================================================
    @Override
    public void doLogin(String password, int rememberMe, String userName, final RequestResultListener requestResultListener) {

        initApiService(password,rememberMe,userName);

        apiService.savePostWithPojoModel(RequestBody.create(MediaType.parse("application/json"),gson.toJson(loginParam))).enqueue(new Callback<ResponseLoginData>() {
            @Override
            public void onResponse(Call<ResponseLoginData> call, Response<ResponseLoginData> response) {
                if(response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<ResponseLoginData> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }
}
