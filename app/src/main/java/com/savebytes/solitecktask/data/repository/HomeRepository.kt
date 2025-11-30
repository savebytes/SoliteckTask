package com.savebytes.solitecktask.data.repository

import com.savebytes.solitecktask.data.api.AppApiService
import com.savebytes.solitecktask.data.models.MovieResponse
import com.savebytes.solitecktask.domain.repo.HomeRepo
import com.savebytes.solitecktask.utils.Resource
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiService: AppApiService
) : HomeRepo{
    override suspend fun getMovies(): Resource<MovieResponse> {
        return try {
            val response = apiService.getMovies()
            Resource.success(response)
        } catch (e: Exception) {
            Resource.error(e.localizedMessage ?: "API Connection Failed", null)
        }
    }
}