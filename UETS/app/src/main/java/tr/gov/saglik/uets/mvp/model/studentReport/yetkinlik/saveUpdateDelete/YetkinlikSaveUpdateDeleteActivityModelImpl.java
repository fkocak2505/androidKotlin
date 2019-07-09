package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.requestModel.DeleteParams;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.requestModel.EducatorParams;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.requestModel.InstitutionParams;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.requestModel.SaveParams;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4EducatorList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4InstitutionData;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4TeamMemberList;

public class YetkinlikSaveUpdateDeleteActivityModelImpl implements IYetkinlikSaveUpdateDeleteActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    /// Request Param
    InstitutionParams institutionParams;
    EducatorParams educatorParams;
    EducatorParams teamMemberParams;
    DeleteParams deleteParams;

    //==============================================================================================

    /**
     * Init Retrofirt API Service
     */
    //==============================================================================================
    @Override
    public void initApiService() {
        apiService = ApiUtils.getAPIService();

    }

    //==============================================================================================

    /**
     * Set Params 4 Institution List
     */
    //==============================================================================================
    @Override
    public void setParam4InstitutionList() {
        institutionParams = new InstitutionParams();
        institutionParams.setName("");
        institutionParams.setIsDelete(false);
        institutionParams.setDefaultValue("");
    }

    //==============================================================================================

    /**
     * Set Param 4 Educator List
     * @param institutionId
     */
    //==============================================================================================
    @Override
    public void setParam4EducatorListByInstitutionMember(int institutionId) {
        educatorParams = new EducatorParams();
        educatorParams.setName("");
        educatorParams.setIsDelete(false);
        educatorParams.setDefaultValue("");
        educatorParams.setInstitutionId(institutionId);
    }

    //==============================================================================================

    /**
     * Set Params 4 Team Member
     * @param institutionId
     */
    //==============================================================================================
    @Override
    public void setParam4TeamMemberListByInstitutionId(int institutionId) {
        teamMemberParams = new EducatorParams();
        teamMemberParams.setName("");
        teamMemberParams.setIsDelete(false);
        teamMemberParams.setDefaultValue("");
        teamMemberParams.setInstitutionId(institutionId);
    }

    //==============================================================================================

    /**
     * Set Params 4 Delete Yetkinlik
     * @param yetkinlikId
     */
    //==============================================================================================
    @Override
    public void setParameter4DeleteYetkinlik(int yetkinlikId) {
        deleteParams = new DeleteParams();
        deleteParams.setId(yetkinlikId);
    }

    //==============================================================================================

    /**
     * Model Request 4 Institution List
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getInstitutionList(final RequestResultListener requestResultListener) {
        initApiService();

        setParam4InstitutionList();

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.getInstitutionList(authKey, "true", RequestBody.create(MediaType.parse("application/json"), gson.toJson(institutionParams))).enqueue(new Callback<Response4InstitutionData>() {
            @Override
            public void onResponse(Call<Response4InstitutionData> call, Response<Response4InstitutionData> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<Response4InstitutionData> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }

    //==============================================================================================

    /**
     * Model Request 4 Educator List
     * @param institutionId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getEducatorListByInstitutionId(int institutionId, final RequestResultListener requestResultListener) {
        initApiService();

        setParam4EducatorListByInstitutionMember(institutionId);

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.getEducatorList(authKey, "true", RequestBody.create(MediaType.parse("application/json"), gson.toJson(educatorParams))).enqueue(new Callback<Response4EducatorList>() {
            @Override
            public void onResponse(Call<Response4EducatorList> call, Response<Response4EducatorList> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<Response4EducatorList> call, Throwable t) {
                requestResultListener.onFail();
            }
        });

    }

    //==============================================================================================

    /**
     * Model Request 4 TeamMember List..
     * @param institutionId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getTeamMemberListByInstitutionId(int institutionId, final RequestResultListener requestResultListener) {
        initApiService();

        setParam4TeamMemberListByInstitutionId(institutionId);

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.getTeamMember(authKey, "true", RequestBody.create(MediaType.parse("application/json"), gson.toJson(teamMemberParams))).enqueue(new Callback<Response4TeamMemberList>() {
            @Override
            public void onResponse(Call<Response4TeamMemberList> call, Response<Response4TeamMemberList> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<Response4TeamMemberList> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }

    //==============================================================================================

    /**
     * Model Request 4 Save Yetkinlik
     * @param saveParams
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void saveYetkinlik(SaveParams saveParams, final RequestResultListener requestResultListener) {
        initApiService();

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.saveYetkinlik(authKey,"true", RequestBody.create(MediaType.parse("application/json"), gson.toJson(saveParams))).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }

    //==============================================================================================

    /**
     * Model Request 4 Delete Yetkinlik
     * @param yetkinlikId
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void deleteYetkinlik(int yetkinlikId, final RequestResultListener requestResultListener) {
        initApiService();

        setParameter4DeleteYetkinlik(yetkinlikId);

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.deleteYetkinlik(authKey,"true",RequestBody.create(MediaType.parse("application/json"), gson.toJson(deleteParams))).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }
}
