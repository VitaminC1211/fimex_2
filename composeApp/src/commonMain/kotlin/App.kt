import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import tab.home.HomeTab
import androidx.compose.material.Scaffold
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import tab.cart.CartTab
import tab.profile.ProfileTab

@Composable
fun App() {
    MaterialTheme {
        TabNavigator(HomeTab){
            Scaffold(
                bottomBar = {
                    BottomNavigation {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(CartTab)
                        TabNavigationItem(ProfileTab)
                    }
                }
            ){
                CurrentTab()
            }
        }
//        Navigator(HomeScreen()) { navigator ->
//            SlideTransition(navigator)
//        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab){
    val tabNavigator = LocalTabNavigator.current
    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        label = { Text(tab.options.title) },
        icon = {}
    )
}