package tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement;

import android.content.Context;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.gov.acsgb.isgkatip.RequestResultListener;
import tr.gov.acsgb.isgkatip.api.ApiUtils;
import tr.gov.acsgb.isgkatip.api.interfaced.APIService;
import tr.gov.acsgb.isgkatip.common.CSharedPreferences;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.requestModel.AnnouncementsParam;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.requestModel.FilterList4Announcement;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.responseModel.ResponseAnnouncementData;

public class AnnouncementActivityModelImpl implements IAnnouncementActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    // News Request Param
    AnnouncementsParam announcementsParam;

    //==============================================================================================
    @Override
    public void initApiService(int page, int size, List<FilterList4Announcement> filterList4Announcements) {
        apiService = ApiUtils.getAPIService();

        announcementsParam = new AnnouncementsParam();
        announcementsParam.setPage(page);
        announcementsParam.setSize(size);
        announcementsParam.setFilterList4Announcements(filterList4Announcements);
    }

    //==============================================================================================
    @Override
    public void getAnnouncementsData(Context context, int page, int size, List<FilterList4Announcement> filterList4Announcements, final RequestResultListener requestResultListener) {
        initApiService(page, size, filterList4Announcements);

        String authKey = new CSharedPreferences(context).getString("AUTH_KEY");

        apiService.getAnnouncement(authKey, RequestBody.create(MediaType.parse("application/json"), gson.toJson(announcementsParam))).enqueue(new Callback<ResponseAnnouncementData>() {
            @Override
            public void onResponse(Call<ResponseAnnouncementData> call, Response<ResponseAnnouncementData> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<ResponseAnnouncementData> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }
}
