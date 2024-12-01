package com.nevrmd.barkbark.data.source.remote

import com.nevrmd.barkbark.data.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://dogapi.dog/"

interface ApiRequest {
    @GET("api/v2/breeds")
    suspend fun getBreedsByPageNumber(
        @Query("page[number]") pageNumber: Int,
    ): ApiResponse
}