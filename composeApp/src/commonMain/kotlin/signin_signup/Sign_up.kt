package signin_signup

import HomeRepository
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import data.Register
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class Sign_up : Screen {

    @Composable
    override fun Content() {
        var flag by remember { mutableStateOf(false) }
        var error_flag by remember { mutableStateOf(false) }

        var emailError by remember { mutableStateOf("") }
        var passwordError by remember { mutableStateOf("") }
        var nameError by remember { mutableStateOf("") }
        var confirmPassError by remember { mutableStateOf("") }

        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confpassword by remember { mutableStateOf("") }

        Box {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Register here",
                    style = TextStyle(
                        fontSize = 20.sp
                    )
                )
                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    value = name,
                    onValueChange = {
                        name = it
                        if (it.isEmpty()) {
                            nameError = "Name cannot be empty"
                        } else {
                            nameError = ""
                        }
                    },
                    label = { Text("Name") },
                    isError = name.isEmpty(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        textColor = Color.Gray,
                        focusedBorderColor = Color.hsl(216F, 1F, 0.5F),
                        focusedLabelColor = Color.hsl(216F, 1F, 0.5F),
                        cursorColor = Color.hsl(216F, 1F, 0.5F)
                    )
                )
                if (nameError.isNotEmpty()) {
                    Text(text = nameError, color = Color.Red)
                }
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        textColor = Color.Gray,
                        focusedBorderColor = Color.hsl(216F, 1F, 0.5F),
                        focusedLabelColor = Color.hsl(216F, 1F, 0.5F),
                        cursorColor = Color.hsl(216F, 1F, 0.5F)
                    )
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        textColor = Color.Gray,
                        focusedBorderColor = Color.hsl(216F, 1F, 0.5F),
                        focusedLabelColor = Color.hsl(216F, 1F, 0.5F),
                        cursorColor = Color.hsl(216F, 1F, 0.5F)
                    )
                )
                if (passwordError.isNotEmpty()) {
                    Text(text = passwordError, color = Color.Red)
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = confpassword,
                    onValueChange = {
                        confpassword = it
                        if (it.isEmpty()) {
                            confirmPassError = "Confirm password cannot be empty"
                        } else {
                            confirmPassError = ""
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        textColor = Color.Gray,
                        focusedBorderColor = Color.hsl(216F, 1F, 0.5F),
                        focusedLabelColor = Color.hsl(216F, 1F, 0.5F),
                        cursorColor = Color.hsl(216F, 1F, 0.5F)
                    ),
                    label = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                if (confirmPassError.isNotEmpty()) {
                    Text(text = confirmPassError, color = Color.Red)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color.hsl(216F, 1F, 0.5F),
                        contentColor = Color.White
                    ),
                    onClick = {
                        if (name.isEmpty()) {
                            nameError = "Name cannot be empty"
                        }
                        if (email.isEmpty()) {
                            emailError = "Email cannot be empty"
                        }
                        if (password.isEmpty()) {
                            passwordError = "Password cannot be empty"
                        }
                        if (confpassword.isEmpty()) {
                            confirmPassError = "Confirm password cannot be empty"
                        }
                        if (password.isNotEmpty() && email.isNotEmpty() && name.isNotEmpty() && confpassword.isNotEmpty()) {
                            if(password != confpassword){
                                confirmPassError = "Confirm password doesn't match"
                            } else {
                                val register = Register(name, email, password)
                                val homeRepository = HomeRepository()
                                CoroutineScope(Dispatchers.IO).launch {
                                    val userResponse =
                                        homeRepository.sendRegisterDataToBackend(register)
                                    if (userResponse == "1") {
                                        flag = true
                                    } else {
                                        error_flag = true
                                    }
                                }
                            }
                        }
                    }
                ) {
                    Text("Register")
                }
            }
        }

        if (flag) {
            val navigator = LocalNavigator.current
            navigator?.push(Sign_in())
        }

        if (error_flag) {
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
                    Text("This user already registered", color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(onClick = {
                            error_flag = false
                            navigator?.push(Sign_in())
                        }) {
                            Text("Go to login?")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = {
                            error_flag = false
                        }) {
                            Text("Close")
                        }
                    }
                }
            }
        }
    }
}