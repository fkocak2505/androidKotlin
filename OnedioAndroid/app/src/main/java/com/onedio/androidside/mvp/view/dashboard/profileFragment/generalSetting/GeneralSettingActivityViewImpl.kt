package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.model.ApplicationSettingModel
import com.onedio.androidside.mvp.model.dashboard.model.GeneralSettingModel
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.feedbackSetting.FeedBackSettingActivity
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.listAdapter.GeneralSettingListAdapter
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.profileSetting.UserProfileInfoDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy.SecurityAndPolicyActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.userAgreement.UserAgreementActivityViewImpl
import com.onedio.androidside.mvp.view.loginAndRegister.LoginAndRegisterDashboardActivityViewImpl
import com.onedio.androidside.prefs.StringSharedPrefs
import kotlinx.android.synthetic.main.activity_profile_fragment_general_setting.*

class GeneralSettingActivityViewImpl : AppCompatActivity(),
    IGeneralSettingActivityView {

    private lateinit var toolBar: Toolbar

    private lateinit var listOfApplicationSetting: MutableList<ApplicationSettingModel>

    private lateinit var sharedPreferences: SharedPreferences

    private var theme: String = ""

    //==================================================================================================================
    /**
     * onCreate Override Method...
     */
    //==================================================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile_fragment_general_setting)

        val sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        prepareToolbar()

        initGeneraSettingActivityComponent()

        changeComponentTypeFace()
        //goBack()

        clickGeneralListViewItem()

    }

    private fun prepareToolbar() {
        toolBar = toolBar4GeneralSetting as Toolbar

        toolBar.title = "Genel Ayarlar"
        toolBar.setTitleTextAppearance(this, R.style.MuliSemiboldTextAppearance)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        if (theme == "dark") {
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)
        } else {
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)
        }

        toolBar.setOnClickListener {

        }

    }

    //==================================================================================================================
    /**
     * Init General Setting Activity Componetn...
     */
    //==================================================================================================================
    override fun initGeneraSettingActivityComponent() {
        //supportActionBar?.hide()

        sharedPreferences =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)!!

        /*toolbarTitleGeneralSetting.typeface =
            OnedioCommon.changeTypeFace4ActivityBold(applicationContext)*/

        checkApplicationSettingListData()

        setGeneralSettingData()

    }

    override fun setGeneralSettingData() {
        var listOfGeneralSetting: MutableList<GeneralSettingModel> = mutableListOf()


        listOfGeneralSetting.add(
            GeneralSettingModel(
                R.drawable.ic_profile_setting,
                "Profil Ayarları",
                false
            )
        )
        listOfGeneralSetting.add(
            GeneralSettingModel(
                R.drawable.ic_security_privacy,
                "Güvenlik ve Gizlilik Ayarları",
                false
            )
        )
        listOfGeneralSetting.add(
            GeneralSettingModel(
                R.drawable.ic_app_setting,
                "Uygulama Ayarları",
                false
            )
        )
        listOfGeneralSetting.add(
            GeneralSettingModel(
                R.drawable.ic_notification_setting,
                "Bildirim Ayarları",
                false
            )
        )
        listOfGeneralSetting.add(
            GeneralSettingModel(
                R.drawable.ic_user_contract,
                "Kullanıcı Sözleşmeleri ve Yayın İlkeleri",
                false
            )
        )

        listOfGeneralSetting.add(
            GeneralSettingModel(
                R.drawable.ic_dark_mode_setting,
                "Gece Modu",
                true
            )
        )

        listOfGeneralSetting.add(
            GeneralSettingModel(
                R.drawable.ic_feedback,
                "Geri Bildirim",
                false
            )
        )

        listOfGeneralSetting.add(
            GeneralSettingModel(
                R.drawable.ic_exit_app,
                "Çıkış Yap",
                false
            )
        )

        generalSettingListView.adapter =
            GeneralSettingListAdapter(
                applicationContext,
                listOfGeneralSetting,
                listOfApplicationSetting

            ) { generalListViewModelItem, position ->
                when (generalListViewModelItem.title) {
                    "Profil Ayarları" -> {
                        if (OnedioCommon.isUserLogin()) {
                            startActivity(
                                Intent(
                                    applicationContext,
                                    UserProfileInfoDetailActivityViewImpl::class.java
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
                    "Güvenlik ve Gizlilik Ayarları" -> {
                        if (OnedioCommon.isUserLogin())
                            startActivity(
                                Intent(
                                    applicationContext,
                                    SecurityAndPolicyActivityViewImpl::class.java
                                )
                            )
                    }
                    "Uygulama Ayarları" -> {

                    }
                    "Bildirim Ayarları" -> {

                    }
                    "Kullanıcı Sözleşmeleri ve Yayın İlkeleri" -> {
                        startActivity(
                            Intent(
                                applicationContext,
                                UserAgreementActivityViewImpl::class.java
                            )
                        )
                    }
                    "Gece Modu" -> {

                    }
                    "Geri Bildirim" -> {
                        startActivity(
                            Intent(
                                applicationContext,
                                FeedBackSettingActivity::class.java
                            )
                        )
                    }
                    "Çıkış Yap" -> {
                        StringSharedPrefs(
                            sharedPreferences,
                            OnedioConstant.SHARED_PREF_ACCESS_TOKEN,
                            ""
                        ).setValue(" ")

                        //OnedioSingletonPattern.instance.setAccessToken(" ")

                        StringSharedPrefs(
                            sharedPreferences,
                            OnedioConstant.SHARED_PREF_TOKEN,
                            ""
                        ).setValue(" ")

                        //OnedioSingletonPattern.instance.setToken(" ")

                        startActivity(
                            Intent(
                                applicationContext,
                                DashboardActivityViewImpl::class.java
                            )
                        )
                    }
                }
            }
    }

    override fun checkApplicationSettingListData() {
        listOfApplicationSetting = mutableListOf()
        if (OnedioSharedPrefs(applicationContext).getApplicationSetting() != null && OnedioSharedPrefs(
                applicationContext
            ).getApplicationSetting()!!.size == 0
        ) {
            //listOfApplicationSetting.add(ApplicationSettingModel(0, "Versiyon", "", false, "Türkçe"))
            //wlistOfApplicationSetting.add(ApplicationSettingModel(1, "Gece Modu", "", true, "false"))
            listOfApplicationSetting.add(
                ApplicationSettingModel(
                    2,
                    "Otomatik video oynatmak",
                    "",
                    true,
                    "false"
                )
            )
            listOfApplicationSetting.add(
                ApplicationSettingModel(
                    3,
                    "İçerik Okuduysam Gizle",
                    "",
                    true,
                    "false"
                )
            )
            listOfApplicationSetting.add(
                ApplicationSettingModel(
                    4,
                    "Veri Kullanımını Kısıtla",
                    "Videoları ve gifleri yüklemez ama imajları yükler.",
                    true,
                    "false"
                )
            )
        } else {
            listOfApplicationSetting = OnedioSharedPrefs(
                applicationContext
            ).getApplicationSetting()!!
            listOfApplicationSetting =
                listOfApplicationSetting.sortedWith(compareBy { it.index }).toMutableList()
        }
    }

    //==================================================================================================================
    /**
     * change component typeface...
     */
    //==================================================================================================================
    override fun changeComponentTypeFace() {
        /*toolbarTitleGeneralSetting.typeface =
            OnedioCommon.changeTypeFace4Activity(applicationContext)*/
    }

    //==================================================================================================================
    /**
     * Click General Setting ListView Item...
     */
    //==================================================================================================================
    override fun clickGeneralListViewItem() {
        /*generalSettingListView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                when (position) {
                    0 -> startActivity(
                        Intent(
                            applicationContext,
                            UserProfileInfoDetailActivityViewImpl::class.java
                        )
                    )
                    1 -> startActivity(
                        Intent(
                            applicationContext,
                            SecurityAndPolicyActivityViewImpl::class.java
                        )
                    )
                    *//*2 -> startActivity(
                        Intent(
                            applicationContext,
                            ApplicationSettingActivityViewImpl::class.java
                        )
                    )
                    3 -> startActivity(
                        Intent(
                            applicationContext,
                            NotificationSettingActivityViewImpl::class.java
                        )
                    )*//*
                    4 -> startActivity(
                        Intent(
                            applicationContext,
                            UserAgreementActivityViewImpl::class.java
                        )
                    )
                    6 -> {
                        StringSharedPrefs(
                            sharedPreferences,
                            OnedioConstant.SHARED_PREF_ACCESS_TOKEN,
                            ""
                        ).setValue(" ")

                        //OnedioSingletonPattern.instance.setAccessToken(" ")

                        StringSharedPrefs(
                            sharedPreferences,
                            OnedioConstant.SHARED_PREF_TOKEN,
                            ""
                        ).setValue(" ")

                        //OnedioSingletonPattern.instance.setToken(" ")

                        startActivity(
                            Intent(
                                applicationContext,
                                DashboardActivityViewImpl::class.java
                            )
                        )
                    }
                }

            }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /*//==================================================================================================================
    */
    /**
     * Go Back...
     *//*
    //==================================================================================================================
    fun goBack() {
        goBack.setOnClickListener() {
            *//*OnedioSingletonPattern.instance.setTabIndex(OnedioSingletonPattern.instance.getTabIndex())*//*
            startActivity(
                Intent(
                    this@GeneralSettingActivityViewImpl,
                    ProfileActivityViewImpl::class.java
                )
            )
        }
    }

    //==================================================================================================================
    */
    /**
     * Handle Keyboard Back Button...
     *//*
    //==================================================================================================================
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                *//*OnedioSingletonPattern.instance.setTabIndex(OnedioSingletonPattern.instance.getTabIndex())*//*
                startActivity(
                    Intent(
                        this@GeneralSettingActivityViewImpl,
                        ProfileActivityViewImpl::class.java
                    )
                )
            }
        }
        return super.onKeyDown(keyCode, event)
    }*/
}