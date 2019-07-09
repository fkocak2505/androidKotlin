package tr.gov.acsgb.isgkatip.api.interfaced;

/**
 * Created by fatihkocak on 25.04.2019.
 */

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.responseModel.ResponsePublicationsData;
import tr.gov.acsgb.isgkatip.mvp.model.login.responseModel.ResponseLoginData;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.responseModel.ResponseAnnouncementData;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.responseModel.ResponseNasilYaparimData;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.responseModel.ResponseNeYapmaliyimData;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.responseModel.ResponseNewsData;

public interface APIService {

    @POST("/isg-katip/auth")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<ResponseLoginData> savePostWithPojoModel(@Body RequestBody type);

    @POST("/isg-katip/v1/cntnt/search")
    @Headers({ "ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<ResponseNewsData> getNews(@Header("Remember") String auth, @Body RequestBody type);

    @POST("/isg-katip/v1/cntnt/search")
    @Headers({ "ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<ResponseNeYapmaliyimData> getNeYapmaliyim(@Header("Remember") String auth, @Body RequestBody type);

    @POST("/isg-katip/v1/cntnt/search")
    @Headers({ "ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<ResponseAnnouncementData> getAnnouncement(@Header("Remember") String auth, @Body RequestBody type);

    @POST("/isg-katip/v1/cntnt/search")
    @Headers({ "ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<ResponsePublicationsData> getPublications(@Header("Remember") String auth, @Body RequestBody type);

    @POST("/isg-katip/v1/cntnt/search")
    @Headers({ "ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<ResponseNasilYaparimData> getNasilYaparim(@Header("Remember") String auth, @Body RequestBody type);

}