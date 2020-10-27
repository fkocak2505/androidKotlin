package com.wetranslate.wetranslateapp.common

import android.content.Context
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.handlers.AsyncHandler
import com.amazonaws.services.translate.AmazonTranslateAsyncClient
import com.amazonaws.services.translate.model.TranslateTextRequest
import com.amazonaws.services.translate.model.TranslateTextResult

class AWSTranslate {

    companion object {
        fun translate(context: Context, str2BeTranslated: String, edtTranslated: TextView){
            val awsCredentials: AWSCredentials = object : AWSCredentials {
                override fun getAWSAccessKeyId(): String {
                    return ""
                }

                override fun getAWSSecretKey(): String {
                    return ""
                }
            }

            val translateAsyncClient = AmazonTranslateAsyncClient(awsCredentials)
            val translateTextRequest = TranslateTextRequest()
                .withText(str2BeTranslated)
                .withSourceLanguageCode("auto")
                .withTargetLanguageCode("en")
            translateAsyncClient.translateTextAsync(translateTextRequest, object :
                AsyncHandler<TranslateTextRequest?, TranslateTextResult?> {
                override fun onError(e: Exception) {
                    Toast.makeText(context, "Hata", Toast.LENGTH_SHORT).show()
                }

                override fun onSuccess(
                    request: TranslateTextRequest?,
                    result: TranslateTextResult?
                ) {

                    edtTranslated.text = result?.translatedText

                }
            })
        }
    }

}