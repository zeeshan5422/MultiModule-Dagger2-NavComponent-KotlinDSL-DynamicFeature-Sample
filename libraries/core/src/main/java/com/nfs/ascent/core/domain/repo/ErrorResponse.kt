package com.nfs.ascent.core.domain.repo

enum class ErrorCode {
    UNAUTHORIZED,
    NOT_FOUND,
    NO_NETWORK,
    BAD_RESPONSE,
    EMPTY_DATA,
    UNKNOWN
}

data class ErrorResponse(val code : ErrorCode = ErrorCode.UNKNOWN,
                         val error : String = "",
                         val message : String = "") {
    override fun toString(): String {
        return code.name + " - " + error + ": " + message
    }
}