package com.rilodev.core.domain.usecase

import com.rilodev.core.data.repositories.Resource
import com.rilodev.core.domain.model.DataModel
import com.rilodev.core.domain.repositories.IAppRepositories
import kotlinx.coroutines.flow.Flow

class GetDataUseCase(private val repositories: IAppRepositories) {
    operator fun invoke(): Flow<Resource<List<DataModel>>> {
        return repositories.getData()
    }
}