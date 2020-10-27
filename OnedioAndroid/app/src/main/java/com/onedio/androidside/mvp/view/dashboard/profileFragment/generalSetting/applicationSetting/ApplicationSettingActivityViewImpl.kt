package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.applicationSetting

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_profile_fragment_application_setting.*
import kotlinx.android.synthetic.main.activity_profile_fragment_application_setting.goBack
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.mvp.model.dashboard.model.ApplicationSettingModel
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.GeneralSettingActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.applicationSetting.listAdaptert.ApplicationSettingListAdapter
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.applicationSetting.listAdaptert.CustomSpinnerAdapter
import com.onedio.androidside.singleton.OnedioSingletonPattern

class ApplicationSettingActivityViewImpl : AppCompatActivity(),
    IApplicationSettingActivityView {

    private lateinit var spinnerAdapter: CustomSpinnerAdapter

    /// After Change
    var mListOfVersion = arrayOf("Türkçe", "İngilizce").toMutableList()
    private lateinit var listOfApplicationSetting: MutableList<ApplicationSettingModel>

    //==================================================================================================================
    /**
     * onCreate Override Method...
     */
    //==================================================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setThemeMode()

        setContentView(R.layout.activity_profile_fragment_application_setting)

        initApplicatioSettingComponent()
        changeComponentTypeface()
        changeIconIfDarkModeOn()
        goBack()

        prepareApplicationSettinData()
        clickApplicationSettingListView()

    }

    //==================================================================================================================
    /**
     * Init Application Setting Component...
     */
    //==================================================================================================================
    override fun initApplicatioSettingComponent() {
        supportActionBar?.hide()

        spinnerAdapter =
            CustomSpinnerAdapter(
                applicationContext,
                mListOfVersion
            )
        versiyonSpinner!!.adapter = spinnerAdapter
    }

    //==================================================================================================================
    /**
     * Change icon if dark mode on...
     */
    //==================================================================================================================
    override fun changeIconIfDarkModeOn() {
        if (OnedioSharedPrefs(applicationContext).isNightModeEnabled()) {
            goBack.setImageResource(R.drawable.ic_back_dark_mode)
        } else {
            goBack.setImageResource(R.drawable.ic_back)
        }
    }

    //==================================================================================================================
    /**
     * change component typeface...
     */
    //==================================================================================================================
    override fun changeComponentTypeface() {
        applicationToolbarTitle.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        appVersionTextView.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
    }

    //==================================================================================================================
    /**
     * Set theme Mode...
     */
    //==================================================================================================================
    override fun setThemeMode() {
        if (OnedioSharedPrefs(applicationContext).isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    //==================================================================================================================
    /**
     * Prepare Application Setting Data...
     */
    //==================================================================================================================
    override fun prepareApplicationSettinData() {
        listOfApplicationSetting = mutableListOf()
        if (OnedioSharedPrefs(applicationContext).getApplicationSetting() != null && OnedioSharedPrefs(
                applicationContext
            ).getApplicationSetting()!!.size == 0
        ) {
            //listOfApplicationSetting.add(ApplicationSettingModel(0, "Versiyon", "", false, "Türkçe"))
            listOfApplicationSetting.add(
                ApplicationSettingModel(
                    1,
                    "Gece Modu",
                    "",
                    true,
                    "false"
                )
            )
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
            listOfApplicationSetting = listOfApplicationSetting.sortedWith(compareBy { it.index }).toMutableList()
        }

        lvApplicationSetting.adapter =
            ApplicationSettingListAdapter(
                applicationContext,
                mListOfVersion,
                listOfApplicationSetting
            )
    }

    //==================================================================================================================
    /**
     * Click Application Setting ListView...
     */
    //==================================================================================================================
    override fun clickApplicationSettingListView() {
        lvApplicationSetting.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

        }
    }

    //==================================================================================================================
    /**
     * Go Back...
     */
    //==================================================================================================================
    fun goBack() {
        goBack.setOnClickListener() {
            /*OnedioSingletonPattern.instance.setTabIndex(4)*/
            startActivity(Intent(this@ApplicationSettingActivityViewImpl, GeneralSettingActivityViewImpl::class.java))
        }
    }

    //==================================================================================================================
    /**
     * Handle Keyboard Back Button...
     */
    //==================================================================================================================
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                /*OnedioSingletonPattern.instance.setTabIndex(4)*/
                startActivity(
                    Intent(
                        this@ApplicationSettingActivityViewImpl,
                        GeneralSettingActivityViewImpl::class.java
                    )
                )
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}