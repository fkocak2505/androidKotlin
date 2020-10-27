package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy.listAdapter

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
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.model.SecurityPrivacyModel

class SecurityPrivacyListAdapter(val context: Context, val securityPrivacyList: MutableList<SecurityPrivacyModel>) :
    BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val layouInflator = LayoutInflater.from(context)
        val inflater = layouInflator
        val rowView = inflater.inflate(R.layout.general_setting_security_privacy_list_view_item, null, true)

        val titleText = rowView.findViewById(R.id.securityPrivacyTitle) as TextView
        val securityPrivacySubTitle = rowView.findViewById(R.id.securityPrivacySubTitle) as TextView
        val securitySettingSwitch = rowView.findViewById(R.id.securitySettingSwitch) as Switch
        val icon = rowView.findViewById(R.id.securityIcon) as ImageView

        titleText.typeface = OnedioCommon.changeTypeFace4Activity(context)
        securityPrivacySubTitle.typeface = OnedioCommon.changeTypeFace4Activity(context)

        titleText.text = securityPrivacyList[position].title
        securitySettingSwitch.isChecked = securityPrivacyList[position].valOfSecuritySettingItem.toBoolean()

        isSwitchesVisible(position, securitySettingSwitch, icon)

        itemHasSubtitle(position, titleText, securityPrivacySubTitle)

        changeTextColorDependDarkModeOn(titleText, securityPrivacySubTitle)

        switchCheckedChangeListener(position, securitySettingSwitch)

        return rowView
    }

    fun isSwitchesVisible(pos: Int, cSwitch: Switch, cImageView: ImageView) {
        if (securityPrivacyList[pos].isSwitchVisible) {
            cSwitch.visibility = View.VISIBLE
            cImageView.visibility = View.INVISIBLE
        } else {
            cSwitch.visibility = View.INVISIBLE
            cImageView.visibility = View.VISIBLE
        }
    }

    fun itemHasSubtitle(pos: Int, cTitleTextView: TextView, cSubtitleTextView: TextView) {
        if (!securityPrivacyList[pos].subTitle.equals("")) {
            cSubtitleTextView.visibility = View.VISIBLE
            cSubtitleTextView.text = securityPrivacyList[pos].subTitle
        } else {
            val scale = context.resources.getDisplayMetrics().density
            val dpAsPixels = (20 * scale + 0.5f).toInt()
            cTitleTextView.setPadding(0, 0, 0, dpAsPixels)
        }
    }

    fun changeTextColorDependDarkModeOn(cTitleTextView: TextView, cSubtitleTextView: TextView) {
        val sharedPref = context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if (theme == "dark") {
            cTitleTextView.setTextColor(Color.parseColor("#FFFFFF"))
            cSubtitleTextView.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            cTitleTextView.setTextColor(Color.parseColor("#191919"))
            cSubtitleTextView.setTextColor(Color.parseColor("#191919"))
        }
    }

    fun switchCheckedChangeListener(pos: Int, cSwitch: Switch) {
        cSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                securityPrivacyList[pos].valOfSecuritySettingItem = "true"
                OnedioSharedPrefs(context).saveSecuritySetting(securityPrivacyList)
            } else {
                securityPrivacyList[pos].valOfSecuritySettingItem = "false"
                OnedioSharedPrefs(context).saveSecuritySetting(securityPrivacyList)
            }
        }
    }

    override fun getItem(p0: Int): Any {
        return securityPrivacyList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return securityPrivacyList.size
    }

}