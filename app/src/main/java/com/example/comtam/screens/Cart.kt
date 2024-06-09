package com.example.comtam.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.comtam.ShareValue
import com.example.comtam.models.User

class Cart {
    @Composable
    fun Container (gotoScreen : (String) -> Unit,
                   shareValue : ShareValue,
                   writeShare:(user: User) -> Unit
    ) {
        Button(onClick = {
            shareValue.user= User(null,null,null,null)
            writeShare(User(null,null,null,null,))
         }) {
            Text(text = "Đăng xuất")
        }
    }
}