package com.mobilhanem.retrofitloginpost;

import com.mobilhanem.retrofitloginpost.model.UserInfo;
import com.mobilhanem.retrofitloginpost.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by alper on 13/02/17.
 */

public interface RetrofitLoginService {

    @POST("api/index2.php")
    //@FormUrlEncoded
    Call<UserResponse> login(@Body UserInfo userInfo);

}
