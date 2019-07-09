package tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim;

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
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.requestModel.FilterList4NeYapmaliyim;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.requestModel.NeYapmaliyimParam;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.responseModel.ResponseNeYapmaliyimData;

public class NeYapmaliyimActivityModelImpl implements INeYapmaliyimActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    // Ne Yapmaliyim Request Param
    NeYapmaliyimParam neYapmaliyimParam;


    //==============================================================================================
    @Override
    public void initApiService(int page, int size, List<FilterList4NeYapmaliyim> filterLists) {
        apiService = ApiUtils.getAPIService();

        neYapmaliyimParam = new NeYapmaliyimParam();
        neYapmaliyimParam.setPage(page);
        neYapmaliyimParam.setSize(size);
        neYapmaliyimParam.setFilterList(filterLists);
    }

    //==============================================================================================
    @Override
    public void getNeYapmaliyimData(Context context, int page, int size, List<FilterList4NeYapmaliyim> filterLists, final RequestResultListener requestResultListener) {

        initApiService(page, size, filterLists);

        String authKey = new CSharedPreferences(context).getString("AUTH_KEY");

        apiService.getNeYapmaliyim(authKey, RequestBody.create(MediaType.parse("application/json"), gson.toJson(neYapmaliyimParam))).enqueue(new Callback<ResponseNeYapmaliyimData>() {
            @Override
            public void onResponse(Call<ResponseNeYapmaliyimData> call, Response<ResponseNeYapmaliyimData> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<ResponseNeYapmaliyimData> call, Throwable t) {
                requestResultListener.onFail();
            }
        });

    }
}
