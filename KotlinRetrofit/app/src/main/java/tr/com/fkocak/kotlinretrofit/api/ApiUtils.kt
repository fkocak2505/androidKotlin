package tr.com.fkocak.kotlinretrofit.api

class ApiUtils {
    companion object {
        private val BASE_URL: String = "https://www.simplifiedcoding.net/demos/"
        fun getAPIService(): ApiService {
            return RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)
        }
    }
}