package com.rilodev.core.domain.repositories

import com.rilodev.core.data.repositories.Resource
import com.rilodev.core.domain.model.PersonModel
import kotlinx.coroutines.flow.Flow

interface IAppRepositories {
    fun getData(): Flow<Resource<List<PersonModel>>>
}