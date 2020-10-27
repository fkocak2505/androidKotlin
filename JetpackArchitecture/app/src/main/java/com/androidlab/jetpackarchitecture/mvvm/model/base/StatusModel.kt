package com.androidlab.jetpackarchitecture.mvvm.model.base

data class StatusModel(
    val error: Boolean?,
    val code: Int?,
    val type: String?,
    val message: String?
)