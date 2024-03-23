package tab.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object  ProfileTab : Tab {
    lateinit var namevalue: String
    @Composable
    override fun Content() {
        Box(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Round Avatar in the middle of the top of the screen
                Box(
                    modifier = Modifier.padding(top = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier.size(80.dp).background(Color.Gray, shape = CircleShape)
                    ) {
                        // You can place your avatar image here
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                // Letters underneath the avatar
                Text("Hi: $namevalue", style = MaterialTheme.typography.h6)

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color.LightGray
                ) {
                    Text("Top section")
                }

                Spacer(modifier = Modifier.height(16.dp))
                // Card section divided into right and left sections
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Left section
                    Card(
                        modifier = Modifier.weight(1f),
                        backgroundColor = Color.Blue
                    ) {
                        Text("Left Section")
                    }

                    // Right section
                    Card(
                        modifier = Modifier.weight(1f),
                        backgroundColor = Color.Green
                    ) {
                        Text("Right Section")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Card with list-type content
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color.LightGray
                ) {
                    Column {
                        Text("List Item 1")
                        Text("List Item 2")
                        Text("List Item 3")
                        // Add more list items as needed
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color.LightGray
                ) {
                    Column {
                        Text("List Item 1")
                        Text("List Item 2")
                        Text("List Item 3")
                        // Add more list items as needed
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }

    override val options: TabOptions
        @Composable
        get() {

            val icon = rememberVectorPainter(Icons.Default.Person)

            return remember {
                TabOptions(
                    index = 0u,
                    title = "Profile",
                    icon = icon
                )
            }
        }

}