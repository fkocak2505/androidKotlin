package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.detail;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.detail.requestModel.YetkinlikKayitlarimParam;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.detail.responseModel.ResponseYetkinlikKayitlarim;

public class YetkinlikKayitlarimActivityModelImpl implements IYetkinlikKayitlarimActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    /// Request Param
    YetkinlikKayitlarimParam yetkinlikKayitlarimParam;

    //==============================================================================================

    /**
     * Init Retrofit API Service
     * @param memberId
     */
    //==============================================================================================
    @Override
    public void initApiService(int memberId) {
        apiService = ApiUtils.getAPIService();

        yetkinlikKayitlarimParam = new YetkinlikKayitlarimParam();
        yetkinlikKayitlarimParam.setName("");
        yetkinlikKayitlarimParam.setIsDelete(false);
        yetkinlikKayitlarimParam.setDefaultValue("");
        yetkinlikKayitlarimParam.setMemberId(memberId);

    }

    //==============================================================================================

    /**
     * Model Request 4 yetkinlik Kayıtlarım..
     * @param memberId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getYetkinlikKayitlarim(int memberId, final RequestResultListener requestResultListener) {
        initApiService(memberId);

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.getYetkinlikKayitlarim(authKey, "true","ANDROID", RequestBody.create(MediaType.parse("application/json"), gson.toJson(yetkinlikKayitlarimParam))).enqueue(new Callback<ResponseYetkinlikKayitlarim>() {
            @Override
            public void onResponse(Call<ResponseYetkinlikKayitlarim> call, Response<ResponseYetkinlikKayitlarim> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<ResponseYetkinlikKayitlarim> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }

}
