package com.savebytes.solitecktask.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.savebytes.solitecktask.R
import com.savebytes.solitecktask.data.models.MovieItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    movieItem: MovieItem,
    onBackClick: () -> Unit,
    openWeb: (String) -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = movieItem.id,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                    )
                },
                navigationIcon = {
                    Row {
                        Spacer(modifier = Modifier.width(15.dp))
                        Image(
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = "",
                            modifier = Modifier
                                .size(28.dp)
                                .clickable { onBackClick() }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }

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

            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
            ) {
                // Full Image
                AsyncImage(
                    model = movieItem.primaryImage,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .clip(RoundedCornerShape(12.dp))
                        .align(Alignment.Center),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = "Star",
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = movieItem.averageRating.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                }

                Text(
                    text = movieItem.primaryTitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 5.dp)
                )

                Text(
                    text = movieItem.startYear.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = movieItem.description,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .clickable{ openWeb(movieItem.url) }
            ) {
                Text(
                    text = "Open IMDB",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

