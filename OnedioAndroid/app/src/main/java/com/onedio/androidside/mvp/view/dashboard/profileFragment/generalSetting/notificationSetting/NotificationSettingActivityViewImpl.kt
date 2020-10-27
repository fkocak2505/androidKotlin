package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.notificationSetting

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_profile_fragment_application_setting.goBack
import kotlinx.android.synthetic.main.activity_profile_notification_setting.*
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.mvp.model.dashboard.model.NotificationSettingModel
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.GeneralSettingActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.notificationSetting.listAdapter.NotificationSettingListAdapter
import com.onedio.androidside.singleton.OnedioSingletonPattern

class NotificationSettingActivityViewImpl : AppCompatActivity(),
    INotificationSettingActivityView {

    private lateinit var listOfNotificationSetting: MutableList<NotificationSettingModel>

    //==================================================================================================================
    /**
     * onCreate Override Method...
     */
    //==================================================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setThemeMode()

        setContentView(R.layout.activity_profile_notification_setting)

        initNotificationSettingComponent()
        changeComponentTypeface()
        changeIconIfDarkModeOn()
        goBack()

        prepareANotificationSettingData()
        clickNotificationSettingListView()
    }

    //==================================================================================================================
    /**
     * Set Theme Mode...
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
     * Init Notification Setting Component...
     */
    //==================================================================================================================
    override fun initNotificationSettingComponent() {
        supportActionBar?.hide()
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
     * Change Component Typeface...
     */
    //==================================================================================================================
    override fun changeComponentTypeface() {
        notificationToolbarTitle.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
    }

    //==================================================================================================================
    /**
     * Prepare Notification Setting Data...
     */
    //==================================================================================================================
    override fun prepareANotificationSettingData() {
        listOfNotificationSetting = mutableListOf()

        if (OnedioSharedPrefs(applicationContext).getNotificationSetting() != null && OnedioSharedPrefs(
                applicationContext
            ).getNotificationSetting()!!.size == 0
        ) {
            listOfNotificationSetting.add(
                NotificationSettingModel(
                    0,
                    "Takip Eden Üyeler",
                    "",
                    true,
                    "false"
                )
            )
            listOfNotificationSetting.add(
                NotificationSettingModel(
                    1,
                    "Beğenilen Yorumlar",
                    "",
                    true,
                    "false"
                )
            )
            listOfNotificationSetting.add(
                NotificationSettingModel(
                    2,
                    "Cevaplanan Yorumlarım",
                    "",
                    true,
                    "false"
                )
            )
            listOfNotificationSetting.add(
                NotificationSettingModel(
                    3,
                    "Gelen Mesajlarım",
                    "",
                    true,
                    "false"
                )
            )
            listOfNotificationSetting.add(
                NotificationSettingModel(
                    4,
                    "Takip Ettiğim Üyelerin Aktiviteleri",
                    "",
                    true,
                    "false"
                )
            )
            listOfNotificationSetting.add(
                NotificationSettingModel(
                    5,
                    "Son Dakika Haberleri",
                    "",
                    true,
                    "false"
                )
            )
            listOfNotificationSetting.add(
                NotificationSettingModel(
                    6,
                    "Kategori Ayarları",
                    "Push bildirim almak istediğiniz kategorileri seçebilirsiniz. ",
                    false,
                    "false"
                )
            )
        } else {
            listOfNotificationSetting = OnedioSharedPrefs(
                applicationContext
            ).getNotificationSetting()!!
            listOfNotificationSetting = listOfNotificationSetting.sortedWith(compareBy { it.index }).toMutableList()
        }

        lvNotificationSetting.adapter =
            NotificationSettingListAdapter(
                applicationContext,
                listOfNotificationSetting
            )

    }

    //==================================================================================================================
    /**
     * Click Notification Setting ListView...
     */
    //==================================================================================================================
    override fun clickNotificationSettingListView() {
        lvNotificationSetting.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

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
            startActivity(Intent(this@NotificationSettingActivityViewImpl, GeneralSettingActivityViewImpl::class.java))
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
                        this@NotificationSettingActivityViewImpl,
                        GeneralSettingActivityViewImpl::class.java
                    )
                )
            }
        }
        return super.onKeyDown(keyCode, event)
    }

}