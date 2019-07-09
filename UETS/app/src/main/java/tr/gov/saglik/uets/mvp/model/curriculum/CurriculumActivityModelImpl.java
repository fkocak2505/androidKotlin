package tr.gov.saglik.uets.mvp.model.curriculum;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.curriculum.requestParam.CurriculumFilterListParam;
import tr.gov.saglik.uets.mvp.model.curriculum.requestParam.CurriculumListParam;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.Response4CurriculumList;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.filterList.Response4CurriculumFilterList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel.FilterArrParam;

public class CurriculumActivityModelImpl implements ICurriculumActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    /// Request Param
    CurriculumFilterListParam curriculumFilterListParam;
    CurriculumListParam curriculumListParam;


    //==============================================================================================

    /**
     * Init Retrofit Api Service
     */
    //==============================================================================================
    @Override
    public void initApiService() {
        apiService = ApiUtils.getAPIService();
    }

    //==============================================================================================

    /**
     * Set Params Curriculum..
     * @param filterList
     */
    //==============================================================================================
    @Override
    public void setCurriculumListRequestParam(List<FilterArrParam> filterList) {
        curriculumFilterListParam = new CurriculumFilterListParam();
        curriculumFilterListParam.setName("");
        curriculumFilterListParam.setDelete(false);
        curriculumFilterListParam.setDefaultValue("");
        curriculumFilterListParam.setFilterLists(filterList);
    }

    //==============================================================================================

    /**
     * Set Curriculum Filter List Param..
     */
    //==============================================================================================
    @Override
    public void setCurriculumFilterListRequestParam() {
        curriculumFilterListParam = new CurriculumFilterListParam();
        curriculumFilterListParam.setName("");
        curriculumFilterListParam.setDelete(false);
        curriculumFilterListParam.setDefaultValue("");
    }

    //==============================================================================================

    /**
     * Model Request 4 Curriculum List..
     * @param filterList
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getCurriculumList(List<FilterArrParam> filterList, final RequestResultListener requestResultListener) {
        initApiService();

        setCurriculumListRequestParam(filterList);

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.curriculumList(authKey, RequestBody.create(MediaType.parse("application/json"), gson.toJson(curriculumFilterListParam))).enqueue(new Callback<Response4CurriculumList>() {
            @Override
            public void onResponse(Call<Response4CurriculumList> call, Response<Response4CurriculumList> response) {
                if (response.isSuccessful())
                    requestResultListener.onSuccess(response);
                else
                    requestResultListener.onFail();
            }

            @Override
            public void onFailure(Call<Response4CurriculumList> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }

    //==============================================================================================

    /**
     * Model Request 4 Curriculum Filter List..
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getCurriculumFilterList(final RequestResultListener requestResultListener) {
        initApiService();

        setCurriculumFilterListRequestParam();

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.curriculumFilterList(authKey, RequestBody.create(MediaType.parse("application/json"), gson.toJson(curriculumFilterListParam))).enqueue(new Callback<Response4CurriculumFilterList>() {
            @Override
            public void onResponse(Call<Response4CurriculumFilterList> call, Response<Response4CurriculumFilterList> response) {
                if (response.isSuccessful())
                    requestResultListener.onSuccess(response);
                else
                    requestResultListener.onFail();
            }

            @Override
            public void onFailure(Call<Response4CurriculumFilterList> call, Throwable t) {
                requestResultListener.onFail();
            }
        });

    }
}
