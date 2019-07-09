package tr.gov.saglik.uets.mvp.model.studentReport.transport;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.requestModel.TransportInfoDataParams;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4TransportInfoData;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class TransportInfoActivityModelImpl implements ITransportInfoActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    /// Request Param
    TransportInfoDataParams transportInfoDataParams;

    //==============================================================================================

    /**
     * Init Retroofit API Service 4 82 Port..
     */
    //==============================================================================================
    @Override
    public void initApiService() {
        apiService = ApiUtils.getAPIService();
    }

    //==============================================================================================

    /**
     * Model Request 4 Transport Data..
     * @param studenId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getTransportInfoData(int studenId, final RequestResultListener requestResultListener) {

        initApiService();

        setTransportInfoParam(studenId);

        apiService.transportInfoData("bearer " + UETSSingletonPattern.getInstance().getToken(), "Android", RequestBody.create(MediaType.parse("application/json"), gson.toJson(transportInfoDataParams))).enqueue(new Callback<Response4TransportInfoData>() {
            @Override
            public void onResponse(Call<Response4TransportInfoData> call, Response<Response4TransportInfoData> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<Response4TransportInfoData> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }

    //==============================================================================================

    /**
     * Set Transport Prams Data 4 this Request..
     * @param studenId
     */
    //==============================================================================================
    @Override
    public void setTransportInfoParam(int studenId) {
        transportInfoDataParams = new TransportInfoDataParams();
        transportInfoDataParams.setStudentId(studenId);
    }
}
