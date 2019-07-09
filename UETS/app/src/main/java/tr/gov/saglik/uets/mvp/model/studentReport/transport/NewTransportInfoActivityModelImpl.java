package tr.gov.saglik.uets.mvp.model.studentReport.transport;

import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.requestModel.NewTransportTransferParams;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.requestModel.SaveTransportParams;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4NewTransportTransferType;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4SaveTransport;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4TransferProgram;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class NewTransportInfoActivityModelImpl implements INewTransportInfoActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    /// Request Param
    NewTransportTransferParams newTransportTransferParams;
    SaveTransportParams saveTransportParams;

    @Override
    public void initApiService() {
        apiService = ApiUtils.getAPIService();
    }

    @Override
    public void setParamOfNewTransportTransferTypeData(String code) {
        newTransportTransferParams = new NewTransportTransferParams();
        newTransportTransferParams.setGeneralCode(code);
    }

    @Override
    public void setParamOfSaveTransport(String transferTypeId, String transferReasonId, String programId, String content) {
        saveTransportParams = new SaveTransportParams();
        saveTransportParams.setTransferTypeId(transferTypeId);
        saveTransportParams.setTransferReasonId(transferReasonId);
        saveTransportParams.setProgramId(programId);
        saveTransportParams.setAdditionalNote(content);
    }

    @Override
    public void getNewTransportTypeData(String code, final RequestResultListener requestResultListener) {

        initApiService();

        setParamOfNewTransportTransferTypeData(code);

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.newTransportTransferType(authKey, "Android", RequestBody.create(MediaType.parse("application/json"), gson.toJson(newTransportTransferParams))).enqueue(new Callback<Response4NewTransportTransferType>() {
            @Override
            public void onResponse(Call<Response4NewTransportTransferType> call, Response<Response4NewTransportTransferType> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<Response4NewTransportTransferType> call, Throwable t) {
                requestResultListener.onFail();
            }
        });

    }

    @Override
    public void getNewTransportProgramData(final RequestResultListener requestResultListener) {
        initApiService();

        apiService.newTransportProgramData("Android").enqueue(new Callback<Response4TransferProgram>() {
            @Override
            public void onResponse(Call<Response4TransferProgram> call, Response<Response4TransferProgram> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<Response4TransferProgram> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }

    @Override
    public void saveTransport(String transferTypeId, String transferReasonId, String programId, String content, final RequestResultListener requestResultListener) {
        initApiService();

        setParamOfSaveTransport(transferTypeId,transferReasonId,programId,content);

        apiService.saveTransport("bearer " + UETSSingletonPattern.getInstance().getToken(),"Android", RequestBody.create(MediaType.parse("application/json"), gson.toJson(saveTransportParams))).enqueue(new Callback<Response4SaveTransport>() {
            @Override
            public void onResponse(Call<Response4SaveTransport> call, Response<Response4SaveTransport> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<Response4SaveTransport> call, Throwable t) {
                requestResultListener.onFail();
            }
        });


    }
}
