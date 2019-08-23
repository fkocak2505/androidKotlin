package tr.com.fkocak.bytranslator.api.interfaced;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by fatihkocak on 18.10.2018.
 */

public interface IApiInterface4Dictionary {

    @GET("words/{word}")
    Call<String> getDictionary(@Path("word") String word, @Query("when") String when, @Query("encrypted") String encrypted);

    @GET("words")
    Call<String> getHangmanWord(@Query("random") boolean random, @Query("when") String when, @Query("encrypted") String encrypted);
}
