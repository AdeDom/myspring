package com.adedom.myspring.data.model

import com.adedom.myspring.data.constant.HttpConstant
import com.adedom.myspring.data.enumeration.HttpStatusResponse

data class BaseResponse<T>(
    val version: String = HttpConstant.VERSION,
    val status: HttpStatusResponse = HttpStatusResponse.SUCCESS,
    val data: T,
)
