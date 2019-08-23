package tr.com.fkocak.bytranslator.api;

import tr.com.fkocak.bytranslator.api.interfaced.IApiInterface4Dictionary;

/**
 * Created by fatihkocak on 18.10.2018.
 */

public class ApiUtils4Dictionary {
    private ApiUtils4Dictionary(){}

    public static final String baseURL4Dictionary = "https://www.wordsapi.com/mashape/";

    public static IApiInterface4Dictionary getApiInterface4Dictionary(){
        return RetrofitClien4Dictionary.getClient(baseURL4Dictionary).create(IApiInterface4Dictionary.class);
    }
}
