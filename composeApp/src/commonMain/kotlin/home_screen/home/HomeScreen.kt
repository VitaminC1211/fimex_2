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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
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
        val selectedPhoneIcon: MutableStateFlow<List<InnerInfo?>> = MutableStateFlow(listOf())

        BoxWithConstraints {

            val images = listOf(
                "https://www.zdnet.com/a/img/resize/44ee30136face6dcb90fe8ed96dc180a3a41a878/2023/09/12/29be3e47-9f70-4dbe-aa37-58719088b48f/old-iphones-apple-store.jpg?auto=webp&width=1280",
                "https://imageio.forbes.com/specials-images/imageserve/6578eb5f8e31d8383c4f069c/hands-with-smartphones/960x0.jpg?format=jpg&width=1440",
                "https://www.zdnet.com/a/img/resize/cb913fdf4fb4a92b2baf2b50c2a93a1b20d3c142/2023/09/22/4117cf65-90cd-4d96-82f9-377ff2fe6198/iphone-15-optimized-charging-screen.jpg?auto=webp&width=1280",
                "https://www.zdnet.com/a/img/resize/631dfd408a6c443445138394a03d2ff72f75b585/2024/03/04/1bbf5bb9-bde9-49c2-81b9-9a59d5e487ca/dsc09973-2.jpg?auto=webp&width=1280"
            )


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

                var searchText by remember { mutableStateOf(TextFieldValue()) }

                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text("Search...") },
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Gray, // Change cursor line color to black
                        focusedIndicatorColor = Color.Transparent, // Remove focused indicator
                        unfocusedIndicatorColor = Color.Transparent // Remove unfocused indicator
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.Black // Set the color of the search icon to black
                        )
                    },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .height(50.dp)
                )

                LazyRow(
                    state = rememberLazyListState(),
                    modifier = Modifier.fillMaxWidth().height(150.dp)
                ) {
                    itemsIndexed(images) { index, image ->
                        // You can display each image here
                        // For example:
                        Image(
                            painter = rememberImagePainter(image),
                            contentDescription = null, // Add appropriate content description
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(cols),
                    state = scrollState,
                    contentPadding = PaddingValues(16.dp),
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
                                contentPadding = PaddingValues(16.dp),
                                modifier = Modifier.padding(bottom = 50.dp)
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
                                                selectedPhoneIcon.value = selectedService?.innerInfo!!
                                                navigator?.push(DetailScreen(selectedPIcon = MutableStateFlow(selectedPhoneIcon.value)))
                                            },
                                        elevation = 2.dp
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
    }
}

