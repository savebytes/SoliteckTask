package com.savebytes.solitecktask.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.savebytes.solitecktask.presentation.screens.HomeScreen
import com.savebytes.solitecktask.presentation.viewmodels.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import androidx.compose.runtime.collectAsState
import androidx.navigation.toRoute
import com.savebytes.solitecktask.data.models.MovieItem
import com.savebytes.solitecktask.presentation.screens.DetailScreen
import com.savebytes.solitecktask.presentation.screens.WebViewScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    val scope = CoroutineScope(Dispatchers.Main)
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screens.HomeScreen
    ) {

        composable<Screens.HomeScreen> {

            val homeViewModel: HomeViewModel = hiltViewModel()
            val homeState = homeViewModel.homeState.collectAsState()

            HomeScreen(
                state = homeState.value,
                navigateToDetail = { it ->
                    navController.navigate(
                        Screens.DetailScreen(
                            id = it.id,
                            title = it.primaryTitle,
                            rating = it.averageRating,
                            year = it.startYear,
                            description = it.description,
                            imageUrl = it.primaryImage,
                            imdbUrl = it.url
                        )
                    )
                }
            )

        }

        composable<Screens.DetailScreen> { backStackEntry ->

            val details = backStackEntry.toRoute<Screens.DetailScreen>()
            val movieItem = MovieItem(
                id = details.id,
                _id = "",
                description = details.description,
                averageRating = details.rating,
                primaryTitle = details.title,
                primaryImage = details.imageUrl,
                url = details.imdbUrl,
                startYear = details.year,
                runtimeMinutes = -1,
            )
            DetailScreen(
                movieItem = movieItem,
                onBackClick = {
                    navController.popBackStack()
                },
                openWeb = { it ->
                    navController.navigate(Screens.WebViewScreen(it, movieItem.primaryTitle))
                }
            )
        }

        composable<Screens.WebViewScreen> { backStackEntry ->
            val webViewScreen = backStackEntry.toRoute<Screens.WebViewScreen>()
            WebViewScreen(
                url = webViewScreen.url,
                title = webViewScreen.title,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }


    }
}