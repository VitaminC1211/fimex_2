package home_screen.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

data class DetailScreen(val productInfo: String) : Screen {

    @Composable
    override fun Content() {
        var showDetailScreen by remember { mutableStateOf(false) }
        val navigator = LocalNavigator.current
        Scaffold(
            topBar = {
                TopAppBar (
                    title = { Text("Details") },
                    navigationIcon = {
                        IconButton(onClick = { navigator?.pop()}) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back Arrow icon"
                            )
                        }
                    }
                )
            }
        ){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { }){
                    Text("Detail Screen ($productInfo)")
                }
            }
        }
    }
}

