package com.example.skeleton

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ethanhua.skeleton.Skeleton
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getSkeletonRowCount(applicationContext)

        val skeleton = Skeleton.bind(skeleton)
            .load(R.layout.skeleton_row_layout)
            .show()

        Handler().postDelayed(
            Runnable {
                skeleton.hide()
            },
            3000
        )

    }

    fun getSkeletonRowCount(context: Context): Int {
        val pxHeight = getDeviceHeight(context)
        val skeletonRowHeight = resources
            .getDimension(R.dimen.row_layout_height).toInt() //converts to pixel
        return Math.ceil((pxHeight / skeletonRowHeight).toDouble()).toInt()
    }

    fun getDeviceHeight(context: Context): Int {
        val resources = context.getResources()
        val metrics = resources.getDisplayMetrics()
        return metrics.heightPixels
    }
}
