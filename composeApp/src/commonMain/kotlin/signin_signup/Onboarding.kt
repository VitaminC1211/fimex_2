package signin_signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.seiko.imageloader.rememberImagePainter

class Onboarding : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter // Align content to the bottom center
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberImagePainter("https://www.ship2me.co.uk/img/content/main-pic.png"),
                    contentDescription = null,
//                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Fimex_clone")
                    Spacer(modifier = Modifier.weight(1f)) // Add spacer to push buttons to the bottom
                    Text("Purchase with ease!")
                    AnimatedVisibility(visible = true) {
                        Button(
                            onClick = { navigator?.push(Sign_in()) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Login")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    AnimatedVisibility(visible = true) {
                        Button(
                            onClick = { navigator?.push(Sign_up()) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Register")
                        }
                    }
                }
            }
        }
    }
}