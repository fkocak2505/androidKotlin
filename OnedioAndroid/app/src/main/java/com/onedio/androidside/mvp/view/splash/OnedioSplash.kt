package com.onedio.androidside.mvp.view.splash

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.CustomElegantActionListeners
import com.onedio.androidside.common.PopupClass
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.AnalyticsApplication
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
import com.onedio.androidside.remoteConfig.RemoteConfig
import com.onedio.androidside.remoteConfig.RemoteConfigClient
import com.onedio.androidside.remoteConfig.RemoteConfigModel


class OnedioSplash : AppCompatActivity() {

    private lateinit var cApplication: AnalyticsApplication

    private lateinit var remoteConfig: MutableLiveData<RemoteConfig>

    private var theme: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        cApplication = application as AnalyticsApplication

        val sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        val client = RemoteConfigClient(this)
        remoteConfig = client.mutableLiveData
        observeRemoteConfig()

    }

    private fun observeRemoteConfig() {
        remoteConfig.observe(this,
            Observer<RemoteConfig?> { remoteConfig ->
                if (remoteConfig != null) {
                    val objOfRemoteConfig = Gson().fromJson<RemoteConfigModel>(
                        remoteConfig.forceUpdate,
                        RemoteConfigModel::class.java
                    )

                    objOfRemoteConfig?.let {
                        prepareUpdatePopup(objOfRemoteConfig)
                    }?: run{
                        goDashBoard()
                    }
                }
            })
    }

    private fun prepareUpdatePopup(objOfRemoteConfig: RemoteConfigModel) {
        objOfRemoteConfig.latestAndroid?.let {
            if (getCurrentVersion() < it) {
                objOfRemoteConfig.requiredVersionsAndroid?.let { itemOfReqVersionOfAndroid ->
                    if (findVersion(itemOfReqVersionOfAndroid)) {
                        objOfRemoteConfig.forceUpdateMessage?.let { itemOfForceUpdateMessage ->
                            showUpdateDialogIfNeeded(itemOfForceUpdateMessage, true)
                        } ?: run {
                            goDashBoard()
                        }

                    } else {
                        objOfRemoteConfig.defaultMessage?.let { itemOfDefaultForceUpdateMessage ->
                            showUpdateDialogIfNeeded(itemOfDefaultForceUpdateMessage, false)
                        } ?: run {
                            goDashBoard()
                        }
                    }
                } ?: run {
                    goDashBoard()
                }
            } else {
                goDashBoard()
            }
        } ?: run {
            goDashBoard()
        }
    }

    private fun goDashBoard() {
        Handler().postDelayed({
            startActivity(
                Intent(
                    applicationContext,
                    DashboardActivityViewImpl::class.java
                )
            )
        }, 1000)
    }

    private fun findVersion(requiredVersionsOfAndroid: MutableList<String>): Boolean {
        val currentVersion = getCurrentVersion()

        requiredVersionsOfAndroid.forEach { itemOfReqVersionOfAndroid ->
            if (itemOfReqVersionOfAndroid == currentVersion)
                return true
        }

        requiredVersionsOfAndroid.forEach { itemOfReqVersionOfAndroid ->
            if (itemOfReqVersionOfAndroid.contains("*", ignoreCase = true)) {
                val fNumberOfReqVersion = itemOfReqVersionOfAndroid.split(".")[0]
                val fNCurrentVersion = currentVersion.split(".")[0]
                if (fNumberOfReqVersion == fNCurrentVersion)
                    return true
            }
        }

        return false
    }

    private fun getCurrentVersion(): String {
        return applicationContext.packageManager.getPackageInfo(packageName, 0).versionName
    }

    private fun showUpdateDialogIfNeeded(message: String, isForce: Boolean) {
        val dialog = PopupClass(this, isForce)
            /*.setBackgroundTopColor(backgroundTopColor)
            .setBackgroundBottomColor(backgroundBottomColor)*/
            .setCanceledOnTouchOutside(false)
            .setTitleHidden(false)
            .setElegantActionClickListener(object :
                CustomElegantActionListeners {
                override fun onPositiveListener(dialog: PopupClass) {
                    goPlayStore()
                    dialog.dismiss()
                    finish()
                }

                override fun onNegativeListener(dialog: PopupClass) {
                    dialog.dismiss()
                }

                override fun onGotItListener(dialog: PopupClass) {

                    dialog.dismiss()
                }

                override fun onCloseClicked(dialog: PopupClass) {
                    startActivity(
                        Intent(
                            applicationContext,
                            DashboardActivityViewImpl::class.java
                        )
                    )
                }

                override fun onCancelListener(dialog: DialogInterface) {
                    dialog.dismiss()
                }
            })
            .show()

        if (dialog.getTitleIconView() != null) {
            val imageUrl = if (theme == "dark") {
                R.drawable.ic_onedio_logo_popup_dark
            }else{
                R.drawable.ic_onedio_logo_popup
            }


            Glide.with(this).load(imageUrl)
                .into(dialog.getTitleIconView()!!)
            //dialog.getTitleTextView()!!.visibility = View.GONE
            dialog.getContentTextView()!!.text =
                message
            dialog.getPositiveButtonTextView()!!.text = "Uygulamayı Güncelle"
            //dialog.getContentTextView()!!.setTextColor(contentTextColor)
        }
    }

    private fun goPlayStore() {
        val uri: Uri = Uri.parse("market://details?id=$packageName")
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                )
            )
        }
    }
}