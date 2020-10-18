package com.commitmon.api.exception

enum class ErrorCode(
    var status: Int,
    var message: String
) {
    INVALID_INPUT_VALUE(400, "Input is invalid value"),
    INVALID_TYPE_VALUE(400, "Type is invalid value"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "Not found"),
    HANDLE_ACCESS_DENIED(403, "Access is denied"),
    METHOD_NOT_ALLOWED(405, " Method type is invalid"),
    INTERNAL_SERVER_ERROR(500, "Server error"),
}