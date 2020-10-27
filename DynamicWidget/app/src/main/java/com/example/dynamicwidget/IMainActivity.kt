package com.example.dynamicwidget

import com.example.dynamicwidget.model.ConfigModel

interface IMainActivity {

    fun inflateHugeCardComponent(recyclerViewId: Int, constraintId: Int, layout: Int, sizeOfHugeCardListItems: Int)

    fun includeXMLsByOrder(jsonObj: ConfigModel)

}