package com.onedio.androidside.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.*
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdView
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Activity
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Fragment
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.AnalyticsApplication
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.FavoriteDataInfo
import spencerstudios.com.ezdialoglib.EZDialog
import java.lang.Long
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern
import kotlin.math.abs

class OnedioCommon {

    //==================================================================================================================
    /**
     * Dynamic Input isEmpty Method
     * This Method will use all input or needed validate component
     */
    //==================================================================================================================
    companion object {
        fun emptyCheck(emptyCheckModel: MutableList<EmptyCheckModel>): EmptyCheckModel {
            var emptyObject = EmptyCheckModel(null, null, null, null)
            for (i in 0 until emptyCheckModel.size) {
                if (!stringCheck(
                        emptyCheckModel.get(i).componentVal as String
                    )
                ) {
                    emptyObject = EmptyCheckModel(
                        emptyCheckModel.get(i).componentVal,
                        false, emptyCheckModel.get(i).errorMessage + " alanı boş bırakılamaz",
                        emptyCheckModel[i].component
                    )
                    break
                }
            }
            return emptyObject
        }

        //==================================================================================================================
        /**
         * String Empty Check..
         */
        //==================================================================================================================
        fun stringCheck(stringVal: String): Boolean = when {
            stringVal.trim().equals("") -> false
            else -> true
        }

        fun nullChecker(valOfString: String?): Boolean = when {
            valOfString == null -> false
            else -> true
        }

        //==================================================================================================================
        /**
         * Email Validation Regex
         */
        //==================================================================================================================
        fun isEmailValid(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        //==================================================================================================================
        /**
         * Password Count Validator
         */
        //==================================================================================================================
        fun isPasswordCountValid(password: String): Boolean {
            return password.length >= 6
        }

        //==================================================================================================================
        /**
         * Password Validator
         */
        //==================================================================================================================
        fun isPasswordValid(password: String): Boolean {
            val pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}"
            return password.matches(pattern.toRegex())
        }

        //==================================================================================================================
        /**
         * TypeFace Prop..
         */
        //==================================================================================================================
        fun changeTypeFace(activity: FragmentActivity): Typeface {
            return Typeface.createFromAsset(
                activity.applicationContext?.assets,
                "muli-regular.ttf"
            )
        }

        //==================================================================================================================
        /**
         * TypeFace Prop..
         */
        //==================================================================================================================
        fun changeTypeFace4Activity(context: Context): Typeface {
            return Typeface.createFromAsset(context?.assets, "muli-regular.ttf")
        }

        //==================================================================================================================
        /**
         * TypeFace Prop..
         */
        //==================================================================================================================
        fun changeTypeFaceBold(activity: FragmentActivity): Typeface {
            return Typeface.createFromAsset(activity?.applicationContext?.assets, "muli-bold.ttf")
        }

        //==================================================================================================================
        /**
         * TypeFace Prop..
         */
        //==================================================================================================================
        fun changeTypeFace4ActivityBold(context: Context): Typeface {
            return Typeface.createFromAsset(context?.assets, "muli-bold.ttf")
        }

        //==================================================================================================================
        /**
         * TypeFace Prop..
         */
        //==================================================================================================================
        fun changeTypeFaceSemiBold(activity: FragmentActivity): Typeface {
            return Typeface.createFromAsset(
                activity?.applicationContext?.assets,
                "muli-semibold.ttf"
            )
        }

        //==================================================================================================================
        /**
         * TypeFace Prop..
         */
        //==================================================================================================================
        fun changeTypeFace4ActivitySemiBold(context: Context): Typeface {
            return Typeface.createFromAsset(context?.assets, "muli-semibold.ttf")
        }

        //==================================================================================================================
        /**
         * Dp to Px..
         */
        //==================================================================================================================
        fun dpToPx(dp: Int): Int {
            return (dp * Resources.getSystem().getDisplayMetrics().density).toInt()
        }

        //==================================================================================================================
        /**
         * Convert EpochTime to Date..
         */
        //==================================================================================================================
        @SuppressLint("SimpleDateFormat")
        fun convertEpochTime2Date(strOfEpochDate: String): String {
            return SimpleDateFormat("yyyy-MM-dd / HH:mm").format(
                Date(
                    Long.parseLong(
                        strOfEpochDate
                    )
                )
            )
        }

        //==================================================================================================================
        /**
         * Convert Date 2 Time...
         */
        //==================================================================================================================
        fun convertDate2Time(strOfDate: String): String {
            return strOfDate.split("/")[1]
        }


        //==================================================================================================================
        /**
         * Get Diff Time By Days 4 Two Date..
         */
        //==================================================================================================================
        @SuppressLint("SimpleDateFormat")
        fun getDiffTwoDate(firstDate: String, secondDate: String): kotlin.Long {
            var dateFirst = SimpleDateFormat("yyyy-MM-dd").parse(firstDate)
            var dateSecond = SimpleDateFormat("yyyy-MM-dd").parse(secondDate)
            return TimeUnit.DAYS.convert(
                abs(dateSecond!!.time - dateFirst!!.time),
                TimeUnit.MILLISECONDS
            )
        }

        //==================================================================================================================
        /**
         * change Date Format yyyy-MM-dd to MMMM dd, yyyy
         */
        //==================================================================================================================
        @SuppressLint("SimpleDateFormat")
        fun changeDateFormat2Text(stringDate: String): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val d = sdf.parse(stringDate)
            sdf.applyPattern("MMMM dd, yyyy")
            return sdf.format(d!!)
        }

