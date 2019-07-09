package tr.gov.saglik.uets.mvp.model.studentReport.thesis;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.studentReport.thesis.requestParam.ThesisParams;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class ThesisActivityModelImpl implements IThesisActivityModel {

    /// Api Service
    APIService apiService;
    Gson gson = new Gson();

    /// Params
    ThesisParams thesisParams;

    @Override
    public void initApiService() {
        apiService = ApiUtils.getAPIService();
    }

    @Override
    public void setParamsOfThesisData(String studentId) {
        thesisParams = new ThesisParams();
        thesisParams.setStudentId(studentId);
    }

    @Override
    public void getThesis(String studentId, final RequestResultListener requestResultListener) {

        initApiService();

        setParamsOfThesisData(studentId);

        apiService.thesis("bearer " + UETSSingletonPattern.getInstance().getToken(), "Android",RequestBody.create(MediaType.parse("application/json"), gson.toJson(thesisParams))).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
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
}
