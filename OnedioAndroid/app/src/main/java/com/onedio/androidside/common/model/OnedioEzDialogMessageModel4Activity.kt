package com.onedio.androidside.common.model

import android.content.Context

data class OnedioEzDialogMessageModel4Activity(
    val context: Context,
    val title: String,
    val message: String,
    val buttonText: String,
    val headerColor: Int,
    val titleTextColor: Int,
    val messageTextColor: Int,
    val buttonTextColor: Int
)