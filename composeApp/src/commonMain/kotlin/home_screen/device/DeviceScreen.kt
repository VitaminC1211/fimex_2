package home_screen.device

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.seiko.imageloader.rememberImagePainter
import data.InnerImage
import data.InnerInfo
import home_screen.details.DetailScreen

import kotlinx.coroutines.flow.MutableStateFlow

data class DeviceScreen(
    val selectedService: MutableStateFlow<List<InnerImage?>> = MutableStateFlow(listOf()),
    val selectedPhoneIcon: MutableStateFlow<List<InnerInfo?>> = MutableStateFlow(listOf())
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        AppContent(navigator = navigator)
    }

    @Composable
    fun AppContent(navigator: Navigator?) {

        val scrollState = rememberLazyGridState()

        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color.White,
                    title = { Text("Select the Device you want") },
                    navigationIcon = {
                        IconButton(onClick = { navigator?.pop() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back Arrow icon"
                            )
                        }
                    }
                )
            }
        ) {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        state = scrollState,
                        contentPadding = PaddingValues(16.dp),
                        modifier = Modifier.padding(bottom = 50.dp)
                    ) {
                        item(span = { GridItemSpan(2) }) {
                            Column {
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                        items(
                            items = selectedService.value,
                            key = { selectedService -> selectedService?.id.toString() }
                        ) { selectedService ->
                            Card(
                                shape = RoundedCornerShape(30.dp),
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedPhoneIcon.value = selectedService?.innerInfo!!
                                        navigator?.push(
                                            DetailScreen(
                                                selectedPIcon = MutableStateFlow(
                                                    selectedPhoneIcon.value
                                                )
                                            )
                                        )
                                    },
                                elevation = 10.dp
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    val painter =
                                        rememberImagePainter(url = selectedService?.images.toString())
                                    Image(
                                        painter,
                                        modifier = Modifier.height(100.dp).padding(8.dp),
                                        contentDescription = selectedService?.images.toString()
                                    )
                                    Text(
                                        selectedService?.description.toString(),
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.padding(8.dp)
                                            .heightIn(min = 20.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}