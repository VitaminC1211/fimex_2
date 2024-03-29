package home_screen.details

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import data.CountryPrice
import data.InnerInfo
import data.PhoneInner
import kotlinx.coroutines.flow.MutableStateFlow

data class DetailScreen(
    val selectedPIcon: MutableStateFlow<List<InnerInfo?>> = MutableStateFlow(listOf()),
    val selectedPitem: MutableStateFlow<List<PhoneInner?>> = MutableStateFlow(listOf()),
    val countryList: MutableStateFlow<List<CountryPrice?>> = MutableStateFlow(listOf())

) : Screen {

    @Composable
    fun DetailScreen() {
        LazyColumn (
            modifier = Modifier.height(150.dp)
        ){
            items(50) { i ->
                Text("Row $i", Modifier.fillMaxWidth().padding(8.dp))
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {

        var cols = 1
        val scrollState = rememberLazyGridState()
        var showDetailScreen by remember { mutableStateOf(false) }
        var PriceListFlag by remember { mutableStateOf(false) }
        val navigator = LocalNavigator.current

        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color.White,
                    title = { Text("Choose a model") },
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(cols),
                state = scrollState,
                contentPadding = PaddingValues(16.dp)
            ) {
                item(span = { GridItemSpan(cols) }) {
                    Column {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }

                items(
                    items = selectedPIcon.value,
                    key = { selectedPicon -> selectedPicon?.id.toString() }) { selectedPicon ->
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),

                        elevation = 10.dp
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.clickable {
                                    selectedPitem.value = selectedPicon?.phoneInner!!
                                    showDetailScreen = !showDetailScreen
                                    println("XXXXXXXXXXXXXXXXX$showDetailScreen")
                                }.fillMaxWidth(),
                            ){
                                Text(
                                    selectedPicon?.text.toString(),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(10.dp).heightIn(min = 15.dp)
                                )
                            }
                            if(showDetailScreen){
                                DetailScreen()
                            }
                        }
                    }

                }
            }
            
            if (PriceListFlag) {
                ModalBottomSheetLayout(
                    // Bottom sheet content
                    sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded),
                    sheetShape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
                    modifier = Modifier.fillMaxSize().animateContentSize(),
                    sheetContent = {

                        // Content of the bottom sheet
                        LazyVerticalGrid(
                            modifier = Modifier.padding(bottom = 70.dp, top = 30.dp),
                            columns = GridCells.Fixed(1),
                            content = {
                                item(span = { GridItemSpan(1) }) {
                                    Column {
                                        Spacer(modifier = Modifier.height(16.dp))
                                    }
                                }
                                items(
                                    items = selectedPitem.value,
                                    key = { selecteditem -> selecteditem?.id.toString() }
                                ) { selecteditem ->
                                    countryList.value = selecteditem?.countryPrice!!
                                    Card(
                                        backgroundColor = Color.LightGray,
                                        shape = RoundedCornerShape(15.dp),
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        elevation = 2.dp
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                selecteditem?.phoneType.toString(),
                                                maxLines = 2,
                                                overflow = TextOverflow.Ellipsis,
                                                modifier = Modifier.padding(8.dp)
                                                    .heightIn(min = 20.dp)
                                            )
                                            countryList.value.forEach { country ->
                                                Card(
                                                    shape = RoundedCornerShape(15.dp),
                                                    modifier = Modifier
                                                        .padding(8.dp)
                                                        .fillMaxWidth(),
                                                    elevation = 2.dp
                                                ) {
                                                    Column(
                                                        verticalArrangement = Arrangement.Center,
                                                        horizontalAlignment = Alignment.CenterHorizontally,
                                                        modifier = Modifier.clickable {

                                                        }
                                                    ) {
                                                        Text(
                                                            country?.price.toString(),
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
                        )
                    }
                ) {

                }
            }
        }
    }
}

