package com.example.comtam.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.asm.ApiClient
import com.example.comtam.R
import com.example.comtam.ShareValue
import com.example.comtam.models.User
import com.example.comtam.ui.theme.Orange
import com.example.comtam.ui.theme.PurpleGrey80
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register {

    @Composable
    fun Container(gotoScreen: (String) -> Unit, shareValue: ShareValue) {
        Body(gotoScreen = { gotoScreen(it) })
    }

    @Composable
    fun Body(gotoScreen: (String) -> Unit) {
        val already = "Already have an account?"
        var name by rememberSaveable { mutableStateOf("") }
        var email by rememberSaveable {
            mutableStateOf("")
        }
        var password by rememberSaveable {
            mutableStateOf("")
        }
        var passwordVisible by remember { mutableStateOf(false) }

        val context = LocalContext.current

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Create \nAccount",
                    modifier = Modifier.clickable { gotoScreen("login") },
                    fontSize = 40.sp,
                    lineHeight = 30.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = buildAnnotatedString {
                        append("Enter your Email and Password to\nsign up. ")
                        withStyle(style = SpanStyle(color = Orange)) {
                            append(already)
                        }
                    },
                    fontSize = 15.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .clickable { gotoScreen("login") }
                )
                Spacer(modifier = Modifier.height(30.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Image",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(150.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))

                TextField(
                    value = name,
                    onValueChange = { newText ->
                        name = newText.trim()
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        focusedLabelColor = Color.White
                    ),
                    label = { Text("Your name", color = PurpleGrey80) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(30))
                        .border(1.dp, Color.Gray, RoundedCornerShape(30)),
                    shape = RoundedCornerShape(30),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = "User",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = email,
                    onValueChange = { newText ->
                        email = newText.trim()
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        focusedLabelColor = Color.White
                    ),
                    label = { Text("Email", color = PurpleGrey80) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(30))
                        .border(1.dp, Color.Gray, RoundedCornerShape(30)),
                    shape = RoundedCornerShape(30),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.sms),
                            contentDescription = "User",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = password,
                    onValueChange = { newText ->
                        password = newText.trim()
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        focusedLabelColor = Color.Black
                    ),
                    label = { Text("Password", color = PurpleGrey80) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(30))
                        .border(1.dp, Color.Gray, RoundedCornerShape(30)),
                    shape = RoundedCornerShape(30),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        Image(
                            painter = painterResource(
                                id = if (passwordVisible) R.drawable.key else R.drawable.key
                            ),
                            contentDescription = "Toggle password visibility",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    passwordVisible = !passwordVisible
                                }
                        )
                    }
                )
                Spacer(modifier = Modifier.height(46.dp))
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(
                            Orange,
                            shape = MaterialTheme.shapes.large
                        )
                        .padding(horizontal = 40.dp),
                    onClick = {
                        if (name.isBlank() || email.isBlank() || password.isBlank()) {
                            Toast.makeText(context, "Vui lòng điền vào tất cả các ô", Toast.LENGTH_SHORT).show()
                        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            Toast.makeText(context, "Vui lòng nhập địa chỉ email hợp lệ", Toast.LENGTH_SHORT).show()
                        } else {
                            val body = User(id = null, name = name, email = email, password = password)
                            val call = ApiClient.apiService.registerAPI(body)
                            call.enqueue(object : Callback<User> {
                                override fun onResponse(call: Call<User>, response: Response<User>) {
                                    if (response.isSuccessful) {
                                        Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                                        gotoScreen("login")
                                    } else {
                                        Toast.makeText(context, "Đăng ký thất bại: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<User>, t: Throwable) {
                                    Toast.makeText(context, "Đăng ký thất bại: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                    }) {
                    Text(
                        text = "Sign Up",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
