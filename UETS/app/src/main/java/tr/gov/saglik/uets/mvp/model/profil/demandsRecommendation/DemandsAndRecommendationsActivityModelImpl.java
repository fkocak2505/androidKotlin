package tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.requestParams.DemandsAndRecommendationsParams;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4DemandsAndRecommendation;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class DemandsAndRecommendationsActivityModelImpl implements IDemandsAndRecommendationsActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    /// Request Param
    DemandsAndRecommendationsParams demandsAndRecommendationsParams;

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
     * Set DemandAndRecommendation Param..
     * @param studentPerfectionId
     */
    //==============================================================================================
    @Override
    public void setDemandsAndRecommendationsParams(String studentPerfectionId) {
        demandsAndRecommendationsParams = new DemandsAndRecommendationsParams();
        demandsAndRecommendationsParams.setName("");
        demandsAndRecommendationsParams.setDelete(false);
        demandsAndRecommendationsParams.setDefaultValue("");
        demandsAndRecommendationsParams.setStudentPerfectionMainID(studentPerfectionId);
    }

    //==============================================================================================

    /**
     * Model Request 4 DemandAndRecommendation..
     * @param studentPerfectionId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getDemandsAndRecommendationData(String studentPerfectionId, final RequestResultListener requestResultListener) {
        initApiService();

        setDemandsAndRecommendationsParams(studentPerfectionId);

        apiService.demandsAndRecommendation("bearer " + UETSSingletonPattern.getInstance().getToken(),"false","Android", RequestBody.create(MediaType.parse("application/json"), gson.toJson(demandsAndRecommendationsParams))).enqueue(new Callback<Response4DemandsAndRecommendation>() {
            @Override
            public void onResponse(Call<Response4DemandsAndRecommendation> call, Response<Response4DemandsAndRecommendation> response) {
                if (response.isSuccessful())
                    requestResultListener.onSuccess(response);
                else
                    requestResultListener.onFail();
            }

            @Override
            public void onFailure(Call<Response4DemandsAndRecommendation> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }


}
