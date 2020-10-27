package com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets

import com.example.dynamicwidget.layoutPlace.ComponentMPValue
import com.example.dynamicwidget.layoutPlace.ConstLayoutPlace

interface IFillHugeCardWidget {

    fun setComponentProp(componentMPValue: ComponentMPValue): FillHugeCardWidget

    fun setViewSpecialProp(backgroundColor: String): FillHugeCardWidget

    fun setComponentMarginValue(constLayoutPlace: ConstLayoutPlace): FillHugeCardWidget

    fun setRecyclerViewDivider(): FillHugeCardWidget

    fun getDpValue(valOfMargin: Int): Int

}