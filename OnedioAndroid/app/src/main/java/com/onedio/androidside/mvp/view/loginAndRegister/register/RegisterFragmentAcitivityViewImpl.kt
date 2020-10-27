package com.onedio.androidside.mvp.view.loginAndRegister.register

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.facebook.CallbackManager
import com.github.florent37.viewtooltip.ViewTooltip
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import kotlinx.android.synthetic.main.fragment_register.view.avlIndicatorProgress
import kotlinx.android.synthetic.main.fragment_register.view.edtOfEposta
import kotlinx.android.synthetic.main.fragment_register.view.edtOfPassword
import com.onedio.androidside.R
import com.onedio.androidside.common.EmptyCheckModel
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Fragment
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.loginAndRegister.register.RegisterFragmentActivityModelImpl
import com.onedio.androidside.mvp.model.loginAndRegister.register.responseModel.Response4IsMailExist
import com.onedio.androidside.mvp.model.loginAndRegister.register.responseModel.Response4Register
import com.onedio.androidside.mvp.presenter.loginAndRegister.register.RegisterFragmentAcitivityPresenterImpl
import com.onedio.androidside.mvp.view.loginAndRegister.LoginAndRegisterDashboardActivityViewImpl
import com.onedio.androidside.mvp.view.loginAndRegister.register.customDialog.UserAggreementDialog

