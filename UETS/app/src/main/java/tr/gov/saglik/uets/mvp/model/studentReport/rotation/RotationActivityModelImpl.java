package tr.gov.saglik.uets.mvp.model.studentReport.rotation;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.studentReport.rotation.requesteModel.RotationDetailListParam;
import tr.gov.saglik.uets.mvp.model.studentReport.rotation.requesteModel.RotationListParam;
import tr.gov.saglik.uets.mvp.model.studentReport.rotation.responseModel.Response4RotationList;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class RotationActivityModelImpl implements IRotationActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    /// Request Param
    RotationListParam rotationListParam;
    RotationDetailListParam rotationDetailListParam;

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
     * Set Params 4 Rotation List
     * @param studentId
     */
    //==============================================================================================
    @Override
    public void setRotationList(int studentId) {
        rotationListParam = new RotationListParam();
        rotationListParam.setStudentId(studentId);
    }

    //==============================================================================================

    /**
     * Set Params 4 Rotation Detail List..
     * @param rotationId
     */
    //==============================================================================================
    @Override
    public void setRotationDetailParams(String rotationId) {
        rotationDetailListParam = new RotationDetailListParam();
        rotationDetailListParam.setRotationId(rotationId);

    }

    //==============================================================================================

    /**
     * Model Req 4 Rotatin List Data..
     * @param studentId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getRotationList(int studentId, final RequestResultListener requestResultListener) {

        initApiService();

        setRotationList(studentId);

        apiService.rotationList("bearer " + UETSSingletonPattern.getInstance().getToken(), "Android", RequestBody.create(MediaType.parse("application/json"), gson.toJson(rotationListParam))).enqueue(new Callback<Response4RotationList>() {
            @Override
            public void onResponse(Call<Response4RotationList> call, Response<Response4RotationList> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<Response4RotationList> call, Throwable t) {
                requestResultListener.onFail();
            }
        });

    }

    //==============================================================================================

    /**
     * Model Req 4 Rotation Detail List Data..
     * @param rotationId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getRotationDetailList(String rotationId, final RequestResultListener requestResultListener) {
        initApiService();

        setRotationDetailParams(rotationId);

        apiService.rotationDetailList("bearer " + UETSSingletonPattern.getInstance().getToken(), "Android", RequestBody.create(MediaType.parse("application/json"), gson.toJson(rotationDetailListParam))).enqueue(new Callback<Response4RotationList>() {
            @Override
            public void onResponse(Call<Response4RotationList> call, Response<Response4RotationList> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<Response4RotationList> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }
}
