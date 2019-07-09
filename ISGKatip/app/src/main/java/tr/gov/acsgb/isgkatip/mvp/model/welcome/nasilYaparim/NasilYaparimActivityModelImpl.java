package tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim;

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
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.requestModel.FilterList4NasilYaparim;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.requestModel.NasilYaparimParam;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.responseModel.ResponseNasilYaparimData;

public class NasilYaparimActivityModelImpl implements INasilYaparimActivityModel {

    // ApiService
    private APIService apiService;
    Gson gson = new Gson();

    // Ne Yapmaliyim Request Param
    NasilYaparimParam nasilYaparimParam;

    //==============================================================================================
    @Override
    public void initApiService(int page, int size, List<FilterList4NasilYaparim> filterList4NasilYaparims) {
        apiService = ApiUtils.getAPIService();

        nasilYaparimParam = new NasilYaparimParam();
        nasilYaparimParam.setPage(page);
        nasilYaparimParam.setSize(size);
        nasilYaparimParam.setFilterList4NasilYaparims(filterList4NasilYaparims);
    }

    //==============================================================================================
    @Override
    public void getNasilYaparimData(Context context, int page, int size, List<FilterList4NasilYaparim> filterList4NasilYaparims, final RequestResultListener requestResultListener) {
        initApiService(page, size, filterList4NasilYaparims);

        String authKey = new CSharedPreferences(context).getString("AUTH_KEY");

        apiService.getNasilYaparim(authKey, RequestBody.create(MediaType.parse("application/json"), gson.toJson(nasilYaparimParam))).enqueue(new Callback<ResponseNasilYaparimData>() {
            @Override
            public void onResponse(Call<ResponseNasilYaparimData> call, Response<ResponseNasilYaparimData> response) {
                if (response.isSuccessful()) {
                    requestResultListener.onSuccess(response);
                } else {
                    requestResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<ResponseNasilYaparimData> call, Throwable t) {
                requestResultListener.onFail();
            }
        });
    }
}
