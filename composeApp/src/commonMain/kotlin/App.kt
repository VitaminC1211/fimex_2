
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import signin_signup.Onboarding

@Composable
fun App() {

    MaterialTheme {
        Navigator(Onboarding())
    }
}

