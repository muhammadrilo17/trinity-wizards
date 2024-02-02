package com.rilodev.core.helpers.mapper

import com.rilodev.core.data.source.local.entity.DataEntity
import com.rilodev.core.data.source.remote.response.DataResponse
import com.rilodev.core.domain.model.DataModel

object DataMapper {
    fun responseToModel(input: DataResponse): DataModel {
        return DataModel(
            id = input.id,
            name = input.name
        )
    }

    fun modelToEntity(input: DataModel): DataEntity {
        return DataEntity(
            id = input.id,
            name = input.name,
        )
    }
}