package com.rilodev.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DataResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)