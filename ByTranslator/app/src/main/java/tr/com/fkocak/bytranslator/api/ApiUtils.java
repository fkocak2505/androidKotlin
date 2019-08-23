package tr.com.fkocak.bytranslator.api;

import tr.com.fkocak.bytranslator.api.interfaced.ApiInterface;

/**
 * Created by fatihkocak on 20.09.2018.
 */

public class ApiUtils {
    private ApiUtils(){}

    public static final String baseURL = "https://translate.google.com/translate_a/";

    public static ApiInterface getApiInterface(){
        return RetrofitClient.getClient(baseURL).create(ApiInterface.class);
    }
}
