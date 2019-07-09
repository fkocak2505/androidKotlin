package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik;

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
import tr.gov.saglik.uets.mvp.model.EmptyRequestParam;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel.FilterArrParam;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel.YetkinlikFilterRequest;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel.ResponseYetkinlikListFilterListData;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.responseModel.ResponseYetkinlikList;

public class YetkinlikListesiActivityModelImpl implements IYetkinlikListesiActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    /// Request Param
    EmptyRequestParam emptyRequestParam;
    YetkinlikFilterRequest yetkinlikFilterRequest;

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
     * Set yetkinlik Fİlters Params..
     */
    //==============================================================================================
    @Override
    public void setYetkinlikListFiltersParam() {
        emptyRequestParam = new EmptyRequestParam();
        emptyRequestParam.setName("");
        emptyRequestParam.setIsDelete(false);
        emptyRequestParam.setDefaultValue("");
    }

    //==============================================================================================

    /**
     * Set Yetkinlik List Params..
     * @param memberId
     * @param filterList
     */
    //==============================================================================================
    @Override
    public void setYetkinlikListParam(int memberId, List<FilterArrParam> filterList) {
        yetkinlikFilterRequest = new YetkinlikFilterRequest();
        yetkinlikFilterRequest.setName("");
        yetkinlikFilterRequest.setIsDelete(false);
        yetkinlikFilterRequest.setDefaultValue("");
        yetkinlikFilterRequest.setMemberId(memberId);
        yetkinlikFilterRequest.setFilterLists(filterList);
    }

    //==============================================================================================

    /**
     * Model Request 4 Yetkinlik Filter Lists..
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getYetkinlikListFilterList(final RequestResultListener requestResultListener) {

        initApiService();
        setYetkinlikListFiltersParam();

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.getYetkinlikListFilterList(authKey, RequestBody.create(MediaType.parse("application/json"), gson.toJson(emptyRequestParam))).enqueue(new Callback<ResponseYetkinlikListFilterListData>() {
            @Override
            public void onResponse(Call<ResponseYetkinlikListFilterListData> call, Response<ResponseYetkinlikListFilterListData> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<ResponseYetkinlikListFilterListData> call, Throwable t) {
                requestResultListener.onFail();
            }
        });

    }

    //==============================================================================================

    /**
     * Model Request 4 Yetkinlik List By Member
     * @param memberId
     * @param filterLists
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getYetkinlikListByMember(int memberId, List<FilterArrParam> filterLists, final RequestResultListener requestResultListener) {
        initApiService();
        setYetkinlikListParam(memberId, filterLists);

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.getYetkinlikList(authKey, "true", "ANDROID", RequestBody.create(MediaType.parse("application/json"), gson.toJson(yetkinlikFilterRequest))).enqueue(new Callback<ResponseYetkinlikList>() {
            @Override
            public void onResponse(Call<ResponseYetkinlikList> call, Response<ResponseYetkinlikList> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<ResponseYetkinlikList> call, Throwable t) {
                requestResultListener.onFail();
            }
        });

    }
}
