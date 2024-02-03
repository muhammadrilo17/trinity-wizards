package com.rilodev.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonModel (
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val dob: String
): Parcelable