package com.adedom.myspring.domain.exception

import java.lang.RuntimeException

class ValidateException(
    override val message: String?,
) : RuntimeException(message)
