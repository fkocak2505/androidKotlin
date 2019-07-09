package tr.gov.acsgb.isgkatip.mvp.model.welcome.news;

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
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.requestModel.FilterList;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.requestModel.NewsParam;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.responseModel.ResponseNewsData;
import tr.gov.acsgb.isgkatip.common.CSharedPreferences;

public class NewsActivityModelImpl implements INewsActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    // News Request Param
    NewsParam newsParam;

    //==============================================================================================
    @Override
    public void initApiService(int page, int size, List<FilterList> filterLists) {
        apiService = ApiUtils.getAPIService();

        newsParam = new NewsParam();
        newsParam.setPage(page);
        newsParam.setSize(size);
        newsParam.setFilterList(filterLists);

    }

    //==============================================================================================
    @Override
    public void getNewsData(Context context, int page, int size, List<FilterList> filterLists, final RequestResultListener requestResultListener) {

        initApiService(page, size, filterLists);

        String authKey = new CSharedPreferences(context).getString("AUTH_KEY");

        apiService.getNews(authKey, RequestBody.create(MediaType.parse("application/json"),gson.toJson(newsParam))).enqueue(new Callback<ResponseNewsData>() {
            @Override
            public void onResponse(Call<ResponseNewsData> call, Response<ResponseNewsData> response) {
                if(response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<ResponseNewsData> call, Throwable t) {
                requestResultListener.onFail();
            }
        });

    }
}
