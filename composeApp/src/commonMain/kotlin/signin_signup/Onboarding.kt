package signin_signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    Text(
                        "FIMEX_clone",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.Red,
                            fontSize = 30.sp,
                            fontFamily = FontFamily.Serif
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f)) // Add spacer to push buttons to the bottom
                    Text(
                        "Purchase with ease!",
                        style = TextStyle(
                            fontSize = 20.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    AnimatedVisibility(visible = true) {
                        Button(
                            onClick = { navigator?.push(Sign_in()) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = Color.hsl(216F, 1F, 0.5F),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Login")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    AnimatedVisibility(visible = true) {
                        Button(
                            onClick = { navigator?.push(Sign_up()) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = Color.White,
                                contentColor = Color.hsl(216F, 1F, 0.5F),
                            ),
                            border = BorderStroke(3.dp, Color.Transparent)
                        ) {
                            Text("Register")
                        }
                    }
                }
            }
        }
    }
}