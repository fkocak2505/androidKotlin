package com.onedio.androidside.common

import android.app.Activity
import android.content.Context
import com.onedio.androidside.R

class OnedioCustomIntent {

    companion object{

        fun startAnim(context: Context, animType: String){
            val activity: Activity = context as Activity
            when(animType){
                "l2r" -> activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
                "r2l" -> activity.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
            }
        }
    }
}