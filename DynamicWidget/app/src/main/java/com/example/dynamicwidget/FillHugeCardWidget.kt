package com.example.dynamicwidget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.dynamicwidget.layoutPlace.ComponentMPValue
import com.example.dynamicwidget.layoutPlace.ConstLayoutPlace

class FillHugeCardWidget(context: Context, xmlOfLayout: ConstraintLayout, cmp: View) :
    IFillHugeCardWidget {

    private var context: Context
    private var xmlOfLayout: ConstraintLayout
    private var cmp: View


    init {
        this.context = context
        this.xmlOfLayout = xmlOfLayout
        this.cmp = cmp
    }

    @SuppressLint("ResourceType")
    override fun setComponentProp(componentMPValue: ComponentMPValue): FillHugeCardWidget {
        cmp.id = componentMPValue.id
        val params = ConstraintLayout.LayoutParams(
            getDpValue(componentMPValue.width),
            getDpValue(componentMPValue.height)
        )

        params.setMargins(
            getDpValue(componentMPValue.componentMargins?.left!!),
            getDpValue(componentMPValue.componentMargins.top),
            getDpValue(componentMPValue.componentMargins.rigth),
            getDpValue(componentMPValue.componentMargins.bottom)
        )

        cmp.layoutParams = params

        xmlOfLayout.addView(cmp)

        return this
    }

    override fun setViewSpecialProp(backgroundColor: String): FillHugeCardWidget {
        cmp.setBackgroundColor(Color.parseColor(backgroundColor))
        return this
    }

    override fun setComponentMarginValue(constLayoutPlace: ConstLayoutPlace): FillHugeCardWidget {
        var constraintSet = ConstraintSet()
        constraintSet.clone(xmlOfLayout)

        constraintSet.connect(
            cmp.id,
            constLayoutPlace.right?.f1!!,
            constLayoutPlace.right.id,
            constLayoutPlace.right.f2
        )
        constraintSet.connect(
            cmp.id,
            constLayoutPlace.left?.f1!!,
            constLayoutPlace.left.id,
            constLayoutPlace.left.f2
        )
        constraintSet.connect(
            cmp.id,
            constLayoutPlace.top?.f1!!,
            constLayoutPlace.top.id,
            constLayoutPlace.top.f2
        )
        constraintSet.applyTo(xmlOfLayout)

        return this
    }

    override fun getDpValue(valOfMargin: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (valOfMargin * scale + 0.5f).toInt()
    }

}