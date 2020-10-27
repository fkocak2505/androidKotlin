package com.weatherlogger.facebooklogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callbackManager = CallbackManager.Factory.create()

        btnOfFacebookLogin.setOnClickListener{
            val listOfReadUserFacebookInfo: MutableList<String> = mutableListOf()
            listOfReadUserFacebookInfo.add("public_profile")
            listOfReadUserFacebookInfo.add("user_friends")


            LoginManager.getInstance()
                .logInWithReadPermissions(
                    this@MainActivity,
                    listOf("public_profile", "email")
                )
        }

        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult?) {

                    Log.i("FB_ACCESS_TOKEN----", loginResult?.accessToken?.token.toString())

                }

                override fun onCancel() {
                    Toast.makeText(
                        applicationContext,
                        "Facebook Login işleminden vazgeçildi",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onError(error: FacebookException?) {

                    var a = 3
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }


}
