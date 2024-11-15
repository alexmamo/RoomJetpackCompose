package ro.alexmamo.roomjetpackcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ro.alexmamo.roomjetpackcompose.navigation.NavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavGraph(
                navController = rememberNavController()
            )
        }
    }
}
//How to test navigation compose android
//https://stackoverflow.com/questions/75644786/jetpack-compose
//https://developer.android.com/codelabs/basic-android-kotlin-compose-test-cupcake#4