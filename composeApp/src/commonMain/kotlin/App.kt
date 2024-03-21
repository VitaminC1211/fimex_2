
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import splash.Splash

@Composable
fun App() {

    MaterialTheme {
        Navigator(Splash())
    }
}

