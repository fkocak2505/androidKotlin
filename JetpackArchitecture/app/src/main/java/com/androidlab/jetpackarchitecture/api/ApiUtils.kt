package tr.com.androidside.onedio.api

import tr.com.androidside.onedio.api.interfaced.IApiService

class ApiUtils {
    companion object {
        private val BASE_URL: String = "https://dio-api-dev.oned.io/v1/1/4/1/"

        private val AUTH_URL = "https://user-test.oned.io/v0.1.0/"

        // todo: bak buna
        private val DYNAMIC_IMAGE_URL = "https://diodynamicimage.oned.io/"

        fun getAPIService(): IApiService {
            return RetrofitClient.getClient(BASE_URL).create(IApiService::class.java)
        }

        fun getAuthService(): IApiService {
            return RetrofitClient.getClient(AUTH_URL).create(IApiService::class.java)
        }
    }
}


