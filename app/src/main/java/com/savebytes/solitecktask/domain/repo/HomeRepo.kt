package com.savebytes.solitecktask.domain.repo

import com.savebytes.solitecktask.data.models.MovieResponse
import com.savebytes.solitecktask.utils.Resource

interface HomeRepo {

    suspend fun getMovies(): Resource<MovieResponse>

}