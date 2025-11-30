package com.savebytes.solitecktask.data.models

data class Data(
    val genres: List<String> = emptyList(),
    val popularMovies: List<MovieItem> = emptyList(),
    val popularTvSeries: List<MovieItem> = emptyList(),
    val topRatedMovies: List<MovieItem> = emptyList(),
    val topRatedTvSeries: List<MovieItem> = emptyList(),
    val trending: List<MovieItem> = emptyList()
)