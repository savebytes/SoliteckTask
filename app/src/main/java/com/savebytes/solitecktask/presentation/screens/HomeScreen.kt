package com.savebytes.solitecktask.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.savebytes.solitecktask.presentation.states.HomeState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import com.savebytes.solitecktask.data.models.MovieItem
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.lerp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    navigateToDetail : (MovieItem) -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Soliteck Task",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = TextStyle(
                            letterSpacing = 0.5.sp
                        ),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1a2a4a)
                )
            )
        },
        containerColor = Color(0xFF0a0f1f)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFF0a0f1f))
        ) {
            // Loading State
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp),
                        color = Color(0xFF1976D2),
                        strokeWidth = 4.dp
                    )
                }
            } else {
                // Content when not loading
                LazyColumn {

                    item {
                        Spacer(modifier = Modifier.height(25.dp))
                        RowSection(
                            title = "Trending",
                            movieItems = state.movieHomeData.trending,
                            navigateToDetail = navigateToDetail
                        )

                    }
                    item {
                        Spacer(modifier = Modifier.height(25.dp))
                        StaggeredTagGrid(genresItem = state.movieHomeData.genres)
                    }

                    item {
                        Spacer(modifier = Modifier.height(25.dp))
                        RowSection(
                            title = "Popular Movies",
                            movieItems = state.movieHomeData.popularMovies,
                            navigateToDetail = navigateToDetail
                        )

                    }

                    item {
                        Spacer(modifier = Modifier.height(25.dp))
                        RowSection(
                            title = "Popular Tv Series",
                            movieItems = state.movieHomeData.popularTvSeries,
                            navigateToDetail = navigateToDetail
                        )

                    }

                    item {
                        Spacer(modifier = Modifier.height(25.dp))

                        RowSection(
                            title = "Top Rated Movies",
                            movieItems = state.movieHomeData.topRatedMovies,
                            navigateToDetail = navigateToDetail
                        )

                    }

                    item {
                        Spacer(modifier = Modifier.height(25.dp))

                        RowSection(
                            title = "Top Rated Tv Series",
                            movieItems = state.movieHomeData.topRatedTvSeries,
                            navigateToDetail = navigateToDetail
                        )

                    }

                }
            }
        }
    }
}

@Composable
fun RowSection(
    title: String,
    movieItems: List<MovieItem>,
    navigateToDetail: (MovieItem) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0a0f1f))
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(movieItems) { item ->
                CardWithImage(
                    imageUrl = item.primaryImage,
                    title = item.primaryTitle,
                    navigateToDetail = {
                        navigateToDetail(item)
                    }
                )
            }
        }
    }

}

@Composable
fun CardWithImage(
    imageUrl: String = "https://m.media-amazon.com/images/M/MV5BYmQ4NWVjN2EtN2U3NC00YzZhLWIzYmMtNjQ0ZjNiN2MzNDI0XkEyXkFqcGc@.jpg://via.placeholder.com/280x320",
    title: String = "Ankit",
    navigateToDetail : () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(190.dp, 230.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color.White, shape = RoundedCornerShape(12.dp))
            .background(Color(0xFF1a2a4a)).clickable{
                navigateToDetail()
            }
    ) {
        // Full Image
        AsyncImage(
            model = imageUrl,
            contentDescription = title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Gradient Overlay
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0xCC000000)
                        ),
                        startY = 0f,
                        endY = 140f
                    )
                )
        )

        // Text on top of image
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // Info Button
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .size(22.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "i",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1a2a4a)
            )
        }
    }
}


@Composable
fun StaggeredTagGrid(
    genresItem: List<String>
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Genres",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(Color(0xFF0a0f1f))
                .padding(horizontal = 16.dp),
            horizontalItemSpacing = 8.dp,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(genresItem) { it ->

                TagItem(text = it)

            }
        }
    }
}


@Composable
fun TagItem(text: String) {
    Box(
        modifier = Modifier
            .border(
                width = 1.5.dp,
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = Color(0xFF1F2937),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 14.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}