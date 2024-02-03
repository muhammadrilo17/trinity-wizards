package com.rilodev.core.helpers.mapper

import com.rilodev.core.data.source.remote.response.PersonResponse
import com.rilodev.core.domain.model.PersonModel

object PersonMapper {
    fun responseToModel(input: PersonResponse): PersonModel {
        return PersonModel(
            id = input.id,
            firstName = input.firstName ?: "",
            lastName = input.lastName ?: "",
            email = input.email ?: "",
            dob = input.dob ?: "",
        )
    }
}