package dev.hmh.jpc_usecase

import android.text.BoringLayout
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.navArgument
import dev.hmh.ammmvvm.data.model.LoginResponse
import dev.hmh.ammmvvm.mvvm.AddUserViewModel
import dev.hmh.ammmvvm.util.ApiResponse
import dev.hmh.jpc_usecase.ui.theme.Purple500
import kotlinx.coroutines.launch

@Composable
fun AddUser(
    viewModel: AddUserViewModel = hiltViewModel()
) {
    var status: Boolean
    val context = LocalContext.current
    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Add User",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                OutlinedTextField(
                    value = nameValue.value,
                    onValueChange = {
                        nameValue.value = it
                    },
                    label = {
                        Text(text = "User Name")
                    },
                    placeholder = {
                        Text(text = "Enter User Name")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = emailValue.value,
                    onValueChange = {
                        emailValue.value = it
                    },
                    label = {
                        Text(text = "Email")
                    },
                    placeholder = {
                        Text(text = "Enter Email")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = passwordValue.value,
                    onValueChange = {
                        passwordValue.value = it
                    },
                    label = {
                        Text(text = "Password")
                    },
                    placeholder = {
                        Text(text = "Enter Password")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.password_eye),
                                contentDescription = "Password Eye",
                                tint = if (passwordVisibility.value) Purple500 else Color.Gray
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
                )
                status = false
                Button(modifier = Modifier.fillMaxWidth(0.8f),
                    onClick = {
                        status = true
                        viewModel.addUser(
                            userName = nameValue.value,
                            email = emailValue.value,
                            password = passwordValue.value
                        )
                        val response = viewModel.response.value
                        when (response) {

                            is ApiResponse.Error -> {
                                status = false
                                Toast.makeText(context, "${response.message}", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            is ApiResponse.Loading -> {
                                status = true
                            }
                            is ApiResponse.Success -> {
                                status = false
                                response.data?.let { loginResponse ->
                                    if (loginResponse?.error == "200") {
                                        print(loginResponse.message)
                                        Log.d("add", "AddUser: " + loginResponse.message)

                                    } else {
                                        Log.d("add", "AddUser: " + loginResponse.message)

                                    }


                                }
                            }

                        }


                    }
                ) {


                    Column(
                        modifier = Modifier.size(20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (!status) {
                            CircularProgressIndicator(color = Color.White)
                        } else {
                            Text(text = "Add User")
                        }

                    }
                }


            }
        }
    }

}

@Composable
fun ProgressBarDemo(
    status: Boolean = false
) {
    Column(
        modifier = Modifier.size(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!status) {
            CircularProgressIndicator(color = Color.White)
        }

    }
}

