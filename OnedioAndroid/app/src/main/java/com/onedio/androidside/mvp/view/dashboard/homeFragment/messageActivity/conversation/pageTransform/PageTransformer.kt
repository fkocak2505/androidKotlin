package com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.conversation.pageTransform

import android.view.View
import androidx.viewpager.widget.ViewPager

class PageTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        if (position < -1) {  // [-Infinity,-1)
            // This page is way off-screen to the left_to_right.
            page.alpha = 0f
        } else if (position <= 1) { // [-1,1]
            page.scaleX = Math.max(MIN_SCALE, 1 - Math.abs(position))
            page.scaleY = Math.max(MIN_SCALE, 1 - Math.abs(position))
            page.alpha = Math.max(MIN_ALPHA, 1 - Math.abs(position))
        } else {  // (1,+Infinity]
            // This page is way off-screen to the right_to_left.
            page.alpha = 0f
        }
    }

    companion object {
        private val MIN_SCALE = 0.9f
        private val MIN_ALPHA = 0.1f
    }
}
