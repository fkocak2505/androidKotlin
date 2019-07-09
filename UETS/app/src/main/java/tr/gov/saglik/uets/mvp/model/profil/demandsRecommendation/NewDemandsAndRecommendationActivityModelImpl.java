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
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.requestParams.NewDemandsAndRecommendationsParams;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.requestParams.SaveDemandAndRecommendation;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4NewDemandAndRecommendationDemandType;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4SaveNewDemandAndRecommendation;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class NewDemandsAndRecommendationActivityModelImpl implements INewDemandsAndRecommendationActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    /// Request Param
    NewDemandsAndRecommendationsParams newDemandsAndRecommendationsParams;
    DemandsAndRecommendationsParams demandsAndRecommendationsParams;
    SaveDemandAndRecommendation saveDemandAndRecommendation;


    //==============================================================================================

    /**
     * Init Retrofir API Service
     */
    //==============================================================================================
    @Override
    public void initApiService() {
        apiService = ApiUtils.getAPIService();
    }

    //==============================================================================================

    /**
     * Set Demand Type Parameter Object..
     *
     * @param memberId
     * @param studentPerfectionId
     */
    //==============================================================================================
    @Override
    public void setNewDemandsAndRecommendationsParams(String memberId, String studentPerfectionId) {
        newDemandsAndRecommendationsParams = new NewDemandsAndRecommendationsParams();
        newDemandsAndRecommendationsParams.setDelete(false);
        newDemandsAndRecommendationsParams.setDefaultValue("");
        newDemandsAndRecommendationsParams.setName("");
        newDemandsAndRecommendationsParams.setMemberId(memberId);
        newDemandsAndRecommendationsParams.setStudentPerfectionMainID(studentPerfectionId);
    }

    //==============================================================================================

    /**
     * Set Member Group Parameter Object
     *
     * @param memberId
     */
    //==============================================================================================
    @Override
    public void setNewDemandsAndRecommendationsParams4MemberGroup(String memberId) {
        demandsAndRecommendationsParams = new DemandsAndRecommendationsParams();
        demandsAndRecommendationsParams.setDefaultValue("");
        demandsAndRecommendationsParams.setName("");
        demandsAndRecommendationsParams.setDelete(false);
        demandsAndRecommendationsParams.setStudentPerfectionMainID(memberId);
    }

    //==============================================================================================

    /**
     * Set New Demands Parameter Object
     *
     * @param content
     * @param subject
     * @param demandTypeId
     * @param memberGroupId
     */
    //==============================================================================================
    @Override
    public void saveNewDemandsAndRecommendationsParams(String content, String subject, int demandTypeId, int memberGroupId) {
        saveDemandAndRecommendation = new SaveDemandAndRecommendation();
        saveDemandAndRecommendation.setContent(content);
        saveDemandAndRecommendation.setSubject(subject);
        saveDemandAndRecommendation.setSuggestionDemandTypeId(demandTypeId);
        saveDemandAndRecommendation.setSendMemberGroupId(memberGroupId);
    }

    //==============================================================================================

    /**
     * Model Request 4 Demand Type 2 Model...
     *
     * @param memberId
     * @param studentPerfectionId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getNewDemandsAndRecommendationData(String memberId, String studentPerfectionId, final RequestResultListener requestResultListener) {

        initApiService();

        setNewDemandsAndRecommendationsParams(memberId, studentPerfectionId);

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.newDemandsAndRecommendationDemandType(authKey, "Android", RequestBody.create(MediaType.parse("application/json"), gson.toJson(newDemandsAndRecommendationsParams))).enqueue(new Callback<Response4NewDemandAndRecommendationDemandType>() {
            @Override
            public void onResponse(Call<Response4NewDemandAndRecommendationDemandType> call, Response<Response4NewDemandAndRecommendationDemandType> response) {
                if (response.isSuccessful())
                    requestResultListener.onSuccess(response);
                else
                    requestResultListener.onFail();
            }

            @Override
            public void onFailure(Call<Response4NewDemandAndRecommendationDemandType> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }

    //==============================================================================================

    /**
     * Model Request 4 Member Group 2 Model
     *
     * @param memberId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getNewDemandsAndRecommendationData4MemberGroup(String memberId, final RequestResultListener requestResultListener) {

        initApiService();

        setNewDemandsAndRecommendationsParams4MemberGroup(memberId);

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.newDemandsAndRecommendationMemberGroup(authKey, "Android", RequestBody.create(MediaType.parse("application/json"), gson.toJson(demandsAndRecommendationsParams))).enqueue(new Callback<Response4NewDemandAndRecommendationDemandType>() {
            @Override
            public void onResponse(Call<Response4NewDemandAndRecommendationDemandType> call, Response<Response4NewDemandAndRecommendationDemandType> response) {
                if (response.isSuccessful())
                    requestResultListener.onSuccess(response);
                else
                    requestResultListener.onFail();
            }

            @Override
            public void onFailure(Call<Response4NewDemandAndRecommendationDemandType> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }

    //==============================================================================================

    /**
     * Model Request 4 New Demand And Recommendation 2 Model
     *
     * @param content
     * @param subject
     * @param demandTypeId
     * @param memberGroupId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void setNewDemandAndRecommendation(String content, String subject, int demandTypeId, int memberGroupId, final RequestResultListener requestResultListener) {

        initApiService();

        saveNewDemandsAndRecommendationsParams(content, subject, demandTypeId, memberGroupId);


        apiService.saveDemandsAndRecommendation("bearer " + UETSSingletonPattern.getInstance().getToken(), "Android", RequestBody.create(MediaType.parse("application/json"), gson.toJson(saveDemandAndRecommendation))).enqueue(new Callback<Response4SaveNewDemandAndRecommendation>() {
            @Override
            public void onResponse(Call<Response4SaveNewDemandAndRecommendation> call, Response<Response4SaveNewDemandAndRecommendation> response) {
                if (response.isSuccessful())
                    requestResultListener.onSuccess(response);
                else
                    requestResultListener.onFail();
            }

            @Override
            public void onFailure(Call<Response4SaveNewDemandAndRecommendation> call, Throwable t) {
                requestResultListener.onFail();
            }
        });

    }
}
