package com.onedio.androidside.api

import com.onedio.androidside.BuildConfig
import com.onedio.androidside.api.interfaced.IApiService

class ApiUtils {
    companion object {
        private val BASE_URL_FEED: String = "https://api.onedio.com"

        private val BASE_URL: String =
            "https://user.oned.io/"

        /*private val BASE_URL: String =
            "http://onedioUserService-prod-env-2.eu-west-1.elasticbeanstalk.com/"*/


        private val BASE_URL_V2: String =
            "http://onedioapiv2-env.frshxf3zwr.eu-west-1.elasticbeanstalk.com/"
        private val BASE_URL_V2_TEST_CLUSTER =
            "http://onedioApiV2-Test-Cluster-env.frshxf3zwr.eu-west-1.elasticbeanstalk.com/"
        private val BASE_URL_ONEDIO = "https://onedio.com/"

        private val BASE_URL_STATIC_ONEDIO = "https://static.onedio.com/"

        private val BASE_URL_FEED_BACK = "https://at2otfsgs7.execute-api.eu-west-1.amazonaws.com/"

        fun getAPIServiceFeedBack(): IApiService {
            return RetrofitClient.getClient(BASE_URL_FEED_BACK)
                .create(IApiService::class.java)
        }

        fun getAPIServiceSlider(): IApiService {
            return RetrofitClient.getClient(BuildConfig.BASE_URL)
                .create(IApiService::class.java)
        }


        fun getAPIService(): IApiService {
            return RetrofitClient.getClient(BASE_URL)
                .create(IApiService::class.java)
        }

        fun getAPIServiceV2(): IApiService {
            return RetrofitClient.getClient(BASE_URL_V2)
                .create(IApiService::class.java)
        }

        fun getAPIServiceV2TestCluster(): IApiService {
            return RetrofitClient.getClient(BASE_URL_V2_TEST_CLUSTER)
                .create(IApiService::class.java)
        }

        fun getAPIServiceOnedio(): IApiService {
            return RetrofitClient.getClient(BASE_URL_ONEDIO)
                .create(IApiService::class.java)
        }

        fun getAPIServiceStaticOnedio(): IApiService {
            return RetrofitClient.getClient(BASE_URL_STATIC_ONEDIO)
                .create(IApiService::class.java)
        }
    }
}


