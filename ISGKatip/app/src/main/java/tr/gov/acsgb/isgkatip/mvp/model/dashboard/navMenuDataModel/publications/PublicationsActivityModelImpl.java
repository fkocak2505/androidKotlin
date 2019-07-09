package tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications;

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
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.requestModel.FilterList4Publications;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.requestModel.PublicationsParam;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.responseModel.ResponsePublicationsData;

public class PublicationsActivityModelImpl implements IPublicationsActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    // Publications Request Param
    PublicationsParam publicationsParam;

    //==============================================================================================
    @Override
    public void initApiService(int page, int size, List<FilterList4Publications> filterList4Publications) {
        apiService = ApiUtils.getAPIService();

        publicationsParam = new PublicationsParam();
        publicationsParam.setPage(page);
        publicationsParam.setSize(size);
        publicationsParam.setFilterList4Publications(filterList4Publications);
    }

    //==============================================================================================
    @Override
    public void getPublicationsData(Context context, int page, int size, List<FilterList4Publications> filterList4Publications, final RequestResultListener requestResultListener) {

        initApiService(page, size, filterList4Publications);

        String authKey = new CSharedPreferences(context).getString("AUTH_KEY");

        apiService.getPublications(authKey, RequestBody.create(MediaType.parse("application/json"), gson.toJson(publicationsParam))).enqueue(new Callback<ResponsePublicationsData>() {
            @Override
            public void onResponse(Call<ResponsePublicationsData> call, Response<ResponsePublicationsData> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<ResponsePublicationsData> call, Throwable t) {
                requestResultListener.onFail();
            }
        });

    }
}
