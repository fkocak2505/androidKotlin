package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy.changePassword

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Activity
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.dashboard.profileDetail.changePassword.ChangePasswordActivityModelImpl
import com.onedio.androidside.mvp.model.dashboard.profileDetail.changePassword.responseModel.Response4ChangePassword
import com.onedio.androidside.mvp.presenter.dashboard.profileDetail.changePassword.ChangePasswordActivityPresenterImpl
import com.onedio.androidside.mvp.presenter.dashboard.profileDetail.changePassword.IChangePasswordActivityPresenter
import com.onedio.androidside.prefs.StringSharedPrefs
import kotlinx.android.synthetic.main.activity_profile_setting_change_password.*
import kotlinx.android.synthetic.main.jz_layout_std.view.*

class ChangePasswordActivityViewImpl : AppCompatActivity(),
    IChangePasswordActivityView {

    private lateinit var changePasswordActivityPresenter: IChangePasswordActivityPresenter

    private lateinit var toolBar: Toolbar

    private lateinit var sharedPreferences: SharedPreferences

    private var theme: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_setting_change_password)

        val sharedPref = getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        sharedPreferences = getSharedPreferences(
            OnedioConstant.SHARED_PREF_FILE_NAME,
            Context.MODE_PRIVATE
        )

        prepareToolbar()

        initChangePasswordActivityComponent()

        changeComponentTypeface()

        clickEmptyArea()

        clickChangePassword()
    }


    override fun initChangePasswordActivityComponent() {
        //supportActionBar?.hide()

        changePasswordActivityPresenter =
            ChangePasswordActivityPresenterImpl(
                ChangePasswordActivityModelImpl(),
                this
            )
    }

    private fun prepareToolbar() {
        toolBar = toolBar4ChangePassword as Toolbar

        toolBar.title = "Şifre Değiştir"
        toolBar.setTitleTextAppearance(this, R.style.MuliSemiboldTextAppearance)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        toolBar.setOnClickListener {

        }

        if(theme == "dark"){
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)
        }else{
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)
        }

        changeTheme()

    }

    private fun changeTheme(){
        if(theme == "dark"){
            txtOfOldPasswordInfo.background = ContextCompat.getDrawable(applicationContext, R.drawable.custom_edittext_dark_mode)
            txtOfNewPasswordInfo.background = ContextCompat.getDrawable(applicationContext, R.drawable.custom_edittext_dark_mode)
            txtOfNewPasswordInfoVerify.background = ContextCompat.getDrawable(applicationContext, R.drawable.custom_edittext_dark_mode)
        }else{
            txtOfOldPasswordInfo.background = ContextCompat.getDrawable(applicationContext, R.drawable.custom_edittext)
            txtOfNewPasswordInfo.background = ContextCompat.getDrawable(applicationContext, R.drawable.custom_edittext)
            txtOfNewPasswordInfoVerify.background = ContextCompat.getDrawable(applicationContext, R.drawable.custom_edittext)
        }
    }

    override fun changeComponentTypeface() {
        //toolbarTitle.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        txtOfOldPasswordInfo.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        edtOldPassword.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        txtOfNewPasswordInfo.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        edtNewPassword.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        txtOfNewPasswordInfoVerify.typeface =
            OnedioCommon.changeTypeFace4Activity(applicationContext)
        edtNewPasswordVerify.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        changePassword.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
    }

    override fun clickChangePassword() {
        val response4UsersProfile = StringSharedPrefs(
            sharedPreferences,
            OnedioConstant.SHARED_PREF_USER_PROFILE_INFO,
            ""
        ).getValue<String>()

        val response4UsersProfileObj = Gson().fromJson(response4UsersProfile, Response4UsersProfile::class.java)

        var userId: String? = null
        response4UsersProfileObj.data?.let {
            it.id?.let {
                userId = it
            }?: run{
                userId = ""
            }
        }?: run{
            userId = ""
        }

        var email: String? = null
        response4UsersProfileObj.data?.let {
            it.email?.let {
                email = it
            }?: run{
                email = ""
            }
        }?: run{
            email = ""
        }

        var username: String? = null
        response4UsersProfileObj.data?.let {
            it.username?.let {
                username = it
            }?: run{
                username = ""
            }
        }?: run{
            username = ""
        }

        changePassword.setOnClickListener() {
            if (OnedioCommon.isPasswordCountValid(edtOldPassword.text.toString()))
                if (OnedioCommon.isPasswordCountValid(edtNewPassword.text.toString())) {
                    if (edtOldPassword.text.toString() != edtNewPassword.text.toString())
                        if (edtNewPassword.text.toString() == edtNewPasswordVerify.text.toString()){
                            /*changePasswordActivityPresenter.changeUserPassword(
                                Gson().fromJson(OnedioSingletonPattern.instance.getUserProfileInfoData(), Response4UserProfileInfo::class.java).valOfUserProfileInfoData?.email!!,
                                Gson().fromJson(OnedioSingletonPattern.instance.getUserProfileInfoData(), Response4UserProfileInfo::class.java).valOfUserProfileInfoData?.username!!,
                                edtNewPassword.text.toString(),
                                edtOldPassword.text.toString()
                            )*/
                            changePasswordActivityPresenter.changeUserPassword(
                                userId!!,
                                email!!,
                                username!!,
                                edtNewPassword.text.toString(),
                                edtOldPassword.text.toString()
                            )
                        }
                        else
                            showEzDialogMessage(
                                "Bilgilendirme..!",
                                "İki yeni şifre aynı değildir.!",
                                "Tamam",
                                ContextCompat.getColor(
                                    applicationContext,
                                    R.color.tabIndicatorColor4Proile
                                ),
                                ContextCompat.getColor(applicationContext, R.color.constWhite),
                                ContextCompat.getColor(applicationContext, R.color.constTextColor),
                                ContextCompat.getColor(applicationContext, R.color.constTextColor)
                            )
                    else
                        showEzDialogMessage(
                            "Bilgilendirme..!",
                            "Eski şifre ile yeni şifre aynı olamaz.",
                            "Tamam",
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.tabIndicatorColor4Proile
                            ),
                            ContextCompat.getColor(applicationContext, R.color.constWhite),
                            ContextCompat.getColor(applicationContext, R.color.constTextColor),
                            ContextCompat.getColor(applicationContext, R.color.constTextColor)
                        )
                } else
                    showEzDialogMessage(
                        "Bilgilendirme..!",
                        "Yeni şifre en az 6 karakterden oluşmalıdır",
                        "Tamam",
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.tabIndicatorColor4Proile
                        ),
                        ContextCompat.getColor(applicationContext, R.color.constWhite),
                        ContextCompat.getColor(applicationContext, R.color.constTextColor),
                        ContextCompat.getColor(applicationContext, R.color.constTextColor)
                    )
            else
                showEzDialogMessage(
                    "Bilgilendirme..!",
                    "Eski şifre en az 6 karakterden oluşmalıdır..!",
                    "Tamam",
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.tabIndicatorColor4Proile
                    ),
                    ContextCompat.getColor(applicationContext, R.color.constWhite),
                    ContextCompat.getColor(applicationContext, R.color.constTextColor),
                    ContextCompat.getColor(applicationContext, R.color.constTextColor)
                )
        }
    }

    override fun showErrorMessage(response4ErrorObj: Response4ErrorObj) {
        showEzDialogMessage(
            "Bilgilendirme..!",
            "Girilen eski şifre bilgisi doğru değildir..!",
            "Tamam",
            ContextCompat.getColor(
                applicationContext,
                R.color.tabIndicatorColor4Proile
            ),
            ContextCompat.getColor(applicationContext, R.color.constWhite),
            ContextCompat.getColor(applicationContext, R.color.constTextColor),
            ContextCompat.getColor(applicationContext, R.color.constTextColor)
        )
    }

    override fun handleChangePasswordAPIResult(response4ChangePassword: Response4ChangePassword) {
        if (response4ChangePassword.status?.code != 200)
            showEzDialogMessage(
                "Hata..!",
                "Bir hata oluştu..!",
                "Tamam",
                ContextCompat.getColor(
                    applicationContext,
                    R.color.tabIndicatorColor4Proile
                ),
                ContextCompat.getColor(applicationContext, R.color.constWhite),
                ContextCompat.getColor(applicationContext, R.color.constTextColor),
                ContextCompat.getColor(applicationContext, R.color.constTextColor)
            )
        else {
            showEzDialogMessage(
                "Başarılı",
                "Şifre değiştirme başarılı",
                "Tamam",
                ContextCompat.getColor(
                    applicationContext,
                    R.color.tabIndicatorColor4Proile
                ),
                ContextCompat.getColor(applicationContext, R.color.constWhite),
                ContextCompat.getColor(applicationContext, R.color.constTextColor),
                ContextCompat.getColor(applicationContext, R.color.constTextColor)
            )
        }

    }

    override fun showLoading() {
        avlIndicatorProgress.playAnimation()
        avlIndicatorProgress.visibility = View.VISIBLE
        /*window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    override fun hideLoading() {
        avlIndicatorProgress.cancelAnimation()
        avlIndicatorProgress.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    fun clickEmptyArea() {
        changePasswordConstraintLayout.setOnClickListener() {
            hideSoftKeyboard(changePasswordConstraintLayout)
        }
    }

    private fun hideSoftKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun showEzDialogMessage(
        titleText: String,
        messageText: String,
        buttonText: String,
        headerColor: Int,
        titleTextColor: Int,
        messageTextColor: Int,
        buttonTextColor: Int
    ) {
        val ezDialogMessage =
            OnedioEzDialogMessageModel4Activity(
                this,
                titleText,
                messageText,
                buttonText,
                headerColor,
                titleTextColor,
                messageTextColor,
                buttonTextColor
            )

        OnedioCommon.showEzDialog4Activity(ezDialogMessage) {
            onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}