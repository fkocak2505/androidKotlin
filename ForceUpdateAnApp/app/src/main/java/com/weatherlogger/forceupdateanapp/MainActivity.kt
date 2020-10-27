package com.weatherlogger.forceupdateanapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import java.io.IOException



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkNewAppVersion(applicationContext, 0)

    }

    @SuppressLint("StaticFieldLeak")
    private fun checkNewAppVersion(context: Context, view: Int) {
        val link = "https://play.google.com/store/apps/details?id=com.onedio.androidside&hl=en"
        object : AsyncTask<Void, Void, String>() {
            override fun doInBackground(vararg strings: Void): String? {
                try {
                    Log.e("startVersion", "start")
                    val newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=com.onedio.androidside&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                        .first()
                        .ownText();
                    Log.e("versionPlaystore", newVersion)
                    return newVersion
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                return null
            }

            override fun onPostExecute(version: String?) {
                super.onPostExecute(version)
                try {
                    val currentVersion = packageManager
                        .getPackageInfo("com.onedio.androidside", 0).versionName
                    if (version != null && currentVersion != null) {
                        Log.e("startVersion", "$version $currentVersion")
                        if (!version.trim { it <= ' ' }.equals(
                                currentVersion.trim { it <= ' ' },
                                ignoreCase = true
                            )
                        ) {
                            val dialog = AlertDialog.Builder(this@MainActivity)
                                .setView(view)
                                .setCancelable(false)
                                .setPositiveButton("Update"
                                ) { dialogInterface, _ ->
                                    dialogInterface.dismiss()
                                    openPlaystore(context)
                                }
                                .setNegativeButton(
                                    "Later"
                                ) { dialogInterface, _ ->
                                    dialogInterface.dismiss()
                                    finish()
                                }
                                .create()
                            dialog.show()
                        }
                    }

                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                }

            }
        }.execute()
    }

    private fun openPlaystore(context: Context) {
        val appPackageName = "com.onedio.androidside" // getPackageName() from Context or Activity object
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
            finish()
        } catch (anfe: android.content.ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
            finish()
        }
    }
}
