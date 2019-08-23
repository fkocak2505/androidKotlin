package tr.com.fkocak.bytranslator.api;

import tr.com.fkocak.bytranslator.api.interfaced.APIService4AWSEB;

public class ApiUtils4AWSEB {
    private ApiUtils4AWSEB() {}

    public static final String BASE_URL = "http://witranslate-env.iiqdki6ybv.eu-west-1.elasticbeanstalk.com/";
    //public static final String BASE_URL = "http://192.168.1.45:3010/";

    public static APIService4AWSEB getAPIService() {

        return RetrofitClient4AWSEB.getClient(BASE_URL).create(APIService4AWSEB.class);
    }
}
