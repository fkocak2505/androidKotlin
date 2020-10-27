package com.eventbus.awstranslate

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.handlers.AsyncHandler
import com.amazonaws.services.translate.AmazonTranslateAsyncClient
import com.amazonaws.services.translate.model.TranslateTextRequest
import com.amazonaws.services.translate.model.TranslateTextResult
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val LOG_TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val awsCredentials: AWSCredentials = object : AWSCredentials {
            override fun getAWSAccessKeyId(): String {
                return "AKIAJFN564UI7QI3RUAA"
            }

            override fun getAWSSecretKey(): String {
                return "vXgS10SGnm++eBRYPKf3m7MJvyXkH+QmDeaEFq7D"
            }
        }

        fffff.setOnClickListener {
            val translateAsyncClient = AmazonTranslateAsyncClient(awsCredentials)
            val translateTextRequest = TranslateTextRequest()
                    .withText(aaaa.text.toString())
                    .withSourceLanguageCode("auto")
                    .withTargetLanguageCode("en")
            translateAsyncClient.translateTextAsync(translateTextRequest, object : AsyncHandler<TranslateTextRequest?, TranslateTextResult?> {
                override fun onError(e: Exception) {
                    Log.e(LOG_TAG, "Error occurred in translating the text: " + e.localizedMessage)
                }

                override fun onSuccess(request: TranslateTextRequest?, result: TranslateTextResult?) {
                    Log.d(LOG_TAG, "Original Text: " + request?.text)
                    Log.d(LOG_TAG, "Translated Text: " + result?.translatedText)

                    aaaa.setText(result?.translatedText)

                }
            })
        }
    }
}