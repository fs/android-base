package com.flatstack.android.model.network.errors

data class ServerError(
    val code: Int,
    val errorBody: String?
) : RuntimeException()
