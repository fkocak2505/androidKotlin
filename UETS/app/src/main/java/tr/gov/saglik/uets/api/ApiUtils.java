package tr.gov.saglik.uets.api;


import tr.gov.saglik.uets.api.interfaced.APIService;

/**
 * Created by fatihkocak on 25.04.2019.
 */

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://172.18.230.37:83/";
    public static final String BASE_URL_82 = "http://172.18.230.37:82/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }


    public static APIService getAPIService82() {

        return RetrofitClient.getClient(BASE_URL_82).create(APIService.class);
    }
}
