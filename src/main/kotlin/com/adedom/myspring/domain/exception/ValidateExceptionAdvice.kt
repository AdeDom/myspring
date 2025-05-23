package com.adedom.myspring.domain.exception

import com.adedom.myspring.data.model.BaseError
import com.adedom.myspring.data.model.ErrorInfo
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ValidateExceptionAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidateException::class)
    fun badRequest(e: ValidateException): BaseError =
        BaseError(
            status = HttpStatus.BAD_REQUEST.value(),
            errors =
                listOf(
                    ErrorInfo(
                        code = 1000,
                        message = e.message,
                    ),
                ),
        )
}
