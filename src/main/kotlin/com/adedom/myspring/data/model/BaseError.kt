package com.adedom.myspring.data.model

import com.adedom.myspring.data.constant.HttpConstant

data class BaseError(
    val version: String = HttpConstant.VERSION,
    val status: Int,
    val errors: List<ErrorInfo>,
)

data class ErrorInfo(
    val code: Int,
    val message: String?,
)
