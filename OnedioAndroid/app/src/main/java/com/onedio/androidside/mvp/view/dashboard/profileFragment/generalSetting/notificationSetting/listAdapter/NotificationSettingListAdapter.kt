package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.notificationSetting.listAdapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.mvp.model.dashboard.model.NotificationSettingModel


class NotificationSettingListAdapter(
    val context: Context,
    val notificationSettingList: MutableList<NotificationSettingModel>
) :
    BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val layouInflator = LayoutInflater.from(context)
        val inflater = layouInflator
        val rowView = inflater.inflate(R.layout.notification_setting_list_view_item, null, true)

        val titleText = rowView.findViewById(R.id.notificationListTitle) as TextView
        val notificationSubTitle = rowView.findViewById(R.id.notificationSubTitle) as TextView
        val notificationSettingSwitch = rowView.findViewById(R.id.notificationSettingSwitch) as Switch
        val rightIcon = rowView.findViewById(R.id.rightIcon) as ImageView

        titleText.typeface = OnedioCommon.changeTypeFace4Activity(context)
        notificationSubTitle.typeface = OnedioCommon.changeTypeFace4Activity(context)

        titleText.text = notificationSettingList[position].title
        notificationSettingSwitch.isChecked = notificationSettingList[position].valOfNotifSettingItem.toBoolean()

        isSwitchesVisible(position, notificationSettingSwitch, rightIcon)

        itemHasSubtitle(position, titleText, notificationSubTitle)

        changeTextColorDependDarkModeOn(titleText, notificationSubTitle)

        switchCheckedChangeListener(position, notificationSettingSwitch)


        return rowView
    }

    fun isSwitchesVisible(pos: Int, cSwitch: View, cImageView: View) {
        if (notificationSettingList[pos].isSwitchVisible) {
            cSwitch.visibility = View.VISIBLE
            cImageView.visibility = View.INVISIBLE
        } else {
            cSwitch.visibility = View.INVISIBLE
            cImageView.visibility = View.VISIBLE
        }
    }

    fun itemHasSubtitle(pos: Int, cTitleTextView: TextView, cSubtitleTextView: TextView) {
        if (!notificationSettingList[pos].subTitle.equals("")) {
            cSubtitleTextView.visibility = View.VISIBLE
            cSubtitleTextView.text = notificationSettingList[pos].subTitle
        } else {
            val scale = context.resources.getDisplayMetrics().density
            val dpAsPixels = (20 * scale + 0.5f).toInt()
            cTitleTextView.setPadding(0, 0, 0, dpAsPixels)
        }
    }

    fun changeTextColorDependDarkModeOn(cTitleTextView: TextView, cSubtitleTextView: TextView) {
        if (AppCompatDelegate.getDefaultNightMode() === AppCompatDelegate.MODE_NIGHT_YES) {
            cTitleTextView.setTextColor(Color.parseColor("#e6e6e6"))
            cSubtitleTextView.setTextColor(Color.parseColor("#e6e6e6"))
        } else {
            cTitleTextView.setTextColor(Color.parseColor("#191919"))
            cSubtitleTextView.setTextColor(Color.parseColor("#191919"))
        }
    }

    fun switchCheckedChangeListener(pos: Int, cSwitch: Switch) {
        cSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                notificationSettingList[pos].valOfNotifSettingItem = "true"
                OnedioSharedPrefs(context).saveNotificationSetting(notificationSettingList)
            } else {
                notificationSettingList[pos].valOfNotifSettingItem = "false"
                OnedioSharedPrefs(context).saveNotificationSetting(notificationSettingList)
            }
        }
    }

    override fun getItem(p0: Int): Any {
        return notificationSettingList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return notificationSettingList.size
    }

}