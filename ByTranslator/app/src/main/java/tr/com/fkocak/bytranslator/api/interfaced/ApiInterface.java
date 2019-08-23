package tr.com.fkocak.bytranslator.api.interfaced;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by fatihkocak on 20.09.2018.
 */

public interface ApiInterface {

    /*@GET("setting_preferences")
    Call<Response4Translate> setting_preferences(@Query("key") String yandexApiKey, @Query("text") String text, @Query("lang") String lang);*/

    @GET("single")
    Call<String> translate(@Query("client") String client,
                                       @Query("sl") String sl,
                                       @Query("tl") String tl,
                                       @Query("hl") String hl,
                                       @Query("dt") List<String> dt,
                                       @Query("ie") String ie,
                                       @Query("oe") String oe,
                                       @Query("otf") int otf,
                                       @Query("ssel") int ssel,
                                       @Query("tsel") int tsel,
                                       @Query("kc") int kc,
                                       @Query("q") String text,
                                       @Query("tk") String token);



}
