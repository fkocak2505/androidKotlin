package com.example.bottomsheetkotlin

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.AbsListView
import android.widget.ExpandableListView

class BottomSheetExpandableListView(p_context: Context, p_attrs: AttributeSet) :
    ExpandableListView(p_context, p_attrs) {

    override fun onInterceptTouchEvent(p_event: MotionEvent): Boolean {
        return true
    }

    override fun onTouchEvent(p_event: MotionEvent): Boolean {
        if (canScrollVertically(this)) {
            parent.requestDisallowInterceptTouchEvent(true)
        }
        return super.onTouchEvent(p_event)
    }

    fun canScrollVertically(view: AbsListView?): Boolean {

        var canScroll = false

        if (view != null && view.childCount > 0) {

            val isOnTop = view.firstVisiblePosition != 0 || view.getChildAt(0).top != 0
            val isAllItemsVisible = isOnTop && lastVisiblePosition == view.childCount

            if (isOnTop || isAllItemsVisible)
                canScroll = true
        }

        return canScroll
    }

}