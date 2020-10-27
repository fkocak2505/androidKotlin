package com.weatherlogger.androidapp.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

//==============================================================================================
/**
 * This is Custom ViewPager for WeatherMainPagerActivityViewImpl..
 */
//==============================================================================================

class CustomViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {
    var pagingEnable = true

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return pagingEnable && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return pagingEnable && super.onInterceptTouchEvent(ev)
    }
}