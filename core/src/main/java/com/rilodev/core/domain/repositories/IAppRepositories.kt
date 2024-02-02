package com.rilodev.core.domain.repositories

import com.rilodev.core.data.repositories.Resource
import com.rilodev.core.domain.model.DataModel
import kotlinx.coroutines.flow.Flow

interface IAppRepositories {
    fun getData(): Flow<Resource<List<DataModel>>>
}