package home_screen.home

import HomeViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.seiko.imageloader.rememberImagePainter
import data.InnerImage
import home_screen.device.DeviceScreen
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        AppContent(homeViewModel = HomeViewModel(), navigator = navigator)
    }

    @Composable
    fun AppContent(homeViewModel: HomeViewModel, navigator: Navigator?) {

        val services = homeViewModel.service.collectAsState()
        val selectedServiceImages: MutableStateFlow<List<InnerImage?>> = MutableStateFlow(listOf())

        BoxWithConstraints {

            val images = listOf(
                "https://innovativezoneindia.com/wp-content/uploads/2021/12/Xiaomi-12-Series.jpg",
                "https://image.khaleejtimes.com/?uuid=5d46f0c2-9a46-5329-9f29-f66785c7615f&function=cropresize&type=preview&source=false&q=75&crop_w=0.99999&crop_h=0.99956&x=0&y=0&width=1500&height=844",
                "https://www.trustedreviews.com/wp-content/uploads/sites/54/2024/02/Honor-Magic-6-Pro-review-3-1024x580.jpg",
                "https://www.trustedreviews.com/wp-content/uploads/sites/54/2021/11/best-camera-phone-920x613.jpg"
            )

            var cols = 4
            val scrollState = rememberLazyGridState()

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                var searchText by remember { mutableStateOf(TextFieldValue()) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 20.dp
                ){
                    TextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = { Text("Search...") },
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = Color.Gray, // Change cursor line color to black
                            focusedIndicatorColor = Color.Transparent, // Remove focused indicator
                            unfocusedIndicatorColor = Color.Transparent,
                            backgroundColor = Color.White// Remove unfocused indicator
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
                    )
                }


                LazyRow(
                    state = rememberLazyListState(),
                    modifier = Modifier.fillMaxWidth().height(120.dp)
                ) {
                    itemsIndexed(images) { index, image ->
                        // You can display each image here
                        // For example:
                        val roundedCornerShape = RoundedCornerShape(15.dp)
                        Image(
                            painter = rememberImagePainter(image),
                            contentDescription = null, // Add appropriate content description
                            modifier = Modifier.fillMaxWidth().padding(10.dp).clip(shape = roundedCornerShape),
                        )
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(cols),
                    state = scrollState,
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.fillMaxSize().padding(bottom = 50.dp)
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
                                    selectedServiceImages.value = service.innerImage!!
                                    navigator?.push(DeviceScreen(selectedService = MutableStateFlow(selectedServiceImages.value)))
                                },
                            elevation = 10.dp,

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
                            }
                        }
                    }
                }
            }
        }
    }
}

