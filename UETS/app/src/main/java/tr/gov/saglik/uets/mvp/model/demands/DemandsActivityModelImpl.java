package tr.gov.saglik.uets.mvp.model.demands;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.demands.requestModel.DemandsOperationTaskParams;
import tr.gov.saglik.uets.mvp.model.demands.requestModel.DemandsParams4TaskOperation;
import tr.gov.saglik.uets.mvp.model.demands.requestModel.Demandsparams;
import tr.gov.saglik.uets.mvp.model.demands.responseModel.Response4Demands;

public class DemandsActivityModelImpl implements IDemandsActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    /// Request Param
    Demandsparams demandsparams;
    DemandsParams4TaskOperation demandsParams4TaskOperation;
    DemandsOperationTaskParams demandsOperationTaskParams;


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
     * Model Request 4 Demand List
     * @param memberId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getDemandsList(int memberId, final RequestResultListener requestResultListener) {

        initApiService();

        setDemandsParam(memberId);

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.demandsList(authKey, RequestBody.create(MediaType.parse("application/json"), gson.toJson(demandsparams))).enqueue(new Callback<Response4Demands>() {
            @Override
            public void onResponse(Call<Response4Demands> call, Response<Response4Demands> response) {
                if (response.isSuccessful())
                    requestResultListener.onSuccess(response);
                else
                    requestResultListener.onFail();
            }

            @Override
            public void onFailure(Call<Response4Demands> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }

    //==============================================================================================

    /**
     * Model Request 4 Task Operation
     * @param demandId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getTaskOperationListByDemandId(int demandId, final RequestResultListener requestResultListener) {
        initApiService();

        setDemandTaskOperationListByDemandId(demandId);

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.taskOperationListByDemandId(authKey, RequestBody.create(MediaType.parse("application/json"), gson.toJson(demandsParams4TaskOperation))).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                    requestResultListener.onSuccess(response);
                else
                    requestResultListener.onFail();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                requestResultListener.onFail();
            }
        });

    }

    //==============================================================================================

    /**
     * Model Request 4 Operation Task
     * @param demandId
     * @param memberId
     * @param taskOperationId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void operationTask(int demandId, int memberId, int taskOperationId, final RequestResultListener requestResultListener) {
        initApiService();

        setOperationTaskParams(demandId, memberId, taskOperationId);

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.operationTask(authKey, RequestBody.create(MediaType.parse("application/json"), gson.toJson(demandsParams4TaskOperation))).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                    requestResultListener.onSuccess(response);
                else
                    requestResultListener.onFail();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }

    //==============================================================================================

    /**
     * Set Demand Params
     * @param memberId
     */
    //==============================================================================================
    @Override
    public void setDemandsParam(int memberId) {
        demandsparams = new Demandsparams();
        demandsparams.setMemberId(memberId);
    }

    //==============================================================================================

    /**
     * Set Demand And Task operation Params..
     * @param demandId
     */
    //==============================================================================================
    @Override
    public void setDemandTaskOperationListByDemandId(int demandId) {
        demandsParams4TaskOperation = new DemandsParams4TaskOperation();
        demandsParams4TaskOperation.setDemandId(demandId);
    }

    //==============================================================================================

    /**
     * Set operation Task Params..
     * @param demandId
     * @param memberId
     * @param taskOperationId
     */
    //==============================================================================================
    @Override
    public void setOperationTaskParams(int demandId, int memberId, int taskOperationId) {
        demandsOperationTaskParams = new DemandsOperationTaskParams();
        demandsOperationTaskParams.setDemandId(demandId);
        demandsOperationTaskParams.setMemberId(memberId);
        demandsOperationTaskParams.setTaskOperationId(taskOperationId);
    }
}
