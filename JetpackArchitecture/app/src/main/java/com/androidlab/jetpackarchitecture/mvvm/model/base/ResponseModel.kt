package com.androidlab.jetpackarchitecture.mvvm.model.base

data class ResponseModel<T>(
    val data :T?,
    val status: StatusModel
)