package com.example.dynamicwidget

import com.example.dynamicwidget.layoutPlace.ComponentMPValue
import com.example.dynamicwidget.layoutPlace.ConstLayoutPlace

interface IFillHugeCardWidget {

    fun setComponentProp(componentMPValue: ComponentMPValue): FillHugeCardWidget

    fun setViewSpecialProp(backgroundColor: String): FillHugeCardWidget

    fun setComponentMarginValue(constLayoutPlace: ConstLayoutPlace): FillHugeCardWidget

    fun getDpValue(valOfMargin: Int): Int

}