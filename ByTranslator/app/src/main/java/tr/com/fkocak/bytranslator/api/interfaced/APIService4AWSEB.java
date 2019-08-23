package tr.com.fkocak.bytranslator.api.interfaced;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService4AWSEB {
    @POST("/api/rekognitionImage")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<String> rekognition(@Body RequestBody type);

    @POST("/api/register")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<String> signUp(@Body RequestBody type);

    @POST("/api/login")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<String> signIn(@Body RequestBody type);
}
