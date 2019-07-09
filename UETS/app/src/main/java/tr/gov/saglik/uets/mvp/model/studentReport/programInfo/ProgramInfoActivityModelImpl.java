package tr.gov.saglik.uets.mvp.model.studentReport.programInfo;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.studentReport.programInfo.requestModel.ProgramInfoRequestParam;
import tr.gov.saglik.uets.mvp.model.studentReport.programInfo.responseModel.Response4ProgramInfo;

public class ProgramInfoActivityModelImpl implements IProgramInfoActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    /// Request Param
    ProgramInfoRequestParam programInfoRequestParam;

    //==============================================================================================

    /**
     * Init Retrofit API Service
     */
    //==============================================================================================
    @Override
    public void initApiService() {
        apiService = ApiUtils.getAPIService();
    }

    //==============================================================================================

    /**
     * Set Program Info Param
     * @param memberId
     */
    //==============================================================================================
    @Override
    public void setProgramInfoParam(int memberId) {
        programInfoRequestParam = new ProgramInfoRequestParam();
        programInfoRequestParam.setMemberId(memberId);
    }

    //==============================================================================================

    /**
     * Model Request 4 Program Info
     * @param memberId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getProgramInfo(int memberId, final RequestResultListener requestResultListener) {
        initApiService();

        setProgramInfoParam(memberId);

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.programInfo(authKey, "true", "Android", RequestBody.create(MediaType.parse("application/json"), gson.toJson(programInfoRequestParam))).enqueue(new Callback<Response4ProgramInfo>() {
            @Override
            public void onResponse(Call<Response4ProgramInfo> call, Response<Response4ProgramInfo> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<Response4ProgramInfo> call, Throwable t) {
                requestResultListener.onFail();
            }
        });

    }

}
