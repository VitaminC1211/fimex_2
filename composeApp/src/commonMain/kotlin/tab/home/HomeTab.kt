package tab.home

import HomeViewModel
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.seiko.imageloader.rememberImagePainter

object HomeTab : Tab {
    @Composable
    override fun Content() {

        AppContent(homeViewModel = HomeViewModel())
//        Navigator(HomeScreen()){navigator ->
//            SlideTransition(navigator)
//        }

//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            Text("Home Tab")
//        }
    }

    @Composable
    fun AppContent(homeViewModel: HomeViewModel) {
        val products = homeViewModel.products.collectAsState()
        BoxWithConstraints {
            val scope = this
            val maxWith = scope.maxWidth

            var cols = 2
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
//                            SearchBar(
//                                modifier = Modifier.fillMaxWidth(),
//                                query = "",
//                                active = false,
//                                onActiveChange = {},
//                                onQueryChange = {},
//                                onSearch = {},
//                                leadingIcon = {
//                                    Icon(
//                                        imageVector = Icons.Default.Search,
//                                        contentDescription = "Search"
//                                    )
//                                },
//                                placeholder = { Text("Search here") }
//                            ) {}
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                    items(
                        items = products.value,
                        key = { product -> product.id.toString() }) { product ->
                        Card(
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier.padding(8.dp).fillMaxWidth(),
                            elevation = 2.dp
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val painter = rememberImagePainter(url = product.image.toString())
                                Image(
                                    painter,
                                    modifier = Modifier.height(130.dp).padding(8.dp),
                                    contentDescription = product.title
                                )
                                Text(
                                    product.title.toString(),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(16.dp).heightIn(min = 40.dp)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
//                                Box(
//                                    modifier = Modifier.fillMaxWidth(),
//                                    contentAligment = Alignment.CenterStart
//                                ){
//
//                                }
                                Text(
                                    "${product.price.toString()} $",
                                    textAlign = TextAlign.Start,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(16.dp).heightIn(min = 40.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private @Composable
    fun Icon(imageVector: Any, contentDescription: String) {

    }

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 0u,
                title = "Home",
                icon = null
            )
        }

}