class RegisterFragmentAcitivityViewImpl : Fragment(),
    IRegisterFragmentAcitivityView {

    private lateinit var viewP: View
    private lateinit var registerFragmentAcitivityPresenterImpl: RegisterFragmentAcitivityPresenterImpl

    private lateinit var callbackManager: CallbackManager

    //// Data 4 Request
    private lateinit var ePosta: String
    private lateinit var userName: String
    private lateinit var name: String
    private lateinit var password: String
    private lateinit var veriftPassword: String

    private var mActivity: Activity? = null

    private var theme: String = ""

    //==================================================================================================================
    /**
     * Register OnCreateView Func..
     */
    //==================================================================================================================
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewP = inflater.inflate(R.layout.fragment_register, container, false)

        val sharedPref = mActivity?.applicationContext?.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref?.getString("mode", "default")!!

        initRegisterFragmentActivityComponent()
        clickRegisterButton()

        clickEmptyArea()

        viewP.checkBox.setOnCheckedChangeListener{ view, isChecked ->
            if(isChecked){
                val customDialog =
                    UserAggreementDialog(
                        mActivity!!
                    )

                val lp = WindowManager.LayoutParams()
                lp.copyFrom(customDialog.window?.attributes)
                lp.width = WindowManager.LayoutParams.MATCH_PARENT
                lp.height = WindowManager.LayoutParams.MATCH_PARENT
                customDialog.show()
                customDialog.window?.attributes = lp

                customDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                customDialog.show()
            }else
                view.isChecked = false
        }

        if(theme == "dark"){
            viewP.ePosta.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext_dark_mode)
            viewP.userName.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext_dark_mode)
            viewP.nameAndSurname.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext_dark_mode)
            viewP.password.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext_dark_mode)
            viewP.passwordVerify.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext_dark_mode)

            viewP.checkBox.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            viewP.ePosta.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext)
            viewP.userName.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext)
            viewP.nameAndSurname.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext)
            viewP.password.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext)
            viewP.passwordVerify.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext)

            viewP.checkBox.setTextColor(Color.parseColor("#191919"))
        }

        return viewP
    }

    //==================================================================================================================
    /**
     * Init Register Component
     */
    //==================================================================================================================
    override fun initRegisterFragmentActivityComponent() {

        changeComponentTextFonts()

        mActivity?.window?.decorView?.clearFocus()

        edtOfTouchListener()

        registerFragmentAcitivityPresenterImpl =
            RegisterFragmentAcitivityPresenterImpl(
                RegisterFragmentActivityModelImpl(),
                this
            )
    }

    override fun changeComponentTextFonts() {
        viewP.edtOfEposta.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.edtOfUsername.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.edtOfName.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.edtOfPassword.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.edtOfPasswordVerify.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.edtOfPasswordVerify.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.checkBox.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.btnRegister.typeface = OnedioCommon.changeTypeFace(activity!!)
    }

    //==================================================================================================================
    /**
     * Click Register Button
     */
    //==================================================================================================================
    override fun clickRegisterButton() {
        viewP.btnRegister.setOnClickListener() {

            hideSoftKeyboard(registerConstraintLayout)

            edtOfEposta.clearFocus()
            edtOfUsername.clearFocus()
            edtOfName.clearFocus()
            edtOfPassword.clearFocus()
            edtOfPasswordVerify.clearFocus()


            ePosta = edtOfEposta.text.toString().trim()
            userName = edtOfUsername.text.toString().trim()
            name = edtOfName.text.toString().trim()
            password = edtOfPassword.text.toString().trim()
            veriftPassword = edtOfPasswordVerify.text.toString().trim()

            if (checkWarningMessage(ePosta, userName, name, password, veriftPassword))
                if (OnedioCommon.isPasswordCountValid(password))
                    if (OnedioCommon.isEmailValid(ePosta))
                        if (password.equals(veriftPassword))
                            if (checkBox.isChecked)
                                registerFragmentAcitivityPresenterImpl.isMailExist(ePosta)
                            else {
                                ViewTooltip
                                    .on(this, checkBox)
                                    .autoHide(true, 3000)
                                    .corner(100)
                                    .color(Color.parseColor("#FFFFFF"))
                                    .textColor(Color.parseColor("#fc3937"))
                                    .position(ViewTooltip.Position.TOP)
                                    .text("Kullanıcı Sözleşmesini onaylayınız")
                                    .show()

                                checkBox.requestFocus()
                            }
                        else {
                            ViewTooltip
                                .on(this, edtOfPassword)
                                .autoHide(true, 3000)
                                .corner(100)
                                .color(Color.parseColor("#FFFFFF"))
                                .textColor(Color.parseColor("#fc3937"))
                                .position(ViewTooltip.Position.TOP)
                                .text("Girilen şifreler aynı değildir.")
                                .show()

                            edtOfPassword.requestFocus()
                        }
                    else {
                        ViewTooltip
                            .on(this, edtOfEposta)
                            .autoHide(true, 3000)
                            .corner(100)
                            .color(Color.parseColor("#FFFFFF"))
                            .textColor(Color.parseColor("#fc3937"))
                            .position(ViewTooltip.Position.TOP)
                            .text("Geçerli bir email adresi giriniz.")
                            .show()

                        edtOfEposta.requestFocus()
                    }
                else {
                    ViewTooltip
                        .on(this, edtOfPassword)
                        .autoHide(true, 3000)
                        .corner(100)
                        .color(Color.parseColor("#FFFFFF"))
                        .textColor(Color.parseColor("#fc3937"))
                        .position(ViewTooltip.Position.TOP)
                        .text("Şifre en az 6 karakterli olmalıdır")
                        .show()

                    edtOfPassword.requestFocus()
                }
        }
    }

    //==================================================================================================================
    /**
     * Click Facebook Register Button
     */
    //==================================================================================================================
    /*override fun clickRegisterButtonWithFB() {
        callbackManager = CallbackManager.Factory.create()

        viewP.btnOfFacebookRegister.setOnClickListener() {
            var listOfReadUserFacebookInfo: MutableList<String> = mutableListOf()
            listOfReadUserFacebookInfo.add("public_profile")
            listOfReadUserFacebookInfo.add("user_friends")

            LoginManager.getInstance()
                .logInWithReadPermissions(
                    this@RegisterFragmentAcitivityViewImpl,
                    Arrays.asList("public_profile", "email")
                )
        }

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult?) {
                registerFragmentAcitivityPresenterImpl.doRegisterWithFB(loginResult?.accessToken?.token.toString())
            }

            override fun onCancel() {
                Toast.makeText(activity?.applicationContext, "Facebook Kayıt işleminden vazgeçildi", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(
                    activity?.applicationContext,
                    "Facebook ile Kayıt sırasında bir hata oluştu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    } */

    override fun handleCheckMail(response4IsMailExist: Response4IsMailExist) {
        if (response4IsMailExist.data?.status.equals("true")) {
            ViewTooltip
                .on(this, edtOfEposta)
                .autoHide(true, 3000)
                .corner(100)
                .color(Color.parseColor("#FFFFFF"))
                .textColor(Color.parseColor("#fc3937"))
                .position(ViewTooltip.Position.TOP)
                .text("$ePosta maili zaten alılnış")
                .show()
            edtOfEposta.requestFocus()
        } else
            registerFragmentAcitivityPresenterImpl.isUserNameExist(userName)
    }

    override fun handleCheckUserName(response4IsMailExist: Response4IsMailExist) {
        if (response4IsMailExist.data?.status.equals("true")) {
            ViewTooltip
                .on(this, edtOfUsername)
                .autoHide(true, 3000)
                .corner(100)
                .color(Color.parseColor("#FFFFFF"))
                .textColor(Color.parseColor("#fc3937"))
                .position(ViewTooltip.Position.TOP)
                .text(userName + " kullanıcı adı zaten alınmış")
                .show()
            edtOfUsername.requestFocus()
        } else
            registerFragmentAcitivityPresenterImpl.doRegister(userName, ePosta, name, password)
    }

    //==================================================================================================================
    /**
     * Handle Register Data
     */
    //==================================================================================================================
    override fun handleSuccessRegisterResponse(response4Register: Response4Register) {
        showEzDialogMessage(
            "Bilgilendirme",
            "Kayıt Başarılı",
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
        /*EZDialog.Builder(activity)
            .setTitle("Bilgilendirme")
            .setMessage("Kayıt başarılı.")
            .setPositiveBtnText("Tamam")
            .setHeaderColor(resources.getColor(R.color.tabSelectedIndicator))
            .setTitleTextColor(resources.getColor(R.color.constWhite))
            .setMessageTextColor(resources.getColor(R.color.constTextColor))
            .setButtonTextColor(resources.getColor(R.color.constTextColor))
            .setCancelableOnTouchOutside(false)
            .OnPositiveClicked {
                startActivity(
                    Intent(
                        activity?.applicationContext,
                        LoginAndRegisterDashboardActivityViewImpl::class.java
                    )
                )
            }
            .build()*/


    }

    /*override fun handleSuccessRegisterResponseWithFB(response4Login: Response4Login) {
        Toast.makeText(activity?.applicationContext, "Facebook ile Kayıt Başarılı", Toast.LENGTH_SHORT).show()
        startActivity(Intent(activity?.applicationContext, LoginAndRegisterDashboardActivityViewImpl::class.java))
    }*/

    override fun showLoading() {
        viewP.avlIndicatorProgress.playAnimation()
        viewP.avlIndicatorProgress.visibility = View.VISIBLE
        /*mActivity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/

    }

    override fun hideLoading() {
        viewP.avlIndicatorProgress.cancelAnimation()
        viewP.avlIndicatorProgress.visibility = View.GONE
        //mActivity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==================================================================================================================
    /**
     * Check all input Component
     */
    //==================================================================================================================
    override fun checkWarningMessage(
        ePosta: String,
        userName: String,
        name: String,
        password: String,
        verifyPassword: String
    ): Boolean {

        if (checkAllEmailValidation()) {
            var listOfValidator: MutableList<EmptyCheckModel> = mutableListOf()
            listOfValidator.add(
                EmptyCheckModel(
                    userName,
                    true,
                    "Kullanıcı Adı",
                    edtOfUsername
                )
            )
            listOfValidator.add(
                EmptyCheckModel(
                    name,
                    true,
                    "İsim Soyisim",
                    edtOfName
                )
            )
            listOfValidator.add(
                EmptyCheckModel(
                    password,
                    true,
                    "Şifre",
                    edtOfPassword
                )
            )
            listOfValidator.add(
                EmptyCheckModel(
                    verifyPassword,
                    true,
                    "Şifre (tekrar)",
                    edtOfPasswordVerify
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
        } else
            return false
    }

    fun checkAllEmailValidation(): Boolean {
        if (!edtOfEposta.text.toString().trim().equals(""))
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

                hideSoftKeyboard(registerConstraintLayout)
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

    /*fun hideSoftKeyboard(activity: FragmentActivity) {
        val inputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager!!.hideSoftInputFromWindow(
            activity.currentFocus!!.windowToken, 0
        )
    }*/

    //==================================================================================================================
    /**
     * Handle After Facebook Register..
     */
    //==================================================================================================================
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun clickEmptyArea() {
        viewP.registerConstraintLayout.setOnClickListener() {
            hideSoftKeyboard(registerConstraintLayout)
        }
    }

    fun hideSoftKeyboard(view: View) {
        val inputMethodManager =
            mActivity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun edtOfTouchListener() {
        viewP.edtOfEposta.setOnTouchListener { _, _ ->
            mActivity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            false
        }

        viewP.edtOfUsername.setOnTouchListener { _, _ ->
            mActivity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            false
        }

        viewP.edtOfName.setOnTouchListener { _, _ ->
            mActivity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            false
        }
        viewP.edtOfPassword.setOnTouchListener { _, _ ->
            mActivity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            false
        }
        viewP.edtOfPasswordVerify.setOnTouchListener { _, _ ->
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

        OnedioCommon.showEzDialog4Fragment(ezDialogMessage) { goLoginAndRegisterActivity() }
    }

    override fun goLoginAndRegisterActivity() {
        startActivity(
            Intent(
                mActivity,
                LoginAndRegisterDashboardActivityViewImpl::class.java
            )
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity) {
            mActivity = context
        }
    }
}