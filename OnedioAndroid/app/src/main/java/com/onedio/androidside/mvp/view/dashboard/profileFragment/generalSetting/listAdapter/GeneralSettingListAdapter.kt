package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.listAdapter

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.common.ThemeHelper
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.model.ApplicationSettingModel
import com.onedio.androidside.mvp.model.dashboard.model.GeneralSettingModel
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.GeneralSettingActivityViewImpl

class GeneralSettingListAdapter(
    val context: Context,
    val generalSettintList: MutableList<GeneralSettingModel>,
    val applicationSettingList: MutableList<ApplicationSettingModel>,
    val listener: (GeneralSettingModel, Int) -> Unit
) :
    BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val layouInflator = LayoutInflater.from(context)
        val inflater = layouInflator
        val rowView = inflater.inflate(R.layout.general_setting_list_view_item, null, true)

        val itemOfGeneralSetting =
            rowView.findViewById(R.id.itemOfGeneralSetting) as ConstraintLayout
        val titleText = rowView.findViewById(R.id.generalListTitle) as TextView
        val generalSettingItemIcon = rowView.findViewById(R.id.generalSettingItemIcon) as ImageView
        val generalSettingDarkModeSwitch =
            rowView.findViewById(R.id.generalSettingDarkModeSwitch) as Switch
        val generalSettingListViewArrow =
            rowView.findViewById(R.id.generalSettingListViewArrow) as ImageView

        titleText.typeface = OnedioCommon.changeTypeFace4Activity(context)

        isSwitchesVisible(position, generalSettingDarkModeSwitch, generalSettingListViewArrow)

        changeTextColorDependDarkModeOn(titleText)

        setData2Adapter(position, titleText, generalSettingItemIcon)

        switchCheckedChangeListener(position, generalSettingDarkModeSwitch)

        itemOfGeneralSetting.setOnClickListener {
            listener(generalSettintList[position], position)
        }

        return rowView
    }

    private fun isSwitchesVisible(pos: Int, cSwitch: Switch, cImageView: ImageView) {
        if (generalSettintList[pos].hasSwitch) {
            cSwitch.visibility = View.VISIBLE
            cImageView.visibility = View.INVISIBLE

            cSwitch.isChecked = applicationSettingList[0].valOfAppSettingItem.toBoolean()

        } else {
            cSwitch.visibility = View.INVISIBLE
            cImageView.visibility = View.VISIBLE
        }
    }

    private fun changeTextColorDependDarkModeOn(cTextView: TextView) {
        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if (theme == "dark")
            cTextView.setTextColor(Color.parseColor("#FFFFFF"))
        else
            cTextView.setTextColor(Color.parseColor("#191919"))
    }

    private fun setData2Adapter(pos: Int, cTextView: TextView, cImageView: ImageView) {
        cTextView.text = generalSettintList[pos].title
        cImageView.setImageResource(generalSettintList[pos].icon)
    }

    private fun switchCheckedChangeListener(pos: Int, cSwitch: Switch) {
        cSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (pos == 5)
                    ThemeHelper.applyTheme(context, "dark")
                applicationSettingList[0].valOfAppSettingItem = "true"
                OnedioSharedPrefs(context).saveApplicationSetting(applicationSettingList)

            } else {
                if (pos == 5)
                    ThemeHelper.applyTheme(context, "light")
                applicationSettingList[0].valOfAppSettingItem = "false"
                OnedioSharedPrefs(context).saveApplicationSetting(applicationSettingList)
            }

            if (pos == 5) {
                val intent = Intent(context, DashboardActivityViewImpl::class.java)
                val options = ActivityOptions.makeCustomAnimation(
                    context,
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent, options.toBundle())
            }
        }
    }

    override fun getItem(p0: Int): Any {
        return generalSettintList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return generalSettintList.size
    }

}