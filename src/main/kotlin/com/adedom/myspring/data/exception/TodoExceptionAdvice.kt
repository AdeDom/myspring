package com.adedom.myspring.data.exception

import com.adedom.myspring.data.model.BaseError
import com.adedom.myspring.data.model.ErrorInfo
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class TodoExceptionAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TodoNotFoundException::class)
    fun todoNotFound(e: TodoNotFoundException): BaseError =
        BaseError(
            status = HttpStatus.NOT_FOUND.value(),
            errors =
                listOf(
                    ErrorInfo(
                        code = 1001,
                        message = e.message,
                    ),
                ),
        )
}
