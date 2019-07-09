package tr.com.fkocak.listviewpaginationandroid.api.interfaced;

/**
 * Created by fatihkocak on 25.04.2019.
 */

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import tr.com.fkocak.listviewpaginationandroid.model.ResponseData;

public interface APIService {

    @POST("/isg-katip/auth")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<ResponseData> savePostWithPojoModel(@Body RequestBody type);

}