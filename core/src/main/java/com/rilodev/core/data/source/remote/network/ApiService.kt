package com.rilodev.core.data.source.remote.network

import com.rilodev.core.data.source.remote.response.base.BaseResponse
import com.rilodev.core.data.source.remote.response.DataResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getData(): BaseResponse<List<DataResponse>>
}