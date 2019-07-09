package tr.gov.saglik.uets.mvp.model.announcements;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.api.ApiUtils;
import tr.gov.saglik.uets.api.interfaced.APIService;
import tr.gov.saglik.uets.mvp.model.announcements.requestModel.AnnouncementsParams;
import tr.gov.saglik.uets.mvp.model.announcements.responseModel.Response4Announcements;

public class AnnouncementsActivityModelImpl implements IAnnouncementsActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    // Request Params
    AnnouncementsParams announcementsParams;


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
     * Set Announcement Params
     */
    //==============================================================================================
    @Override
    public void setAnnouncementsListParams() {
        announcementsParams = new AnnouncementsParams();
        announcementsParams.setIsDelete(false);
        announcementsParams.setLimit(100);
        announcementsParams.setName("");
        announcementsParams.setSkipCount(1);
        announcementsParams.setCode("GNLATP");
    }

    //==============================================================================================

    /**
     * Model Request 2 Announcement Service
     * @param requestResultListener
     */
    //==============================================================================================
    @Override
    public void getAnnouncementList(final RequestResultListener requestResultListener) {
        initApiService();

        setAnnouncementsListParams();

        String authKey = "bearer EzExbJRmCO5-FY7foKMJyv5vvcTuJuszrXTG9tMLUoNeScPgbvVYpZHCRhsgFGocK8lf3avKmbayNjvx5Y-9_4OZr7SfHv8XCuMkD9PMpDUswAsHi6OJyfps4akmcr473L5ALCGqsJXDKLXh_2_6fXbTBJVGOjHBQW7ooK0dIOuZD7Fov4Wk1kGdTfeanb27AJTzvoap9hmUMvnwpIE5t26zccndEhUufRMg1_qcdE_OyIiJZIyXIyH-TjhNc0g-";

        apiService.announcementsList(authKey,"true", RequestBody.create(MediaType.parse("application/json"), gson.toJson(announcementsParams))).enqueue(new Callback<Response4Announcements>() {
            @Override
            public void onResponse(Call<Response4Announcements> call, Response<Response4Announcements> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<Response4Announcements> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }


}
