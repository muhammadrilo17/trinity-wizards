package com.rilodev.core.data.source.remote.response.base

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status_code")
    var statusCode: Int?,
    @SerializedName("status_message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean
)