package com.savebytes.solitecktask.data.models

import kotlinx.serialization.Serializable

@Serializable
data class MovieItem(
    val _id: String = "",
    val averageRating: Double = 0.0,
    val description: String = "",
    val id: String = "",
    val primaryImage: String = "",
    val primaryTitle: String = "",
    val runtimeMinutes: Int = -1,
    val startYear: Int = -1,
    val url: String = ""
)
