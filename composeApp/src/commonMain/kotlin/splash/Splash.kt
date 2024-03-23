package splash

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import kotlinx.coroutines.delay
import signin_signup.Onboarding

class Splash : Screen{

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        LaunchedEffect(key1 = true){
            delay(3000)

            navigator?.push(Onboarding())
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(if(isSystemInDarkTheme()) Color.DarkGray else Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
//           LoaderAnimation(
//               modifier = Modifier.size(400.dp)
//               ,anim = R.
//           )
            Text("Welcome to Fimex_clone")
        }
    }
    private @Composable
    fun LoaderAnimation(modifier: Modifier, anim: Any?) {

    }
}