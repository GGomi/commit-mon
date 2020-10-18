package com.commitmon.api.exception

class BusinessException(
    val code: ErrorCode,
    override val message: String? = null
) : RuntimeException(
    message ?: code.message
)