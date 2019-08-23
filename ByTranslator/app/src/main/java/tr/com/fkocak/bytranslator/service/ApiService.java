package tr.com.fkocak.bytranslator.service;

import android.content.Context;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.com.fkocak.bytranslator.FloatingViewService;
import tr.com.fkocak.bytranslator.api.ApiUtils;
import tr.com.fkocak.bytranslator.api.interfaced.ApiInterface;
import tr.com.fkocak.bytranslator.service.interfaced.IApiService;
import tr.com.fkocak.bytranslator.tokenGenerator.TokenGenerator;

/**
 * Created by fatihkocak on 21.09.2018.
 */

public class ApiService implements IApiService {
    private ApiInterface apiInterface;
    TokenGenerator tk;
    String tokenForSpesificString;

    RotateAnimation anim;


    @Override
    public void translate(final Context context, final String enteredText, final EditText editText, final ImageView imageView, final String fromLanguage, String toLanguage, final boolean isSavedWOS, final boolean isLeaf) {

        apiInterface = ApiUtils.getApiInterface();

        tk = new TokenGenerator("406249.3075489964");
        tokenForSpesificString = tk.token(enteredText);

        String client = "t";
        String sl;
        if (fromLanguage.equals("")) {
            sl = "en";
        } else {
            sl = fromLanguage;
        }
        String tl = toLanguage;
        String hl = toLanguage;
        List<String> dtArray = new ArrayList<>();
        dtArray.add("at");
        dtArray.add("bd");
        dtArray.add("ex");
        dtArray.add("ld");
        dtArray.add("md");
        dtArray.add("qca");
        dtArray.add("rw");
        dtArray.add("rm");
        dtArray.add("ss");
        dtArray.add("t");
        String ie = "UTF-8";
        String oe = "UTF-8";
        int otf = 1;
        int ssel = 0;
        int tsel = 0;
        int kc = 7;
        String q = enteredText;
        String tk = tokenForSpesificString;


        apiInterface.translate(client, sl, tl, hl, dtArray, ie, oe, otf, ssel, tsel, kc, q, tk).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    String translatedText = response.body();
                    try {
                        JSONArray jsonArray = new JSONArray(translatedText);
                        JSONArray jsonArray1 = jsonArray.getJSONArray(0);
                        String appentedTranslateText = "";
                        for (int i = 0; i < jsonArray1.length(); i++) {
                            JSONArray jsonArray2 = jsonArray1.getJSONArray(i);
                            String itemOfTranslatedText = jsonArray2.get(0).toString();
                            appentedTranslateText += itemOfTranslatedText;
                        }

                        if (appentedTranslateText.contains("null")) {
                            editText.setText(appentedTranslateText.substring(0, appentedTranslateText.length() - 4));
                            if (!enteredText.equalsIgnoreCase(editText.getText().toString())) {
                                if (isSavedWOS && !isLeaf) {
                                    String strOfTranslatedText = appentedTranslateText.substring(0, appentedTranslateText.length() - 4);
                                    new FloatingViewService(context).saveEnteredWord(enteredText, strOfTranslatedText);
                                }
                            }

                        } else {
                            editText.setText(appentedTranslateText);
                            if (!enteredText.equalsIgnoreCase(editText.getText().toString())) {
                                if (isSavedWOS && !isLeaf)
                                    new FloatingViewService(context).saveEnteredWord(enteredText, appentedTranslateText);
                            }
                        }

                        if (!isLeaf) imageView.setAnimation(null);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (!isLeaf) imageView.setAnimation(null);
                    }
                } else {
                    Toast.makeText(context, "Body Boş Geldi..", Toast.LENGTH_SHORT).show();
                    if (!isLeaf) imageView.setAnimation(null);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "Hata Var...", Toast.LENGTH_SHORT).show();
                if (!isLeaf) imageView.setAnimation(null);
            }
        });
    }
}
