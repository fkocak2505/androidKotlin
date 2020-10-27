package com.onedio.androidside.mvp.view.loginAndRegister.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.github.florent37.viewtooltip.ViewTooltip
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.EmptyCheckModel
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Fragment
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.UserProfileActivityModelImpl
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.dashboard.responseModel.Response4UserProfileInfo
import com.onedio.androidside.mvp.model.loginAndRegister.login.LoginFragmentActivityModelImpl
import com.onedio.androidside.mvp.model.loginAndRegister.login.responseModel.Response4Login
import com.onedio.androidside.mvp.presenter.dashboard.UserProfileInfoActivityPresenterImpl
import com.onedio.androidside.mvp.presenter.loginAndRegister.login.LoginFragmentActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.loginAndRegister.login.forgotPassword.ForgotPasswordActivityViewImpl
import com.onedio.androidside.prefs.StringSharedPrefs
import com.onedio.androidside.singleton.OnedioSingletonPattern
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import spencerstudios.com.ezdialoglib.EZDialog


class LoginFragmentActivityViewImpl : Fragment(),
    ILoginFragmentActivityView {


    private lateinit var viewP: View
    private lateinit var loginFragmentActivityPresenterImpl: LoginFragmentActivityPresenterImpl

    private lateinit var userProfileInfoActivityPresenterImpl: UserProfileInfoActivityPresenterImpl

    private lateinit var callbackManager: CallbackManager

    private lateinit var ePosta: String
    private lateinit var password: String

    private lateinit var articleItem: HugeCardModel

    private lateinit var sharedPreferences: SharedPreferences

    private var mActivity: Activity? = null

    private var theme: String = ""

    //==============================================================================================
    /**
     * Login Create View Func
     */
    //==============================================================================================
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewP = inflater.inflate(R.layout.fragment_login, container, false)

        initLoginFragmentActivityComponent()

        clickLoginButton()

        clickLoginButtonWithFB()

        clickForgotPassword()

        clickEmptyArea()

        val sharedPref = mActivity?.applicationContext?.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref?.getString("mode", "default")!!

        if(theme == "dark"){
            viewP.or.setTextColor(Color.parseColor("#FFFFFF"))
            viewP.txtInfoEPosta.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext_dark_mode)
            viewP.txtInfoPassword.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext_dark_mode)
            viewP.forgotPassword.setTextColor(Color.parseColor("#FFFFFF"))
            viewP.versionInfo.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            viewP.or.setTextColor(Color.parseColor("#191919"))
            viewP.txtInfoEPosta.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext)
            viewP.txtInfoPassword.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext)
            viewP.forgotPassword.setTextColor(Color.parseColor("#191919"))
            viewP.versionInfo.setTextColor(Color.parseColor("#191919"))
        }

        return viewP
    }

    //==============================================================================================
    /**
     * Init Login Component
     */
    //==============================================================================================
    override fun initLoginFragmentActivityComponent() {
        /// Presenter Prop..
        loginFragmentActivityPresenterImpl =
            LoginFragmentActivityPresenterImpl(
                LoginFragmentActivityModelImpl(),
                this
            )

        userProfileInfoActivityPresenterImpl =
            UserProfileInfoActivityPresenterImpl(
                UserProfileActivityModelImpl(), this
            )

        sharedPreferences = mActivity?.getSharedPreferences(
            OnedioConstant.SHARED_PREF_FILE_NAME,
            Context.MODE_PRIVATE
        )!!

        mActivity?.intent?.getStringExtra("ARTICLE_COMMENT_DATA")?.let {
            articleItem = Gson().fromJson(
                mActivity?.intent?.getStringExtra("ARTICLE_COMMENT_DATA"),
                HugeCardModel::class.java
            )
        } ?: run {

        }


        mActivity?.window?.decorView?.clearFocus()

        edtOfTouchListener()
        changeComponentTextFonts()
    }

    //==============================================================================================
    /**
     * Change Component Text Font...
     */
    //==============================================================================================
    override fun changeComponentTextFonts() {
        viewP.btnOfFacebookLogin.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.or.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.edtOfEposta.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.edtOfPassword.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.btnLogin.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.forgotPassword.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.versionInfo.typeface = OnedioCommon.changeTypeFace(activity!!)

    }

    //==============================================================================================
    /**
     * Click Login Button
     */
    //==============================================================================================
    override fun clickLoginButton() {
        viewP.btnLogin.setOnClickListener {

            OnedioSingletonPattern.instance.setFBLogin(false)

            hideSoftKeyboard(loginnConstraintLayout)

            edtOfEposta.clearFocus()
            edtOfPassword.clearFocus()

            ePosta = edtOfEposta.text.toString().trim()
            password = edtOfPassword.text.toString().trim()

            if (checkWarningMessage(ePosta, password))
                if (OnedioCommon.isPasswordCountValid(password))
                //if (OnedioCommon.isEmailValid(ePosta)) {

                    loginFragmentActivityPresenterImpl.doLogin(ePosta, password)
                /*} else {
                    ViewTooltip
                        .on(this, edtOfEposta)
                        .autoHide(true, 3000)
                        .corner(100)
                        .color(Color.parseColor("#FFFFFF"))
                        .textColor(Color.parseColor("#fc3937"))
                        .position(ViewTooltip.Position.TOP)
                        .text("Girilen email adresi geçerli değildir.")
                        .show()

                    edtOfEposta.requestFocus()

                }*/ else {
                    ViewTooltip
                        .on(this, edtOfPassword)
                        .autoHide(true, 3000)
                        .corner(100)
                        .color(Color.parseColor("#FFFFFF"))
                        .textColor(Color.parseColor("#fc3937"))
                        .position(ViewTooltip.Position.TOP)
                        .text("Şifre en az 6 Karakterden oluşmalıdır")
                        .show()

                    edtOfPassword.requestFocus()
                }


        }
    }

    //==============================================================================================
    /**
     * Click Login Button With Facebook
     */
    //==============================================================================================
    override fun clickLoginButtonWithFB() {
        callbackManager = CallbackManager.Factory.create()

        viewP.btnOfFacebookLogin.setOnClickListener {
            val listOfReadUserFacebookInfo: MutableList<String> = mutableListOf()
            listOfReadUserFacebookInfo.add("public_profile")
            listOfReadUserFacebookInfo.add("user_friends")

            OnedioSingletonPattern.instance.setFBLogin(true)

            LoginManager.getInstance().loginBehavior = LoginBehavior.WEB_ONLY

            LoginManager.getInstance()
                .logInWithReadPermissions(
                    this@LoginFragmentActivityViewImpl,
                    listOf("public_profile", "email")
                )

        }

        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult?) {

                    loginFragmentActivityPresenterImpl.doLoginWithFB(loginResult?.accessToken?.token.toString())

                }

                override fun onCancel() {
                    Toast.makeText(
                        mActivity?.applicationContext,
                        "Facebook Login işleminden vazgeçildi",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onError(error: FacebookException?) {

                    showEzDialogMessage(
                        "Bilgilendirme",
                        "Facebook ile giriş sırasında bir hata oluştu..!",
                        "Tamam",
                        ContextCompat.getColor(
                            mActivity?.applicationContext!!,
                            R.color.tabSelectedIndicator
                        ),
                        ContextCompat.getColor(
                            mActivity?.applicationContext!!,
                            R.color.constWhite
                        ),
                        ContextCompat.getColor(
                            mActivity?.applicationContext!!,
                            R.color.constTextColor
                        ),
                        ContextCompat.getColor(
                            mActivity?.applicationContext!!,
                            R.color.constTextColor
                        )
                    )
                }
            })
    }

    //==============================================================================================
    /**
     * Click Forgot Password...
     */
    //==============================================================================================
    override fun clickForgotPassword() {
        viewP.forgotPassword.setOnClickListener {
            startActivity(
                Intent(
                    mActivity?.applicationContext,
                    ForgotPasswordActivityViewImpl::class.java
                )
            )
        }
    }

    //==============================================================================================
    /**
     * Handle Success Login Data
     */
    //==============================================================================================
    override fun handleSuccessLoginResponse(response4Login: Response4Login) {
        if (response4Login.accessToken == null)
            showEzDialogMessage(
                "Bilgilemdirme",
                "Kullanıcı adınız veya parolanız yanlış..!",
                "Tamam",
                ContextCompat.getColor(
                    mActivity?.applicationContext!!,
                    R.color.tabSelectedIndicator
                ), ContextCompat.getColor(
                    mActivity?.applicationContext!!,
                    R.color.constWhite
                ), ContextCompat.getColor(
                    mActivity?.applicationContext!!,
                    R.color.constTextColor
                ),
                ContextCompat.getColor(
                    mActivity?.applicationContext!!,
                    R.color.constTextColor
                )
            )
        else {

            StringSharedPrefs(
                sharedPreferences,
                OnedioConstant.SHARED_PREF_ACCESS_TOKEN,
                ""
            ).setValue(response4Login.accessToken!!)

            //OnedioSingletonPattern.instance.setAccessToken(response4Login.accessToken as String)
            //userProfileInfoActivityPresenterImpl.getUserProfileInfo()
            userProfileInfoActivityPresenterImpl.getUserProfileData("me")
        }
    }

    override fun handleUsersProfileData(response4UsersProfile: Response4UsersProfile) {
        StringSharedPrefs(
            sharedPreferences,
            OnedioConstant.SHARED_PREF_USER_PROFILE_INFO,
            ""
        ).setValue(Gson().toJson(response4UsersProfile))

        //OnedioSingletonPattern.instance.setUserProfileInfoData(Gson().toJson(response4UsersProfile))
        /*OnedioCommon.nStartActivity(
            activity?.applicationContext!!,
            OnedioSingletonPattern.instance.getActivity()
        )*/

        if (::articleItem.isInitialized) {
            val intent =
                Intent(context, OnedioSingletonPattern.instance.getActivity())
            intent.putExtra("ARTICLE_COMMENT_DATA", Gson().toJson(articleItem))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            mActivity?.finish()
            startAnim()
        } else {
            OnedioCommon.nStartActivity(
                mActivity?.applicationContext!!,
                OnedioSingletonPattern.instance.getActivity()
            )
            startAnim()
        }
    }

    override fun handleUserProfileInfoData(response4UserProfileInfo: Response4UserProfileInfo) {

        StringSharedPrefs(
            sharedPreferences,
            OnedioConstant.SHARED_PREF_USER_PROFILE_INFO,
            ""
        ).setValue(Gson().toJson(response4UserProfileInfo))

        /*OnedioSingletonPattern.instance.setUserProfileInfoData(
            Gson().toJson(
                response4UserProfileInfo
            )
        )*/


        /*startActivity(
            Intent(
                activity?.applicationContext,
                DashboardActivityViewImpl::class.java
            )
        )*/

    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(mActivity!!, "r2l")
    }

    //==============================================================================================
    /**
     * Show Loading...
     */
    //==============================================================================================
    override fun showLoading() {
        viewP.avlIndicatorProgress.playAnimation()
        viewP.avlIndicatorProgress.visibility = View.VISIBLE
        /*mActivity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    //==============================================================================================
    /**
     * Hide Loading...
     */
    //==============================================================================================
    override fun hideLoading() {
        viewP.avlIndicatorProgress.cancelAnimation()
        viewP.avlIndicatorProgress.visibility = View.GONE
        //mActivity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    //==============================================================================================
    /**
     * Check All input for Login
     */
    //==============================================================================================
    override fun checkWarningMessage(ePosta: String, password: String): Boolean {
        val listOfValidator: MutableList<EmptyCheckModel> = mutableListOf()
        //listOfValidator.add(EmptyCheckModel(ePosta, true, "E-Posta", edtOfEposta))

        //if (checkAllEmailValidation()) {
        listOfValidator.add(
            EmptyCheckModel(
                password,
                true,
                "Şifre",
                edtOfPassword
            )
        )

        if (OnedioCommon.emptyCheck(listOfValidator).status != null) {
            ViewTooltip
                .on(this, OnedioCommon.emptyCheck(listOfValidator).component)
                .autoHide(true, 3000)
                .corner(100)
                .color(Color.parseColor("#FFFFFF"))
                .textColor(Color.parseColor("#fc3937"))
                .position(ViewTooltip.Position.TOP)
                .text(OnedioCommon.emptyCheck(listOfValidator).errorMessage)
                .show()

            OnedioCommon.emptyCheck(listOfValidator).component?.requestFocus()

            return false
        }
        return true
        /*} else
            return false*/
    }

    //==============================================================================================
    /**
     * Check Email Validation...
     */
    //==============================================================================================
    private fun checkAllEmailValidation(): Boolean {
        if (edtOfEposta.text.toString().trim() != "")
            if (OnedioCommon.isEmailValid(edtOfEposta.text.toString().trim()))
                return true
            else {
                ViewTooltip
                    .on(this, edtOfEposta)
                    .autoHide(true, 3000)
                    .corner(100)
                    .color(Color.parseColor("#FFFFFF"))
                    .textColor(Color.parseColor("#fc3937"))
                    .position(ViewTooltip.Position.TOP)
                    .text("Girilen email adresi geçerli değildir.")
                    .show()

                edtOfEposta.requestFocus()

                return false
            }
        else {
            ViewTooltip
                .on(this, edtOfEposta)
                .autoHide(true, 3000)
                .corner(100)
                .color(Color.parseColor("#FFFFFF"))
                .textColor(Color.parseColor("#fc3937"))
                .position(ViewTooltip.Position.TOP)
                .text("E-Posta alanı boş bırakılamaz")
                .show()

            edtOfEposta.requestFocus()
            return false
        }

    }

    //==============================================================================================
    /**
     * Show Error Message...
     */
    //==============================================================================================
    override fun showError(response4ErrorObj: Response4ErrorObj) {
        showEzDialogMessage(
            "Uyarı..!",
            response4ErrorObj.status?.message!!,
            "Tamam",
            ContextCompat.getColor(
                mActivity?.applicationContext!!,
                R.color.tabSelectedIndicator
            ), ContextCompat.getColor(
                mActivity?.applicationContext!!,
                R.color.constWhite
            ), ContextCompat.getColor(
                mActivity?.applicationContext!!,
                R.color.constTextColor
            ),
            ContextCompat.getColor(
                mActivity?.applicationContext!!,
                R.color.constTextColor
            )
        )
    }

    //==============================================================================================
    /**
     * Handle After Facebook Login..
     */
    //==============================================================================================
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    //==============================================================================================
    /**
     * Click Empty Area...
     */
    //==============================================================================================
    override fun clickEmptyArea() {
        viewP.loginnConstraintLayout.setOnClickListener {
            hideSoftKeyboard(loginnConstraintLayout)
        }
    }

    //==============================================================================================
    /**
     * Hide Soft Keyboard...
     */
    //==============================================================================================
    private fun hideSoftKeyboard(view: View) {
        val inputMethodManager =
            mActivity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //==============================================================================================
    /**
     * Touch EditText Listener...
     */
    //==============================================================================================
    override fun edtOfTouchListener() {
        viewP.edtOfEposta.setOnTouchListener { _, _ ->
            mActivity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            false
        }

        viewP.edtOfPassword.setOnTouchListener { _, _ ->
            mActivity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            false
        }
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
            OnedioEzDialogMessageModel4Fragment(
                mActivity!!,
                titleText,
                messageText,
                buttonText,
                headerColor,
                titleTextColor,
                messageTextColor,
                buttonTextColor
            )

        OnedioCommon.showEzDialog4Fragment(ezDialogMessage) {}
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity) {
            mActivity = context
        }
    }

}