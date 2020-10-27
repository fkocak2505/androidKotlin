package com.musicore.popupdialogandroid

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var backgroundTopColor: Int = Color.WHITE
    private var backgroundBottomColor: Int = Color.WHITE
    private var titleTextColor: Int = Color.BLACK
    private var contentTextColor: Int = Color.BLACK

    private var positiveIconColor: Int = Color.BLACK
    private var negativeIconColor: Int = Color.BLACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        aaaa.setOnClickListener {

            val dialog = PopupClass(this)
                .setBackgroundTopColor(backgroundTopColor)
                .setBackgroundBottomColor(backgroundBottomColor)
                .setCanceledOnTouchOutside(false)
                .setTitleHidden(false)
                .setElegantActionClickListener(object :
                    CustomElegantActionListeners {
                    override fun onPositiveListener(dialog: PopupClass) {

                        dialog.dismiss()
                    }

                    override fun onNegativeListener(dialog: PopupClass) {
                        dialog.dismiss()
                    }

                    override fun onGotItListener(dialog: PopupClass) {

                        dialog.dismiss()
                    }

                    override fun onCloseClicked(dialog: PopupClass) {

                        dialog.dismiss()
                    }

                    override fun onCancelListener(dialog: DialogInterface) {
                        dialog.dismiss()
                    }
                })
                .show()  // Finally don't forget to call show()

            if (dialog.getTitleIconView() != null) {
                // You can use glide or picasso to display your title image
                val imageUrl =
                    R.drawable.ic_onedio_logo_lm
                Glide.with(this).load(imageUrl)
                    .into(dialog.getTitleIconView()!!)
                /*dialog.getTitleTextView()!!.text = "Onedio' da daha kaliteli zaamn geçirmeniz için  uygulamamızı güncelledik. Uygulamayı Lütfen güncelleyiniz."
                dialog.getTitleTextView()!!.setTextColor(titleTextColor)*/
                dialog.getTitleTextView()!!.visibility = View.GONE
                dialog.getContentTextView()!!.text =
                    "Onedio' da daha kaliteli zaamn geçirmeniz için  uygulamamızı güncelledik. Uygulamayı Lütfen güncelleyiniz."
                dialog.getContentTextView()!!.setTextColor(contentTextColor)
                /*dialog.getPositiveButtonIconView()!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_launcher_foreground
                    )
                )
                dialog.getPositiveButtonIconView()!!.setColorFilter(positiveIconColor)
                dialog.getPositiveButtonTextView()!!.text = "positiveText"
                dialog.getNegativeButtonIconView()!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_launcher_foreground
                    )
                )
                dialog.getNegativeButtonIconView()!!.setColorFilter(negativeIconColor)
                dialog.getNegativeButtonTextView()!!.text = "negative text"

                dialog.getGotItButtonTextView()!!.visibility = View.GONE
                dialog.getGotItButton()!!.visibility = View.GONE*/
            }

        }
    }
}