package com.rilodev.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PersonResponse(
    @SerializedName("id") val id: String,
    @SerializedName("firstName") val firstName: String?,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("dob") var dob: String?
)