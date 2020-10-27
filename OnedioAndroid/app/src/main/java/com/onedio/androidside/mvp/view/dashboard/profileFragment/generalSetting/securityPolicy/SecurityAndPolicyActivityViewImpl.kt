package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.model.SecurityPrivacyModel
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy.changePassword.ChangePasswordActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy.listAdapter.SecurityPrivacyListAdapter
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy.userBlocked.UserBlockListActivityViewImpl
import com.onedio.androidside.mvp.view.loginAndRegister.LoginAndRegisterDashboardActivityViewImpl
import com.onedio.androidside.prefs.StringSharedPrefs
import com.onedio.androidside.singleton.OnedioSingletonPattern
import kotlinx.android.synthetic.main.activity_profile_fragment_general_setting_security_policy.*

class SecurityAndPolicyActivityViewImpl : AppCompatActivity(),
    ISecurityAndPolicyActivityView {

    private lateinit var listOfSecuritySetting: MutableList<SecurityPrivacyModel>

    private lateinit var toolBar: Toolbar

    private var theme: String = ""

    private lateinit var sharedPref: SharedPreferences

    //==================================================================================================================
    /**
     * onCreate Override Method...
     */
    //==================================================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_fragment_general_setting_security_policy)

        sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        initComponent()

        changeIconIfDarkModeOn()

        changeComponentTypeface()

        prepareSecuritySettingData()

        clickListViewItem()

    }

    //==================================================================================================================
    /**
     * Init Component...
     */
    //==================================================================================================================
    override fun initComponent() {
        //supportActionBar?.hide()

        toolBar = toolBar4SecurityAndPolicy as Toolbar

        toolBar.title = "Güvenlik ve Gizlilik Ayarları"
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        toolBar.setOnClickListener {}

        if (theme == "dark") {
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)
        } else {
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)
        }

    }


    //==================================================================================================================
    /**
     * Prepare Security Setting Data...
     */
    //==================================================================================================================
    override fun prepareSecuritySettingData() {
        listOfSecuritySetting = mutableListOf()

        if (OnedioSharedPrefs(applicationContext).getSecuritySetting() != null && OnedioSharedPrefs(
                applicationContext
            ).getSecuritySetting()!!.size == 0
        ) {
            if (!OnedioSingletonPattern.instance.isFBLogin())
                listOfSecuritySetting.add(
                    SecurityPrivacyModel(
                        0,
                        "Şifre Değiştir",
                        "",
                        false,
                        "false"
                    )
                )
            /*listOfSecuritySetting.add(SecurityPrivacyModel(1, "Engellenen Hesaplar", "", false, "false"))
            listOfSecuritySetting.add(SecurityPrivacyModel(2, "İki Aşamalı Giriş", "", false, "false"))
            listOfSecuritySetting.add(SecurityPrivacyModel(3, "Üyelik Silme", "", false, "false"))
            listOfSecuritySetting.add(
                SecurityPrivacyModel(
                    4,
                    "Aranabilme",
                    "Profiliniz arama motorları tarafından taranabiliyor." +
                            "Eğer bu özelliği kapatırsanız profiliniz arama motorları tarafından taranamayacak.",
                    true,
                    "false"
                )
            )*/
        } else {
            listOfSecuritySetting = OnedioSharedPrefs(
                applicationContext
            ).getSecuritySetting()!!
            listOfSecuritySetting =
                listOfSecuritySetting.sortedWith(compareBy { it.index }).toMutableList()
        }

        securityPrivacySettingListView.adapter =
            SecurityPrivacyListAdapter(
                applicationContext,
                listOfSecuritySetting
            )

    }

    //==================================================================================================================
    /**
     * Change icon if dark mode on...
     */
    //==================================================================================================================
    override fun changeIconIfDarkModeOn() {
        if (OnedioSharedPrefs(applicationContext).isNightModeEnabled()) {
            //goBack.setImageResource(R.drawable.ic_back_dark_mode)
        } else {
            //goBack.setImageResource(R.drawable.ic_back)
        }
    }

    //==================================================================================================================
    /**
     * Change component Typeface...
     */
    //==================================================================================================================
    override fun changeComponentTypeface() {
        //toolbarTitleSecurity.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
    }

    //==================================================================================================================
    /**
     * Click ListView Item...
     */
    //==================================================================================================================
    override fun clickListViewItem() {
        securityPrivacySettingListView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                val selectedSecuritySetting =
                    parent.getItemAtPosition(position) as SecurityPrivacyModel

                when (position) {
                    0 -> {
                        /*if (!OnedioSingletonPattern.instance.isFBLogin())
                            startActivity(Intent(applicationContext, ChangePasswordActivityViewImpl::class.java))
                        else
                            startActivity(Intent(applicationContext, UserBlockListActivityViewImpl::class.java))*/
                        val accessToken = StringSharedPrefs(
                            sharedPref,
                            OnedioConstant.SHARED_PREF_ACCESS_TOKEN,
                            ""
                        ).getValue<String>()

                        if (accessToken != "") {
                            if (!OnedioSingletonPattern.instance.isFBLogin())
                                startActivity(
                                    Intent(
                                        applicationContext,
                                        ChangePasswordActivityViewImpl::class.java
                                    )
                                )
                            else
                                startActivity(
                                    Intent(
                                        applicationContext,
                                        UserBlockListActivityViewImpl::class.java
                                    )
                                )
                        } else {
                            startActivity(
                                Intent(
                                    applicationContext,
                                    LoginAndRegisterDashboardActivityViewImpl::class.java
                                )
                            )
                        }

                    }
                    1 -> {

                        val accessToken = StringSharedPrefs(
                            sharedPref,
                            OnedioConstant.SHARED_PREF_ACCESS_TOKEN,
                            ""
                        ).getValue<String>()

                        if(accessToken != ""){
                            if (!OnedioSingletonPattern.instance.isFBLogin())
                                startActivity(
                                    Intent(
                                        applicationContext,
                                        UserBlockListActivityViewImpl::class.java
                                    )
                                )
                            else
                                Toast.makeText(
                                    applicationContext,
                                    listOfSecuritySetting[position].title,
                                    Toast.LENGTH_SHORT
                                ).show()
                        }else{
                            startActivity(
                                Intent(
                                    applicationContext,
                                    LoginAndRegisterDashboardActivityViewImpl::class.java
                                )
                            )
                        }

                        /*if (!OnedioSingletonPattern.instance.isFBLogin())
                            startActivity(
                                Intent(
                                    applicationContext,
                                    UserBlockListActivityViewImpl::class.java
                                )
                            )
                        else
                            Toast.makeText(
                                applicationContext,
                                listOfSecuritySetting[position].title,
                                Toast.LENGTH_SHORT
                            ).show()*/
                    }
                    else -> Toast.makeText(
                        applicationContext,
                        listOfSecuritySetting[position].title,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
