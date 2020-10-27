package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.applicationSetting.listAdaptert


import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.mvp.model.dashboard.model.ApplicationSettingModel
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.applicationSetting.ApplicationSettingActivityViewImpl

class ApplicationSettingListAdapter(
    val context: Context,
    val mListOfVersion: MutableList<String>,
    val applicationSettingList: MutableList<ApplicationSettingModel>
) :
    BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val layouInflator = LayoutInflater.from(context)
        val inflater = layouInflator
        val rowView = inflater.inflate(R.layout.application_setting_list_view_item, null, true)

        val titleText = rowView.findViewById(R.id.applicationListTitle) as TextView
        val applicationListSubTitle = rowView.findViewById(R.id.applicationListSubTitle) as TextView
        val appSettingSwitch = rowView.findViewById(R.id.appSettingSwitch) as Switch
        //val appSettingSpinner = rowView.findViewById(R.id.appSettingSpinner) as Spinner

        /*val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, mListOfVersion)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        appSettingSpinner!!.adapter = spinnerAdapter*/

        titleText.typeface = OnedioCommon.changeTypeFace4Activity(context)
        applicationListSubTitle.typeface = OnedioCommon.changeTypeFace4Activity(context)

        titleText.text = applicationSettingList[position].title
        appSettingSwitch.isChecked = applicationSettingList[position].valOfAppSettingItem.toBoolean()

        isSwitchesVisible(position, appSettingSwitch)

        itemHasSubtitle(position, titleText, applicationListSubTitle)

        changeTextColorDependDarkModeOn(titleText, applicationListSubTitle)

        switchCheckedChangeListener(position, appSettingSwitch)

        return rowView
    }

    fun isSwitchesVisible(pos: Int, cSwitch: Switch) {
        if (applicationSettingList[pos].isSwitchVisible) {
            cSwitch.visibility = View.VISIBLE
            //appSettingSpinner.visibility = View.INVISIBLE
        } else {
            cSwitch.visibility = View.INVISIBLE
            //appSettingSpinner.visibility = View.VISIBLE
        }
    }

    fun itemHasSubtitle(pos: Int, cTitleTextView: TextView, cSubtitleTextView: TextView) {
        if (!applicationSettingList[pos].subTitle.equals("")) {
            cSubtitleTextView.visibility = View.VISIBLE
            cSubtitleTextView.text = applicationSettingList[pos].subTitle
        } else {
            val scale = context.resources.getDisplayMetrics().density
            val dpAsPixels = (20 * scale + 0.5f).toInt()
            cTitleTextView.setPadding(0, 0, 0, dpAsPixels)
        }
    }

    fun changeTextColorDependDarkModeOn(cTitleTextView: TextView, cApplicationListSubTitle: TextView) {
        if (AppCompatDelegate.getDefaultNightMode() === AppCompatDelegate.MODE_NIGHT_YES) {
            cTitleTextView.setTextColor(Color.parseColor("#e6e6e6"))
            cApplicationListSubTitle.setTextColor(Color.parseColor("#e6e6e6"))
        } else {
            cTitleTextView.setTextColor(Color.parseColor("#191919"))
            cApplicationListSubTitle.setTextColor(Color.parseColor("#191919"))
        }
    }

    fun switchCheckedChangeListener(pos: Int, cSwitch: Switch) {
        cSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (pos == 0)
                    OnedioSharedPrefs(context).setIsNightModeEnabled(true)
                applicationSettingList[pos].valOfAppSettingItem = "true"
                OnedioSharedPrefs(context).saveApplicationSetting(applicationSettingList)

            } else {
                if (pos == 0)
                    OnedioSharedPrefs(context).setIsNightModeEnabled(false)
                applicationSettingList[pos].valOfAppSettingItem = "false"
                OnedioSharedPrefs(context).saveApplicationSetting(applicationSettingList)
            }

            if (pos == 0){
                val intent = Intent(context, ApplicationSettingActivityViewImpl::class.java)
                val options = ActivityOptions.makeCustomAnimation(context, android.R.anim.fade_in, android.R.anim.fade_out)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent, options.toBundle())
            }
        }
    }

    override fun getItem(p0: Int): Any {
        return applicationSettingList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return applicationSettingList.size
    }

}