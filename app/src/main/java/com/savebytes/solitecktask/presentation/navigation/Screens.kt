package com.savebytes.solitecktask.presentation.navigation

import com.savebytes.solitecktask.data.models.MovieItem
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screens {

    @Serializable
    data object HomeScreen : Screens

    @Serializable
    data class DetailScreen(
        val id : String,
        val title : String,
        val rating : Double,
        val year : Int,
        val description : String,
        val imageUrl : String,
        val imdbUrl : String,
    ) : Screens

    @Serializable
    data class WebViewScreen(
        val url: String,
        val title: String = "Web View"
    ) : Screens

}