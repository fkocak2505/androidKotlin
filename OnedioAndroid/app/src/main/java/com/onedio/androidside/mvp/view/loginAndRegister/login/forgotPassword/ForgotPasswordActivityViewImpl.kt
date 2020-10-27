package com.onedio.androidside.mvp.view.loginAndRegister.login.forgotPassword

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.github.florent37.viewtooltip.ViewTooltip
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Activity
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.loginAndRegister.login.forgotPassword.ForgotPasswordActivityModelImpl
import com.onedio.androidside.mvp.model.loginAndRegister.login.forgotPassword.responseModel.Response4ForgotPassword
import com.onedio.androidside.mvp.presenter.loginAndRegister.login.forgotPassword.ForgotPasswordActivityPresenterImpl
import kotlinx.android.synthetic.main.activity_login_forgot_password.*

class ForgotPasswordActivityViewImpl : AppCompatActivity(),
    IForgotPasswordActivityView {

    private lateinit var forgotPasswordActivityPresenterImpl: ForgotPasswordActivityPresenterImpl

    private var theme: String = ""

    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login_forgot_password)

        val sharedPref = applicationContext?.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref?.getString("mode", "default")!!

        prepareToolbar()

        initForgotPasswordActivityComponents()

        //changeForgotPasswordTextFont()

        clickSendEmailButton()

        clickEmptyArea()

    }

    private fun prepareToolbar(){
        toolBar = toolBar4ForgetPassword as Toolbar

        toolBar.title = "Şifremi Unuttum"
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        toolBar.setOnClickListener{}

        if(theme == "dark"){
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)
            edtOfEposta.background = ContextCompat.getDrawable(applicationContext, R.drawable.custom_edittext_dark_mode)
        }else{
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)
            edtOfEposta.background = ContextCompat.getDrawable(applicationContext, R.drawable.custom_edittext)
        }
    }

    override fun initForgotPasswordActivityComponents() {

        edtOfEposta.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)

        edtOfEposta.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                return false
            }
        })

        forgotPasswordActivityPresenterImpl =
            ForgotPasswordActivityPresenterImpl(
                ForgotPasswordActivityModelImpl(),
                this
            )
    }

    override fun clickSendEmailButton() {

        sendEmailForgotPassword.setOnClickListener() {
            if (OnedioCommon.isEmailValid(edtOfEposta.text.toString().trim())) {
                hideSoftKeyboard(forgettConstraintLayout)
                forgotPasswordActivityPresenterImpl.sendEmailForForgotPassword(edtOfEposta.text.toString().trim())
            } else {
                showMessage("Hata", "Geçerli bir email adresi giriniz.", "Tamam", false)
                hideSoftKeyboard(forgettConstraintLayout)
            }

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
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun showError(response4ErrorObj: Response4ErrorObj) {
        showEzDialogMessage(
            "Uyarı..!",
            response4ErrorObj.status?.message!!,
            "Tamam",
            ContextCompat.getColor(
                applicationContext!!,
                R.color.tabIndicatorColor4Proile
            ),
            ContextCompat.getColor(applicationContext!!, R.color.constWhite),
            ContextCompat.getColor(applicationContext!!, R.color.constTextColor),
            ContextCompat.getColor(applicationContext!!, R.color.constTextColor)
        )
    }

    private fun showEzDialogMessage(
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

    override fun handleResponseData(response4ForgotPassword: Response4ForgotPassword) {
        if (response4ForgotPassword.status?.code == 200)
            showMessage(
                "Başarılı",
                edtOfEposta.text.toString() + " adresine mail yollanmıştır.",
                "Tamam",
                true
            )
        else
            showMessage(
                "Başarısız",
                edtOfEposta.text.toString() + " adresine mail yollanırken bir hata oluştu.",
                "Tamam",
                true
            )
    }

    fun showMessage(
        title: String,
        message: String,
        buttonText: String,
        isGoingAnotherActivity: Boolean
    ) {
        /*EZDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveBtnText(buttonText)
            .setHeaderColor(resources.getColor(R.color.tabSelectedIndicator))
            .setTitleTextColor(resources.getColor(R.color.whiteColor))
            .setCancelableOnTouchOutside(false)
            .OnPositiveClicked {
                if (isGoingAnotherActivity)
                    startActivity(
                        Intent(
                            this@ForgotPasswordActivityViewImpl,
                            LoginAndRegisterDashboardActivityViewImpl::class.java
                        )
                    )
            }
            .build()*/
        ViewTooltip
            .on(this, edtOfEposta)
            .autoHide(true, 3000)
            .corner(100)
            .color(Color.parseColor("#FFFFFF"))
            .textColor(Color.parseColor("#fc3937"))
            .position(ViewTooltip.Position.TOP)
            .text(message)
            .show()

        edtOfEposta.requestFocus()
    }

    override fun clickEmptyArea() {
        forgettConstraintLayout.setOnClickListener() {
            hideSoftKeyboard(forgettConstraintLayout)
        }
    }

    private fun hideSoftKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}