package com.musicore.nestedrecyclerview

data class ParentModel (
    val title : String = "",
    val type: String = "",
    val children : List<ChildModel>,
    var isAdLoaded: Boolean? = null
)