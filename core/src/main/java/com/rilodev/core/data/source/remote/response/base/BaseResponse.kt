package com.rilodev.core.data.source.remote.response.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<TValue>(
    @SerializedName("page")
    var message: String?,
    @SerializedName("results")
    var results: TValue
)