        //==================================================================================================================
        /**
         * change Date Format MMMM dd, yyyy to yyyy-MM-dd
         */
        //==================================================================================================================
        @SuppressLint("SimpleDateFormat")
        fun changeDateFormatFromText(stringDate: String): String {
            val sdf = SimpleDateFormat("MMMM dd, yyyy")
            val d = sdf.parse(stringDate)
            sdf.applyPattern("yyyy-MM-dd")
            return sdf.format(d!!)
        }

        //==================================================================================================================
        /**
         * Get Current Date by yyyy-MM-dd..
         */
        //==================================================================================================================
        @SuppressLint("SimpleDateFormat")
        fun getCurrentDate(): String {
            return SimpleDateFormat("yyyy-MM-dd").format(Date(System.currentTimeMillis()))
        }

        //==================================================================================================================
        /**
         * Get Date UTC to dd/MM/yyyy
         */
        //==================================================================================================================
        @SuppressLint("SimpleDateFormat")
        fun getUTCDate2Date(utcDate: String): String {
            val date = utcDate.substring(4, 15)
            val sdf = SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH)
            val d = sdf.parse(date)
            sdf.applyPattern("dd/MM/yyyy")
            return sdf.format(d!!)
        }

        //==================================================================================================================
        /**
         * Get Date New Date Format to dd/MM/yyyy
         */
        //==================================================================================================================
        @SuppressLint("SimpleDateFormat")
        fun convertFeedDate(utcDate: String): String {
            var sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            var outputFormat = SimpleDateFormat("dd/MM/yyyy")
            var date = sdf.parse(utcDate)
            return outputFormat.format(date!!)
        }

        //==================================================================================================================
        /**
         * Get Date New Date Format to dd/MM/yyyy
         */
        //==================================================================================================================
        @SuppressLint("SimpleDateFormat")
        fun convertFeedDate2(utcDate: String): String {
            var sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            var outputFormat = SimpleDateFormat("yyyy-MM-dd")
            var date = sdf.parse(utcDate)
            return outputFormat.format(date!!)
        }

        //==================================================================================================================
        /**
         * Compare 2 String..
         */
        //==================================================================================================================
        fun stringCompare(s1: String, s2: String): Boolean {
            return s1 >= s2
        }

        //==================================================================================================================
        /**
         * Show EzDialog 4 Fragment...
         */
        //==================================================================================================================
        fun showEzDialog4Fragment(
            onedioEzDialogMessage: OnedioEzDialogMessageModel4Fragment,
            callback: () -> Unit
        ) {
            try {
                EZDialog.Builder(onedioEzDialogMessage.activity)
                    .setTitle(onedioEzDialogMessage.title)
                    .setMessage(onedioEzDialogMessage.message)
                    .setPositiveBtnText(onedioEzDialogMessage.buttonText)
                    .setHeaderColor(onedioEzDialogMessage.headerColor)
                    .setTitleTextColor(onedioEzDialogMessage.titleTextColor)
                    .setMessageTextColor(onedioEzDialogMessage.messageTextColor)
                    .setButtonTextColor(onedioEzDialogMessage.buttonTextColor)
                    .setCancelableOnTouchOutside(true)
                    .OnPositiveClicked {
                        callback()
                    }
                    .build()
            } catch (e: WindowManager.BadTokenException) {
                sendLog2Crashlytics("POPUP----$e")
            }
        }

        //==================================================================================================================
        /**
         * Show EzDialog 4 Activity...
         */
        //==================================================================================================================
        fun showEzDialog4Activity(
            onedioEzDialogMessage: OnedioEzDialogMessageModel4Activity,
            callback: () -> Unit
        ) {

            try {
                EZDialog.Builder(onedioEzDialogMessage.context)
                    .setTitle(onedioEzDialogMessage.title)
                    .setMessage(onedioEzDialogMessage.message)
                    .setPositiveBtnText(onedioEzDialogMessage.buttonText)
                    .setHeaderColor(onedioEzDialogMessage.headerColor)
                    .setTitleTextColor(onedioEzDialogMessage.titleTextColor)
                    .setMessageTextColor(onedioEzDialogMessage.messageTextColor)
                    .setButtonTextColor(onedioEzDialogMessage.buttonTextColor)
                    .setCancelableOnTouchOutside(true)
                    .OnPositiveClicked {
                        callback()
                    }
                    .build()
            } catch (e: WindowManager.BadTokenException) {
                sendLog2Crashlytics("POPUP----$e")
            }


        }

        //==================================================================================================================
        /**
         * Check User Login
         */
        //==================================================================================================================
        fun isUserLogin(): Boolean {
            val sharedPreferences = getSharedPref()
            val accessToken = getAccessToken(sharedPreferences)
            return accessToken != " "

            //return OnedioSingletonPattern.instance.getAccessToken() != " "

        }

        //==================================================================================================================
        /**
         * Convert Map To String..
         */
        //==================================================================================================================
        fun convertMap2String(map: Map<String, MutableList<String>>): String {
            val mapAsString = StringBuilder("")
            for (key in map.keys) {
                val commaSeparator = map[key]?.joinToString("-")
                mapAsString.append("$key=$commaSeparator, ")
            }
            mapAsString.delete(mapAsString.length - 2, mapAsString.length).append("")
            return mapAsString.toString()
        }

        //==================================================================================================================
        /**
         * Convert String To Map..
         */
        //==================================================================================================================
        fun convertString2Map(mapAsString: String): Map<String, MutableList<String>> {
            return mapAsString.split(", ").associate {
                val (left, right) = it.split("=")
                left to right.split("-").toMutableList()
            }
        }

        //==================================================================================================================
        /**
         * Share Article
         */
        //==================================================================================================================
        fun shareArticle(activity: Activity, legacyId: kotlin.Long) {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
            var emojiType = String(Character.toChars(0x1F440))
            var shareMessage = "Bunu gördün mü? $emojiType\n\n"
            shareMessage += "https://onedio.com/h/$legacyId\n\n"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            Toast.makeText(
                activity.applicationContext,
                "$shareMessage kopyalandı",
                Toast.LENGTH_SHORT
            ).show()

            val clipBoard: ClipboardManager =
                activity.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData: ClipData = ClipData.newPlainText("label", shareMessage)
            clipBoard.setPrimaryClip(clipData)


            activity.startActivity(Intent.createChooser(shareIntent, "Seçiniz.."))
        }

        //==================================================================================================================
        /**
         * StartActivity With Custom..
         */
        //==================================================================================================================
        fun cStartActivity(context: Context, className: Class<out Activity>) {
            val intent =
                Intent(context, className)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

        //==================================================================================================================
        /**
         * StartActivity..
         */
        //==================================================================================================================
        fun nStartActivity(context: Context, className: Class<out Activity>) {
            val intent =
                Intent(context, className)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

        //==================================================================================================================
        /**
         * Check Network Connection
         */
        //==================================================================================================================
        fun checkNetwork(context: Context): Boolean {
            val mgr =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = mgr.activeNetworkInfo
            return netInfo?.isConnected ?: false
        }

        //==================================================================================================================
        /**
         * Open Onedio Facebook Home Page
         */
        //==================================================================================================================
        fun openFacebook(context: Context): String {
            val facebookUrl = "https://www.facebook.com/onediocom"
            val facebookPageId = "onediocom"

            val packageManager = context.packageManager
            return try {
                val versionCode =
                    packageManager.getPackageInfo("com.facebook.katana", 0).versionCode
                if (versionCode >= 3002850) {
                    "fb://facewebmodal/f?href=$facebookUrl"
                } else {
                    "fb://page/$facebookPageId"
                }
            } catch (e: PackageManager.NameNotFoundException) {
                facebookUrl //normal web url
            }
        }

        //==================================================================================================================
        /**
         * Open Onedio Twitter Home Page
         */
        //==================================================================================================================
        fun openTwitter(context: Context): String {
            val packageManager = context.packageManager
            return try {

                packageManager.getPackageInfo("com.facebook.katana", 0)
                "https://twitter.com/onediocom"

            } catch (e: PackageManager.NameNotFoundException) {
                "https://twitter.com/onediocom"
            }
        }

        //==================================================================================================================
        /**
         * Open Onedio Youtube Home Page
         */
        //==================================================================================================================
        fun openYoutube(context: Context): String {
            val packageManager = context.packageManager
            return try {
                packageManager.getPackageInfo("com.google.android.youtube", 0)
                "https://www.youtube.com/channel/onediotube"

            } catch (e: PackageManager.NameNotFoundException) {
                try {
                    packageManager.getPackageInfo("com.google.android.youtube.PlayerActivity", 0)
                    "https://www.youtube.com/user/onediotube"
                } catch (e: PackageManager.NameNotFoundException) {
                    "https://www.youtube.com/user/onediotube"
                }
            }
        }

        //==================================================================================================================
        /**
         * Open Onedio Instagram Home Page
         */
        //==================================================================================================================
        fun openInstagram(context: Context): String {
            val packageManager = context.packageManager
            return try {
                packageManager.getPackageInfo("com.instagram.android", 0)
                "http://instagram.com/_u/onediocom"

            } catch (e: PackageManager.NameNotFoundException) {
                "http://instagram.com/onediocom"
            }
        }

        //==================================================================================================================
        /**
         * Open Onedio OynaKazan Home Page
         */
        //==================================================================================================================
        fun openOynakazan(context: Context): String {
            val packageManager = context.packageManager
            return try {
                packageManager.getPackageInfo("com.oynakazanapp.android", 0)
                "market://details?id=com.oynakazanapp.android"

            } catch (e: PackageManager.NameNotFoundException) {
                "https://play.google.com/store/apps/details?id=com.oynakazanapp.android"
            }
        }

        //==================================================================================================================
        /**
         * Convert List 2 String
         */
        //==================================================================================================================
        fun convertList2String(list: MutableList<FavoriteDataInfo>): String {
            return Gson().toJson(list)
        }

        //==================================================================================================================
        /**
         * Convert String 2 List
         */
        //==================================================================================================================
        fun convertString2List(str: String): MutableList<FavoriteDataInfo> {
            val listType = object : TypeToken<MutableList<FavoriteDataInfo>>() {

            }.type

            return Gson().fromJson(str, listType)

        }

        //==================================================================================================================
        /**
         * Convert List 2 String
         */
        //==================================================================================================================
        fun convertList2StringStr(list: MutableList<String>): String {
            return Gson().toJson(list)
        }

        //==================================================================================================================
        /**
         * Convert String 2 List
         */
        //==================================================================================================================
        fun convertString2ListStr(str: String): MutableList<String> {
            val listType = object : TypeToken<MutableList<String>>() {

            }.type

            return Gson().fromJson(str, listType)

        }

        //==================================================================================================================
        /**
         * Send Event 2 Firebase Crashlytics
         */
        //==================================================================================================================
        fun sendLog2Crashlytics(eventMessage: String) {
            FirebaseCrashlytics.getInstance().log(eventMessage)
        }

        //==================================================================================================================
        /**
         * Convert String 4 Taboola Url
         */
        //==================================================================================================================
        fun convertString4TaboolaUrl(title: String): String {
            var articleTitle = title
            /*articleTitle = articleTitle.replace(" ", "-")*/

            if (articleTitle.first().toString() == "'")
                articleTitle = articleTitle.removePrefix("'")
            if (articleTitle.last().toString() == "'")
                articleTitle = articleTitle.removeSuffix("'")

            articleTitle = articleTitle.replace("İ", "i")
            articleTitle = articleTitle.toLowerCase()


            val builder = StringBuilder(articleTitle)
            val regex = "[;/:*?\"<>|&]"
            val pattern = Pattern.compile(regex)
            val matcher = pattern.matcher(builder.toString())
            articleTitle = matcher.replaceAll("")

            articleTitle = articleTitle.replace("!", "")
            articleTitle = articleTitle.replace(".", "")
            articleTitle = articleTitle.replace(",", "")

            articleTitle = articleTitle.replace(" '", "-")
            articleTitle = articleTitle.replace("' ", "-")
            articleTitle = articleTitle.replace(" ’", "-")
            articleTitle = articleTitle.replace("’ ", "-")
            articleTitle = articleTitle.replace("'", "-")
            articleTitle = articleTitle.replace("’", "-")
            articleTitle = articleTitle.replace(" ", "-")

            val turkishChars = charArrayOf(
                0x131.toChar(),
                0x130.toChar(),
                0xFC.toChar(),
                0xDC.toChar(),
                0xF6.toChar(),
                0xD6.toChar(),
                0x15F.toChar(),
                0x15E.toChar(),
                0xE7.toChar(),
                0xC7.toChar(),
                0x11F.toChar(),
                0x11E.toChar()
            )
            val englishChars =
                charArrayOf('i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G')
            for (i in turkishChars.indices) {
                articleTitle = articleTitle.replace(
                    String(charArrayOf(turkishChars[i])),
                    String(charArrayOf(englishChars[i]))
                )
            }

            return articleTitle

        }

        //==================================================================================================================
        /**
         * Load MoastHead Ads..
         */
        //==================================================================================================================
        fun loadMastheadAd(publisherAdView: PublisherAdView, logTagname: String) {
            publisherAdView.visibility = View.VISIBLE
            val adRequestBottom = PublisherAdRequest.Builder().build()
            publisherAdView.loadAd(adRequestBottom)
            publisherAdView.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    Log.i(logTagname + "_AD_LOADED -", "onAdLoaded")
                }

                override fun onAdFailedToLoad(errorCode: Int) {
                    Log.i(logTagname + "_AD_FAILED -", "onAdFailedToLoad")
                    publisherAdView.visibility = View.GONE
                }

                override fun onAdOpened() {
                    Log.i(logTagname + "_AD_OPENED -", "onAdOpened")
                }

                override fun onAdClicked() {
                    Log.i(logTagname + "_AD_CLICKED -", "onAdClicked")
                }

                override fun onAdLeftApplication() {
                    Log.i(logTagname + "_AD_LEFTAPP -", "onAdLeftApplication")
                }

                override fun onAdClosed() {
                    Log.i(logTagname + "_AD_CLOSED -", "onAdClosed")
                }
            }
        }

        //==================================================================================================================
        /**
         * Get SharedPref..
         */
        //==================================================================================================================
        fun getSharedPref(): SharedPreferences? {
            AnalyticsApplication.sharedPrefInstance?.let {
                it.applicationContext?.let {
                    return it.getSharedPreferences(
                        OnedioConstant.SHARED_PREF_FILE_NAME,
                        Context.MODE_PRIVATE
                    )
                } ?: run {
                    return null
                }
            } ?: run {
                return null
            }
        }

        //==================================================================================================================
        /**
         * Get Access Token..
         */
        //==================================================================================================================
        fun getAccessToken(sharedPreferences: SharedPreferences?): String? {
            sharedPreferences?.let {
                return it.getString(OnedioConstant.SHARED_PREF_ACCESS_TOKEN, " ")
            } ?: run {
                return " "
            }
        }

        //==================================================================================================================
        /**
         * Get Token..
         */
        //==================================================================================================================
        fun getTokken(sharedPreferences: SharedPreferences?): String {
            sharedPreferences?.let {
                it.getString(OnedioConstant.SHARED_PREF_TOKEN, " ")?.let {
                    return it
                } ?: run {
                    return " "
                }
            } ?: run {
                return " "
            }
        }
    }
}