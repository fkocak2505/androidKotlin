package tr.com.fkocak.bytranslator.service;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.com.fkocak.bytranslator.HangmanActivity;
import tr.com.fkocak.bytranslator.SavedWord;
import tr.com.fkocak.bytranslator.api.ApiUtils;
import tr.com.fkocak.bytranslator.api.ApiUtils4Dictionary;
import tr.com.fkocak.bytranslator.api.interfaced.ApiInterface;
import tr.com.fkocak.bytranslator.api.interfaced.IApiInterface4Dictionary;
import tr.com.fkocak.bytranslator.model.randomword.Response4RandomWord;
import tr.com.fkocak.bytranslator.service.interfaced.IApiService4Dictionary;

/**
 * Created by fatihkocak on 17.10.2018.
 */

public class ApiService4Dictionary implements IApiService4Dictionary {
    private IApiInterface4Dictionary iApiInterface4Dictionary;


    @Override
    public void getDictionary(final Context context, final String word, final String when, final String encrypted) {
        iApiInterface4Dictionary = ApiUtils4Dictionary.getApiInterface4Dictionary();

        iApiInterface4Dictionary.getDictionary(word, when, encrypted).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response != null && response.errorBody() == null){
                    if(response.body() != null || !response.body().isEmpty()){
                        new SavedWord().parseDictionaryData(context,response.body());
                    } else {
                        Toast.makeText(context, "Response/Body Null ya da Boş Geldi...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, word + " kelimesinin sözlüğü bulunmamaktadır.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "Dictionary Service Fail etti...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
