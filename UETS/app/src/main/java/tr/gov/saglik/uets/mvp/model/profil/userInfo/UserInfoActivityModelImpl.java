package tr.gov.saglik.uets.mvp.model.profil.userInfo;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.profil.userInfo.requestModel.UserInfoDataParams;
import tr.gov.saglik.uets.mvp.model.profil.userInfo.responseModel.Response4UserInfoData;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class UserInfoActivityModelImpl implements IUserInfoActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    /// Request Param
    UserInfoDataParams userInfoDataParams;

    //==============================================================================================

    /**
     * Init Retrofit ApiService..
     */
    //==============================================================================================
    @Override
    public void initApiService() {
        apiService = ApiUtils.getAPIService();
    }

    //==============================================================================================

    /**
     * Set Params 4 UserInfo
     * @param id
     */
    //==============================================================================================
    @Override
    public void setUserInfoData(String id) {
        userInfoDataParams = new UserInfoDataParams();
        userInfoDataParams.setId(id);
    }

    //==============================================================================================

    /**
     * Model Req 4 UserInfo
     * @param id
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getUserInfo(String id, final RequestResultListener requestResultListener) {

        initApiService();

        setUserInfoData(id);

        apiService.userInfoData("bearer " + UETSSingletonPattern.getInstance().getToken(), "Android", RequestBody.create(MediaType.parse("application/json"), gson.toJson(userInfoDataParams))).enqueue(new Callback<Response4UserInfoData>() {
            @Override
            public void onResponse(Call<Response4UserInfoData> call, Response<Response4UserInfoData> response) {
                if (response.isSuccessful())
                    requestResultListener.onSuccess(response);
                else
                    requestResultListener.onFail();
            }

            @Override
            public void onFailure(Call<Response4UserInfoData> call, Throwable t) {
                requestResultListener.onFail();
            }
        });

    }
}
