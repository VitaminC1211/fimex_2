
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import tab.cart.CartTab
import tab.home.HomeTab
import tab.profile.ProfileTab

@Composable
fun App() {

    MaterialTheme {
//        Navigator(Splash())

        TabNavigator(HomeTab) {
            Scaffold(
                bottomBar = {
                    BottomNavigation(
                        backgroundColor = Color.White
                    ) {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(CartTab)
                        TabNavigationItem(ProfileTab)
                    }
                }
            ) {
                CurrentTab()
            }
        }

    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
//        label = { Text(tab.options.title ?: "") },
        icon = {
            tab.options.icon?.let { icon ->
                Icon(
                    painter = icon,
                    contentDescription = tab.options.title ?: "",
                    tint = Color.Black
                )
            }
        }
    )
}

