package com.savebytes.solitecktask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.savebytes.solitecktask.presentation.navigation.AppNavigation
import com.savebytes.solitecktask.presentation.theme.SoliteckTaskTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    private val channel: Channel<(NavHostController) -> Unit> by lazy {
        Channel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navController = rememberNavController()
            HandleIntentInNavControllerLaunchedEffect(
                channel = channel, navHostController = navController
            )

            val currentBackStackEntry by navController.currentBackStackEntryAsState()

            SoliteckTaskTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    AppNavigation(
                        navController = navController,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}

@Composable
internal fun HandleIntentInNavControllerLaunchedEffect(
    channel: Channel<(NavHostController) -> Unit>,
    navHostController: NavHostController,
) {
    LaunchedEffect(key1 = Unit) {
        channel.receiveAsFlow().collectLatest {
            it.invoke(navHostController)
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SoliteckTaskTheme {
        Greeting("Android")
    }
}