package tr.com.androidside.onedio.api.interfaced

import com.androidlab.jetpackarchitecture.mvvm.model.ClientCredentials
import com.androidlab.jetpackarchitecture.mvvm.model.token.TokenModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface IApiService {

    @POST("token")
    fun getAppToken(@Body clientCredentials: ClientCredentials): Single<Response<TokenModel>>
//
//    @POST("/v0.1.0/oauth/authorize")
//    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
//    fun doLogin(@Header("oio-token") oioToken: String, @Body type: RequestBody): Call<Response4Login>
//
//    @GET("/v0.1.0/isemailexist/{email}")
//    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
//    fun isMailExist(@Header("oio-token") oioToken: String, @Path("email") email: String): Call<Response4IsMailExist>
//
//    @GET("/v0.1.0/isusernameexist/{userName}")
//    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
//    fun isUserNameExist(@Header("oio-token") oioToken: String, @Path("userName") email: String): Call<Response4IsMailExist>

}