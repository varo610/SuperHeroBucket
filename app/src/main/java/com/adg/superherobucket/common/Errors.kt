package com.adg.superherobucket.common

sealed class BaseError(
    val id: Int,
    val description: String) {

    enum class Code(val code: Int) {
        BASE_DEFAULT_ERROR(1001)
    }

    class NetworkError(
        id: Int,
        description: String) : BaseError(id, description) {

        enum class Code(val code: Int) {
            NETWORK_DEFAULT_ERROR(2001),
            NETWORK_IO_EXCEPTION(2002),
            NETWORK_SERVER_ERROR(2003),
            NETWORK_UNAUTHORIZED(2004)
        }

    }

}

