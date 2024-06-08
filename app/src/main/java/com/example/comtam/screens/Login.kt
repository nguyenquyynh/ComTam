package com.example.comtam.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comtam.R
import com.example.comtam.ShareValue
import com.example.comtam.ui.theme.Orange
import com.example.comtam.ui.theme.TextGray
import com.example.comtam.ui.theme.WhiteTr

class Login {
    @Composable
    fun Container(
        gotoScreen: (String) -> Unit,
        shareValue: ShareValue
    ) {

        var Email by remember {
            mutableStateOf("")
        }
        var Password by remember {
            mutableStateOf("")
        }
        Box(
            modifier = Modifier.run {
                fillMaxSize()
                    .paint(
                        painter = painterResource(id = R.drawable.signin),
                        contentScale = ContentScale.FillBounds
                    )
            }
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ) {
                Column {

                    Text(
                        text = "Well hello",
                        color = Black,
                        fontSize = 36.sp,
                        modifier = Modifier.padding(top = 35.dp)
                    )
                    Text(
                        text = "there !",
                        color = Black,
                        fontSize = 36.sp,
                    )
                    Text(
                        text = "Enter your Email and Password to sign up Create new account.",
                        color = TextGray,
                        modifier = Modifier
                            .clickable { gotoScreen("register") }
                            .fillMaxWidth(0.8f)
                            .padding(bottom = 30.dp, top = 20.dp)
                    )



                    Box(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.padding(horizontal = 1.dp)
                        ) {
                            OutlinedTextField(
                                value = Email,
                                onValueChange = { Email = it },
                                label = { Text(text = "Email") },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                ),
                                shape = RoundedCornerShape(15.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                trailingIcon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.sms),
                                        contentDescription = "user",
                                        modifier = Modifier
                                            .height(20.dp)
                                            .width(20.dp)
                                    )
                                },
                            )

                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.padding(horizontal = 1.dp)
                        ) {
                            OutlinedTextField(
                                value = Password,
                                onValueChange = { Password = it },
                                label = { Text(text = "Password") },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                ),
                                shape = RoundedCornerShape(15.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                trailingIcon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.key),
                                        contentDescription = "user",
                                        modifier = Modifier
                                            .height(20.dp)
                                            .width(20.dp)
                                    )
                                },
                            )

                        }
                    }
                    TextButton(
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .align(Alignment.CenterHorizontally)
                            .width(250.dp)
                            .height(50.dp)
                            .background(
                                White,
                                shape = MaterialTheme.shapes.small
                            ),

                        onClick = {
                            gotoScreen("register")
                        }) {
                        Text(
                            text = "Forgot Password ? ",
                            color = Blue,
                            fontSize = 18.sp,
                        )
                    }

                    TextButton(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                Orange,
                                shape = MaterialTheme.shapes.small
                            ),
                        onClick = {
                            gotoScreen("navigation")
                        }) {
                        Text(
                            text = "Log in",
                            color = White,
                            fontSize = 18.sp,
                        )
                    }


                }
            }
        }
    }
}