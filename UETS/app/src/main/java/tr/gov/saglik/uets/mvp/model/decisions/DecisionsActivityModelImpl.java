package tr.gov.saglik.uets.mvp.model.decisions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.documents.responseModel.Response4DocumentList;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class DecisionsActivityModelImpl implements IDecisionsActivityModel {

    APIService apiService;

    @Override
    public void initApiService() {
        apiService = ApiUtils.getAPIService();
    }

    @Override
    public void decisionList(final RequestResultListener requestResultListener) {

        initApiService();

        apiService.decisionList("bearer " + UETSSingletonPattern.getInstance().getToken(),"Android").enqueue(new Callback<Response4DocumentList>() {
            @Override
            public void onResponse(Call<Response4DocumentList> call, Response<Response4DocumentList> response) {
                if(response.isSuccessful())
                    requestResultListener.onSuccess(response);
                else
                    requestResultListener.onFail();
            }

            @Override
            public void onFailure(Call<Response4DocumentList> call, Throwable t) {
                requestResultListener.onFail();
            }
        });

    }
}
