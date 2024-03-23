package signin_signup

import HomeRepository
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import data.Login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import tab.cart.CartTab
import tab.home.HomeTab
import tab.profile.ProfileTab

class Sign_in() : Screen{

    @Composable
    override fun Content() {

        var flag by remember { mutableStateOf(false) }
        var loginWrongPass by remember { mutableStateOf(false) }
        var didntRegister by remember { mutableStateOf(false) }

        var emailError by remember { mutableStateOf("") }
        var passwordError by remember { mutableStateOf("") }

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Box {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                        if (it.isEmpty()) {
                            emailError = "Email cannot be empty"
                        } else {
                            emailError = ""
                        }
                    },
                    label = { Text("Email") },
                    isError = email.isEmpty()
                )
                if (emailError.isNotEmpty()) {
                    Text(text = emailError, color = Color.Red)
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                        if (it.isEmpty()) {
                            passwordError = "Password cannot be empty"
                        } else {
                            passwordError = ""
                        }
                    },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = password.isEmpty()
                )
                if (passwordError.isNotEmpty()) {
                    Text(text = passwordError, color = Color.Red)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (email.isEmpty()) {
                            emailError = "Email cannot be empty"
                        }
                        if (password.isEmpty()) {
                            passwordError = "Password cannot be empty"
                        }
                        if (email.isNotEmpty() && password.isNotEmpty()) {
                            val login = Login(email, password)
                            val homeRepository = HomeRepository()
                            CoroutineScope(Dispatchers.IO).launch {
                                val loginResponse = homeRepository.sendLoginDataToBackend(login)

                                if (loginResponse == "1") {
                                    loginWrongPass = true
                                }
                                if (loginResponse == "0") {
                                    didntRegister = true
                                }
                                if (loginResponse != "1" && loginResponse != "0"){
                                    flag =true
                                    ProfileTab.namevalue = loginResponse
                                }
                            }
                        }
                    }
                ) {
                    Text("Login")
                }
            }
        }

        if (flag) {
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

        if(loginWrongPass){
            passwordError = "Wrong password!"
        }
        if(didntRegister){
            val navigator = LocalNavigator.current
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.9f))
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                ) {
                    Text("This user didn't registered", color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.End
                    ){
                        Button(onClick = {
                            didntRegister = false
                            navigator?.push(Sign_up())
                        }) {
                            Text("Go to Register?")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = {
                            didntRegister = false
                        }) {
                            Text("Close")
                        }
                    }
                }
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