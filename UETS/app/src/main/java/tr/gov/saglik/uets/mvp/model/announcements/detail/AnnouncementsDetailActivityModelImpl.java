package tr.gov.saglik.uets.mvp.model.announcements.detail;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.announcements.responseModel.Response4AnnouncementsDetail;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.requestModel.DeleteParams;

public class AnnouncementsDetailActivityModelImpl implements IAnnouncementsDetailActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    // Request Params
    DeleteParams deleteParams;


    @Override
    public void initApiService() {
        apiService = ApiUtils.getAPIService();
    }

    @Override
    public void getAnnouncementsDetailData(int id, final RequestResultListener requestResultListener) {
        initApiService();

        deleteParams = new DeleteParams();
        deleteParams.setId(id);

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.announcementDetail(authKey,"true", RequestBody.create(MediaType.parse("application/json"), gson.toJson(deleteParams))).enqueue(new Callback<Response4AnnouncementsDetail>() {
            @Override
            public void onResponse(Call<Response4AnnouncementsDetail> call, Response<Response4AnnouncementsDetail> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<Response4AnnouncementsDetail> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }
}
