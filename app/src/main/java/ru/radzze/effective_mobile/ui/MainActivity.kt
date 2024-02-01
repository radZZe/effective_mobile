package ru.radzze.effective_mobile.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import ru.radzze.effective_mobile.data.UserStore
import ru.radzze.effective_mobile.ui.auth.AuthScreen
import ru.radzze.effective_mobile.ui.graphs.RootNavigationGraph
import ru.radzze.effective_mobile.ui.theme.Effective_mobileTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var userStore:UserStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var isAuth = false
        runBlocking {
            isAuth = userStore.getPrefBool(UserStore.isAuthenticated).first()
        }
        setContent {
            Effective_mobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val test =5
                    RootNavigationGraph(navController = rememberNavController(),isAuth)
                }
            }
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
    Effective_mobileTheme {
        Greeting("Android")
    }
}