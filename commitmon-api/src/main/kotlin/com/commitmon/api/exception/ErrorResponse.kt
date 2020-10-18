package com.commitmon.api.exception

import org.springframework.validation.BindingResult
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.time.LocalDateTime

class ErrorResponse private constructor(
    val message: String,
    val status: Int,
    var errors: List<FieldError> = listOf(),
    var code: String
) {
    val timestamp: LocalDateTime = LocalDateTime.now()

    constructor(code: ErrorCode) : this(message = code.message, status = code.status, code = code.name)

    constructor(code: ErrorCode, message: String) : this(message = message, status =  code.status, code =  code.name)

    constructor(code: ErrorCode, e: Exception) : this(code) {
        errors = FieldError.ofList(e)
    }

    constructor(code: ErrorCode, e: BusinessException) : this(code, e.message ?: code.message) {
        errors = FieldError.ofEmptyList()
    }

    class FieldError private constructor(
        val field: String,
        val value: String,
        val reason: String
    ) {
        companion object {
            fun ofList(bindingResult: BindingResult): List<FieldError> {
                return bindingResult.fieldErrors.map {
                    FieldError(it.field, when (it.rejectedValue) {
                        is String -> it.rejectedValue as String
                        else -> ""
                    }, it.defaultMessage ?: "")
                }
            }

            fun ofList(e: MethodArgumentTypeMismatchException): List<FieldError> {
                val value = if (e.value == null) "" else e.value as String
                return listOf(FieldError(field = e.name, value = value, reason = e.errorCode))
            }

            fun ofList(e: Exception): List<FieldError> {
                return listOf(FieldError(field = "", value = "", reason = e.message
                    ?: "error message is empty"))
            }

            fun ofEmptyList(): List<FieldError> {
                return emptyList()
            }
        }
    }
}