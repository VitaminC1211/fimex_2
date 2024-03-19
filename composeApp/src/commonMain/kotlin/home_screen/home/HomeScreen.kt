package home_screen.home

import HomeViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.seiko.imageloader.rememberImagePainter
import data.InnerImage
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        AppContent(homeViewModel = HomeViewModel(), navigator = navigator)
    }

    @Composable
    fun AppContent(homeViewModel: HomeViewModel, navigator: Navigator?) {

        var showDetailScreen by remember { mutableStateOf(false) }
        val services = homeViewModel.service.collectAsState()
        val selectedServiceImages: MutableStateFlow<List<InnerImage?>> = MutableStateFlow(listOf())

        BoxWithConstraints {
            val scope = this
            val maxWith = scope.maxWidth

            var cols = 4
            var detail_cols = 2
            var modifier = Modifier.fillMaxWidth()
            if (maxWith > 840.dp) {
                cols = 3
                modifier = Modifier.widthIn(max = 1080.dp)
            }

            val scrollState = rememberLazyGridState()

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(cols),
                    state = scrollState,
                    contentPadding = PaddingValues(16.dp)
                ) {
                    item(span = { GridItemSpan(cols) }) {
                        Column {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                    items(
                        items = services.value,
                        key = { service -> service.id.toString() }) { service ->
                        Card(
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .clickable {
//                                    val navigator = navigator
//                                    navigator?.push(DetailScreen(productInfo = product.id.toString()))
                                    selectedServiceImages.value = service.innerImage!!
                                    showDetailScreen = !showDetailScreen
                                },
                            elevation = 2.dp
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val painter = rememberImagePainter(url = service.image.toString())
                                Image(
                                    painter,
                                    modifier = Modifier.height(60.dp).padding(8.dp),
                                    contentDescription = service.image.toString()
                                )
//                                Text(
//                                    product.title.toString(),
//                                    maxLines = 2,
//                                    overflow = TextOverflow.Ellipsis,
//                                    modifier = Modifier.padding(16.dp).heightIn(min = 40.dp)
//                                )
//                                Spacer(modifier = Modifier.height(5.dp))
//
//                                Text(
//                                    "${product.price.toString()} $",
//                                    textAlign = TextAlign.Start,
//                                    maxLines = 2,
//                                    overflow = TextOverflow.Ellipsis,
//                                    modifier = Modifier.padding(16.dp).heightIn(min = 40.dp)
//                                )

                            }
                        }
                    }
                }
                if (showDetailScreen) {
                    AnimatedVisibility(
                        visible = showDetailScreen && selectedServiceImages != null,
                        enter = slideInVertically(initialOffsetY = { it }),
                        exit = slideOutVertically(targetOffsetY = { it })
                    ) {
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .fillMaxHeight()
//                        ) {
//                            Button(onClick = {
//                                navigator?.push(DetailScreen(productInfo = selectedServiceImages.toString()))
//                            }) {
//                                Text("Detail Screen{$selectedServiceImages}")
//                            }
//                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(detail_cols),
                                state = scrollState,
                                contentPadding = PaddingValues(16.dp)
                            ) {
                                item(span = { GridItemSpan(detail_cols) }) {
                                    Column {
                                        Spacer(modifier = Modifier.height(16.dp))
                                    }
                                }
                                items(
                                    items = selectedServiceImages.value,
                                    key = { selectedService -> selectedService?.id.toString() }
                                ) { selectedService ->
                                    Card(
                                        shape = RoundedCornerShape(15.dp),
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth()
                                            .clickable {
                                            },
                                        elevation = 2.dp
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            val painter =
                                                rememberImagePainter(url = selectedService?.image.toString())
                                            Image(
                                                painter,
                                                modifier = Modifier.height(200.dp).padding(8.dp),
                                                contentDescription = selectedService?.image.toString()
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
    }
}

