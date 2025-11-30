package com.savebytes.solitecktask.data.api

import com.savebytes.solitecktask.data.models.MovieResponse
import retrofit2.http.GET

interface AppApiService {

    @GET("/film/home")
    suspend fun getMovies() : MovieResponse

}