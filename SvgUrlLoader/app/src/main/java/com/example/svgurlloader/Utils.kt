package com.example.svgurlloader

import com.pixplicity.sharp.Sharp
import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import okhttp3.*
import java.io.IOException


object Utils {
    private var httpClient: OkHttpClient? = null

    fun fetchSvg(context: Context, url: String, target: ImageView) {
        if (httpClient == null) {
            // Use cache for performance and basic offline capability
            httpClient = OkHttpClient.Builder()
                .cache(Cache(context.cacheDir, 5 * 1024 * 1014))
                .build()
        }

        val request = Request.Builder().url(url).build()
        httpClient!!.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                target.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_launcher_background))
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val stream = response.body()?.byteStream()
                Sharp.loadInputStream(stream).into(target)
                stream?.close()
            }
        })
    }
}