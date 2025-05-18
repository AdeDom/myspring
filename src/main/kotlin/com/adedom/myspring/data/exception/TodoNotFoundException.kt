package com.adedom.myspring.data.exception

import java.lang.RuntimeException

class TodoNotFoundException(
    override val message: String?,
) : RuntimeException(message)
