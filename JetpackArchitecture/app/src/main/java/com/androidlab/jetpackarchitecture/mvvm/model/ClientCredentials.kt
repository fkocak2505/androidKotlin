package com.androidlab.jetpackarchitecture.mvvm.model

import com.google.gson.annotations.SerializedName

data class ClientCredentials(
    @SerializedName("client_id") var clientId: String,
    @SerializedName("client_secret") var clientSecret: String,
    @SerializedName("grant_type") var grantType:String
)