package com.rilodev.core.data.source.remote.network

import com.rilodev.core.data.source.remote.response.PersonResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getData(): List<PersonResponse>
}