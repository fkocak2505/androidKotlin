package com.example.useraggreement

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        aaaa.setOnClickListener {
            val customDialog = CustomDialog(this@MainActivity)

            val lp = WindowManager.LayoutParams()
            lp.copyFrom(customDialog.window?.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.MATCH_PARENT
            customDialog.show()
            customDialog.window?.attributes = lp

            customDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            customDialog.show()
        }
    }
}
