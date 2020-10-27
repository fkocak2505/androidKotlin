package com.onedio.androidside.mvp.view.loginAndRegister

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_login_and_register_dashboard.*
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.loginAndRegister.LoginAndRegisterDashboardActivityModelImpl
import com.onedio.androidside.mvp.model.loginAndRegister.responseModel.Response4Token
import com.onedio.androidside.mvp.presenter.loginAndRegister.LoginAndRegisterDashboardActivityPresenterImpl
import com.onedio.androidside.mvp.view.loginAndRegister.viewPagerAdapter.LoginAndRegisterActivityViewPagerAdapter
import com.onedio.androidside.prefs.StringSharedPrefs
import com.onedio.androidside.singleton.OnedioSingletonPattern

class LoginAndRegisterDashboardActivityViewImpl : AppCompatActivity(),
    ILoginAndRegisterDashboardActivityView {

    private lateinit var toolBar: Toolbar

    private lateinit var loginAndRegisterActivityViewPagerAdapter: LoginAndRegisterActivityViewPagerAdapter

    private lateinit var loginAndRegisterDashboardActivityPresenterImpl: LoginAndRegisterDashboardActivityPresenterImpl

    private lateinit var sharedPreferences: SharedPreferences

    private var theme: String = ""

    //==================================================================================================================
    /**
     * LoginAndRegisterDashboardActivity onCreate
     */
    //==================================================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login_and_register_dashboard)

        val sharedPref = getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        initLoginAndRegisterDashboardAcitivityComponents()

        getToken()

    }

    //==================================================================================================================
    /**
     * Init Login And Register Dashboard Activity Component
     */
    //==================================================================================================================
    override fun initLoginAndRegisterDashboardAcitivityComponents() {
        //supportActionBar?.hide()

        prepareToolbar()

        sharedPreferences = getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)!!

        /*if (OnedioSharedPrefs(applicationContext).isNightModeEnabled()) {
            onedioLogo.setImageResource(R.drawable.ic_onedio_logo_dark_mode)
            goBack.setImageResource(R.drawable.ic_back_dark_mode)
        } else {
            onedioLogo.setImageResource(R.drawable.ic_onedio_logo2)
            goBack.setImageResource(R.drawable.ic_back)
        }*/


        /// Tabs & ViewPager Prop...
        loginAndRegisterActivityViewPagerAdapter =
            LoginAndRegisterActivityViewPagerAdapter(
                supportFragmentManager
            )
        mainViewPager.adapter = loginAndRegisterActivityViewPagerAdapter
        tabs.setupWithViewPager(mainViewPager)
        tabs.tabRippleColor = null

    }

    private fun prepareToolbar(){
        toolBar = toolBar4LoginAndRegister as Toolbar

        toolBar.title = "Giriş Bilgileri"
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        toolBar.setOnClickListener{

        }

        if(theme == "dark"){
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)
        }else{
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)
        }

    }

    //==================================================================================================================
    /**
     * Trigger Presenter for Getting Token
     */
    //==================================================================================================================
    override fun getToken() {
        loginAndRegisterDashboardActivityPresenterImpl =
            LoginAndRegisterDashboardActivityPresenterImpl(
                LoginAndRegisterDashboardActivityModelImpl(),
                this
            )

        loginAndRegisterDashboardActivityPresenterImpl.getToken()
    }

    //==================================================================================================================
    /**
     * Handle Token Data
     */
    //==================================================================================================================
    override fun handleTokenResponse(response4Token: Response4Token) {
        //OnedioSingletonPattern.instance.setToken(response4Token.token)

        StringSharedPrefs(
            sharedPreferences,
            OnedioConstant.SHARED_PREF_TOKEN,
            ""
        ).setValue(response4Token.token)

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed()
            false
        } else super.onKeyDown(keyCode, event)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
