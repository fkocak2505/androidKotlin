package com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.fillRootXMLs

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicwidget.layoutPlace.ComponentMPValue
import com.example.dynamicwidget.layoutPlace.ComponentMarginsAndPaddings
import com.example.dynamicwidget.layoutPlace.ConstLayoutPlace
import com.example.dynamicwidget.layoutPlace.PlaceObj
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig.Response4WidgetConfig
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.FillHugeCardWidget
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.fillRootXMLs.model.WidgetLayoutModel
import com.onedio.androidside.singleton.OnedioSingletonPattern


class FillRootXMLs :
    IFillRootXMLs {

    //private lateinit var context: Context
    private lateinit var activity: FragmentActivity
    private lateinit var widgetConfigModel: Response4WidgetConfig
    private var xmlConfigMap: HashMap<String, Int> = HashMap()
    private var xmlViewsMap: HashMap<String, WidgetLayoutModel> = HashMap()
    private var hugeCardRecyclerViewIds: MutableList<Int> = mutableListOf()
    private lateinit var viewP: View
    private lateinit var v: View
    private lateinit var feedLinearLayout: LinearLayout
    private var idOfRecyclerViews: Int = 0

    constructor(
        activity: FragmentActivity,
        viewP: View,
        widgetConfigModel: Response4WidgetConfig,
        feedLinearLayout: LinearLayout,
        xmlViewsMap: HashMap<String, WidgetLayoutModel>
    ) : this() {
        this.activity = activity
        this.widgetConfigModel = widgetConfigModel
        this.viewP = viewP
        this.feedLinearLayout = feedLinearLayout
        this.xmlViewsMap = xmlViewsMap
    }

    constructor()

    fun mapOfWidgetLayoutAttr(): FillRootXMLs {

        widgetConfigModel.data?.widgets?.forEach { itemOfWidget ->
            val widgetName = itemOfWidget.name
            val widgetId = itemOfWidget.id

            when (widgetName) {
                OnedioConstant.WIDGET_MOBILE_SLIDER -> {
                    xmlConfigMap[OnedioConstant.WIDGET_MOBILE_SLIDER + "$" + widgetId] =
                        R.layout.widget_slider
                }
                OnedioConstant.WIDGET_MOBILE_SLIDER_2 -> {
                    xmlConfigMap[OnedioConstant.WIDGET_MOBILE_SLIDER_2 + "$" + widgetId] =
                        R.layout.widget_second_slider
                }
                OnedioConstant.WIDGET_MOBILE_BIG_CARD -> {
                    xmlConfigMap[OnedioConstant.WIDGET_MOBILE_BIG_CARD + "$" + widgetId] =
                        R.layout.widget_huge_card
                }
                OnedioConstant.WIDGET_MOBILE_EDITOR_CHOOSING -> {
                    xmlConfigMap[OnedioConstant.WIDGET_MOBILE_EDITOR_CHOOSING + "$" + widgetId] =
                        R.layout.widget_editor_choosing
                }
                OnedioConstant.WIDGET_MOBILE_TEST -> {
                    xmlConfigMap[OnedioConstant.WIDGET_MOBILE_TEST + "$" + widgetId] =
                        R.layout.widget_tests
                }
                OnedioConstant.WIDGET_MOBILE_VIDEOS -> {
                    xmlConfigMap[OnedioConstant.WIDGET_MOBILE_VIDEOS + "$" + widgetId] =
                        R.layout.widget_videos
                }
                OnedioConstant.WIDGET_MOBILE_SPORTS -> {
                    xmlConfigMap[OnedioConstant.WIDGET_MOBILE_SPORTS + "$" + widgetId] =
                        R.layout.widget_sports
                }
                OnedioConstant.WIDGET_MOBILE_INFINITE -> {
                    xmlConfigMap[OnedioConstant.WIDGET_MOBILE_INFINITE + "$" + widgetId] =
                        R.layout.widget_infinite
                }
            }
        }


        /*xmlConfigMap[OnedioConstant.WIDGET_MOBILE_SLIDER + "$3248294829842"] = R.layout.widget_slider
        xmlConfigMap[OnedioConstant.WIDGET_MOBILE_BIG_CARD + "$23847294829"] = R.layout.widget_huge_card
        xmlConfigMap[OnedioConstant.WIDGET_MOBILE_EDITOR_CHOOSING + "$28736258273"] = R.layout.widget_editor_choosing
        xmlConfigMap[OnedioConstant.WIDGET_MOBILE_BIG_CARD + "$87459364983"] = R.layout.widget_huge_card
        xmlConfigMap[OnedioConstant.WIDGET_MOBILE_TEST + "$234820852"] = R.layout.widget_tests
        xmlConfigMap[OnedioConstant.WIDGET_MOBILE_BIG_CARD + "$38746583"] = R.layout.widget_huge_card
        xmlConfigMap[OnedioConstant.WIDGET_MOBILE_BIG_CARD + "$678645345"] = R.layout.widget_huge_card
        xmlConfigMap[OnedioConstant.WIDGET_MOBILE_VIDEOS + "$34536346"] = R.layout.widget_videos
        xmlConfigMap[OnedioConstant.WIDGET_MOBILE_BIG_CARD + "$65756755"] = R.layout.widget_huge_card
        xmlConfigMap[OnedioConstant.WIDGET_MOBILE_SPORTS + "$34535343"] = R.layout.widget_sports
        xmlConfigMap[OnedioConstant.WIDGET_MOBILE_INFINITE + "$2973297"] = R.layout.widget_infinite*/

        return this
    }


    fun fillWidgetXMLs(): FillRootXMLs {

        val widgets = widgetConfigModel.data?.widgets
        val sortedWidgetList = widgets?.sortedWith(compareBy { it.order })

        for (itemOfConfigData in sortedWidgetList!!) {

            if (itemOfConfigData.name == OnedioConstant.WIDGET_MOBILE_BIG_CARD) {
                hugeCardRecyclerViewIds.add(idOfRecyclerViews)
                idOfRecyclerViews += 1
                inflateHugeCardComponent(
                    hugeCardRecyclerViewIds[hugeCardRecyclerViewIds.size - 1],
                    R.id.hugeCardConstraint,
                    R.layout.widget_huge_card
                )
            } else {
                val xmlPart = xmlConfigMap[itemOfConfigData.name + "$" + itemOfConfigData.id]

                xmlPart?.let {

                }?.run {
                    /*View.inflate(
                        activity.applicationContext,
                        xmlPart,
                        viewP.findViewById(R.id.feedLinearLayout)
                    )*/

                    val viewGroup = viewP.findViewById<ViewGroup>(R.id.feedLinearLayout)
                    val views = LayoutInflater.from(activity.applicationContext)
                        .inflate(xmlPart, viewGroup, false)
                    viewGroup.addView(views)

                    var widgetTitle = itemOfConfigData.title
                    widgetTitle?.let {

                    } ?: run {
                        widgetTitle = ""
                    }
                    xmlViewsMap[itemOfConfigData.name + "$" + itemOfConfigData.id] =
                        WidgetLayoutModel(
                            widgetTitle!! + "§" + itemOfConfigData.targetId,
                            views
                        )

                }
            }
        }

        OnedioSingletonPattern.instance.setXmlViews(xmlViewsMap)
        OnedioSingletonPattern.instance.setHugeCardRecyclerViewIds(hugeCardRecyclerViewIds)
        return this
    }


    private fun inflateHugeCardComponent(
        recyclerViewId: Int,
        constraintId: Int,
        layout: Int
    ) {

        val inflater =
            activity.applicationContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        v = inflater.inflate(layout, null)

        val constLayout = v.findViewById<ConstraintLayout>(constraintId)

        val recyclerView = RecyclerView(activity.applicationContext)
        val line = View(activity.applicationContext)

        /*val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels*/

        val displayMetrics = activity.applicationContext.resources.displayMetrics
        val dpHeight = displayMetrics.heightPixels / displayMetrics.density
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density

        Log.i("WIDTH ---- ", dpWidth.toString())

        FillHugeCardWidget(
            activity.applicationContext,
            constLayout,
            recyclerView
        )
            .setComponentProp(
                ComponentMPValue(
                    recyclerViewId,
                    dpWidth.toInt(),
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ComponentMarginsAndPaddings(
                        0,
                        0,
                        0,
                        20
                    ),
                    null
                )
            )
            .setComponentMarginValue(
                ConstLayoutPlace(
                    PlaceObj(ConstraintSet.START, constLayout.id, ConstraintSet.START),
                    PlaceObj(ConstraintSet.TOP, constLayout.id, ConstraintSet.TOP),
                    PlaceObj(ConstraintSet.END, constLayout.id, ConstraintSet.END),
                    PlaceObj(ConstraintSet.BOTTOM, constLayout.id, ConstraintSet.BOTTOM)
                )
            )
            .setRecyclerViewDivider()

        //fillRecyclerViewData(recyclerView, sizeOfHugeCardListItems)

        /*FillHugeCardWidget(context, constLayout, line)
            .setComponentProp(
                ComponentMPValue(
                    2,
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    10,
                    ComponentMarginsAndPaddings(
                        0,
                        10,
                        0,
                        0
                    ),
                    null
                )
            )
            .setViewSpecialProp("#e2e2e2")
            .setComponentMarginValue(
                ConstLayoutPlace(
                    PlaceObj(ConstraintSet.START, recyclerView.id, ConstraintSet.START),
                    PlaceObj(ConstraintSet.TOP, recyclerView.id, ConstraintSet.BOTTOM),
                    PlaceObj(ConstraintSet.END, recyclerView.id, ConstraintSet.END),
                    null
                )
            )*/

        feedLinearLayout.addView(v)

    }
}