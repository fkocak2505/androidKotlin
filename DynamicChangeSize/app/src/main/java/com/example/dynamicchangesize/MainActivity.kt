package com.example.dynamicchangesize

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity() {

    var array = arrayOf(
        "Melbourne",
        "Vienna",
        "Vancouver",
        "Toronto",
        "Calgary",
        "Adelaide",
        "Perth",
        "Auckland",
        "Helsinki",
        "Hamburg",
        "Munich",
        "New York",
        "Sydney",
        "Paris",
        "Cape Town",
        "Barcelona",
        "London",
        "Bangkok",
        "Bangkoka",
        "Bangkokwa",
        "Bangkokffgg"
    )

    private lateinit var constraintSet: ConstraintSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = ArrayAdapter(this,
            R.layout.itemm, array)

        listView.setAdapter(adapter)

        if(false){
            button2.visibility = View.GONE
            /*constraintSet = ConstraintSet()
            constraintSet.clone(constraintLayout)
            constraintSet.connect(listView.id, ConstraintSet.TOP, 0, ConstraintSet.BOTTOM)
            constraintSet.applyTo(constraintLayout)

            var params = listView.layoutParams as ViewGroup.MarginLayoutParams

            params.setMargins(
                0, TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 48f, resources
                        .displayMetrics
                ).toInt(), 0, 0
            )
            params.setMargins(0,24,0,0)
            listView.layoutParams = params*/


            /*val params2 = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            params2.setMargins(0, 48, 0, 0)
            listView.layoutParams = params2*/


            val constraintSet = ConstraintSet()
            constraintSet.clone(constraintLayout)
            constraintSet.connect(
                listView.id,
                ConstraintSet.TOP,
                0,
                ConstraintSet.BOTTOM,
                dpToPx(48)
            )
            constraintSet.applyTo(constraintLayout)

        }
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().getDisplayMetrics().density).toInt()
    }

    fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }
}
