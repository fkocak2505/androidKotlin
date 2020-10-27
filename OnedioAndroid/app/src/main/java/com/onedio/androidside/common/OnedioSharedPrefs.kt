package com.onedio.androidside.common

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.onedio.androidside.mvp.model.dashboard.model.ApplicationSettingModel
import com.onedio.androidside.mvp.model.dashboard.model.NotificationSettingModel
import com.onedio.androidside.mvp.model.dashboard.model.SecurityPrivacyModel

class OnedioSharedPrefs(var context: Context) {

    lateinit var sharedPreferences: SharedPreferences

    val NIGHT_MODE = "NIGHT_MODE"
    val APPLICATION_SETTING = "APPLICATION_SETTING"
    val NOTIFICATION_SETTING = "NOTIFICATION_SETTING"
    val SECURITY_SETTING = "SECURITY_SETTING"


    fun isNightModeEnabled(): Boolean {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getBoolean(NIGHT_MODE, false)
    }

    fun setIsNightModeEnabled(isNightModeEnabled: Boolean) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.edit().putBoolean(NIGHT_MODE, isNightModeEnabled).apply()
    }

    fun saveApplicationSetting(mListOfApplicationSettingModel: MutableList<ApplicationSettingModel>) {
        var mListOfApplicationSettingByString: MutableList<String> = mutableListOf()

        for (itemOfApplicationSetting in mListOfApplicationSettingModel) {
            mListOfApplicationSettingByString.add(Gson().toJson(itemOfApplicationSetting))
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.edit().putStringSet(APPLICATION_SETTING, mListOfApplicationSettingByString.toSet()).apply()
    }


    fun getApplicationSetting(): MutableList<ApplicationSettingModel>? {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        var mListOfApplicationSettingModel: MutableList<ApplicationSettingModel> = mutableListOf()

        if (sharedPreferences.getStringSet(APPLICATION_SETTING, null) != null) {
            val mListOfApplicationSettingByString =
                sharedPreferences.getStringSet(APPLICATION_SETTING, null)!!.toMutableList()

            for (itemOfApplicationSetting in mListOfApplicationSettingByString) {
                mListOfApplicationSettingModel.add(
                    Gson().fromJson(
                        itemOfApplicationSetting,
                        ApplicationSettingModel::class.java
                    )
                )
            }
        }

        return mListOfApplicationSettingModel
    }

    fun saveNotificationSetting(mListOfNotificatioNSettingModel: MutableList<NotificationSettingModel>) {
        var mListOfNotificationSettingByString: MutableList<String> = mutableListOf()

        for (itemOfNotificationSetting in mListOfNotificatioNSettingModel) {
            mListOfNotificationSettingByString.add(Gson().toJson(itemOfNotificationSetting))
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.edit().putStringSet(NOTIFICATION_SETTING, mListOfNotificationSettingByString.toSet()).apply()
    }

    fun getNotificationSetting(): MutableList<NotificationSettingModel>? {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        var mListOfNotificationSettingModel: MutableList<NotificationSettingModel> = mutableListOf()

        if (sharedPreferences.getStringSet(NOTIFICATION_SETTING, null) != null) {
            val mListOfNotificationSettingByString =
                sharedPreferences.getStringSet(NOTIFICATION_SETTING, null)!!.toMutableList()

            for (itemOfApplicationSetting in mListOfNotificationSettingByString) {
                mListOfNotificationSettingModel.add(
                    Gson().fromJson(
                        itemOfApplicationSetting,
                        NotificationSettingModel::class.java
                    )
                )
            }
        }

        return mListOfNotificationSettingModel
    }

    fun saveSecuritySetting(mListOfSecuritySettingModel: MutableList<SecurityPrivacyModel>) {
        var mListOfSecuritySettingByString: MutableList<String> = mutableListOf()

        for (itemOfNotificationSetting in mListOfSecuritySettingModel) {
            mListOfSecuritySettingByString.add(Gson().toJson(itemOfNotificationSetting))
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.edit().putStringSet(SECURITY_SETTING, mListOfSecuritySettingByString.toSet()).apply()
    }

    fun getSecuritySetting(): MutableList<SecurityPrivacyModel> {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        var mListOSecuritySettingModel: MutableList<SecurityPrivacyModel> = mutableListOf()

        if (sharedPreferences.getStringSet(SECURITY_SETTING, null) != null) {
            val mListOfNotificationSettingByString =
                sharedPreferences.getStringSet(SECURITY_SETTING, null)!!.toMutableList()

            for (itemOfApplicationSetting in mListOfNotificationSettingByString) {
                mListOSecuritySettingModel.add(
                    Gson().fromJson(
                        itemOfApplicationSetting,
                        SecurityPrivacyModel::class.java
                    )
                )
            }
        }

        return mListOSecuritySettingModel
    }

}