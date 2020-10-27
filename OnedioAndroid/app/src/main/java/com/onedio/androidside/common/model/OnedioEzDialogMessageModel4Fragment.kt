package com.onedio.androidside.common.model

import android.app.Activity

data class OnedioEzDialogMessageModel4Fragment(
    val activity: Activity,
    val title: String,
    val message: String,
    val buttonText: String,
    val headerColor: Int,
    val titleTextColor: Int,
    val messageTextColor: Int,
    val buttonTextColor: Int
)