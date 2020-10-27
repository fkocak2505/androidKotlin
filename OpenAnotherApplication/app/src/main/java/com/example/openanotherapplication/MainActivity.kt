package com.example.openanotherapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        openFacebook()
        openTwitter()
        openInstagram()
        openYoutube()
        openOynakazan()

    }

    private fun openFacebook(){
        openFacebook.setOnClickListener {

            val isAppInstalled = appInstalledOrNot("com.facebook.katana")
            if (isAppInstalled) {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/171393949589921"))
                    startActivity(intent)
                } catch (err: ActivityNotFoundException) {
                    Toast.makeText(
                        applicationContext,
                        "App installed but not working on phone..!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://www.facebook.com/onediocom")
                    )
                )
            }
        }
    }

    private fun openTwitter(){
        openTwitter.setOnClickListener{
            Toast.makeText(applicationContext, "Twitter",Toast.LENGTH_LONG).show()
        }
    }

    private fun openInstagram(){
        openInstagram.setOnClickListener{
            Toast.makeText(applicationContext, "İnstagram",Toast.LENGTH_LONG).show()
        }
    }

    private fun openYoutube(){
        openYoutube.setOnClickListener{
            Toast.makeText(applicationContext, "Youtube",Toast.LENGTH_LONG).show()
        }
    }

    private fun openOynakazan(){
        openOynakazan.setOnClickListener{
            Toast.makeText(applicationContext, "Oyna Kazan",Toast.LENGTH_LONG).show()
        }
    }

    private fun appInstalledOrNot(uri: String): Boolean {
        val pm = packageManager
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
        }
        return false
    }

}
