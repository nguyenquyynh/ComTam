package com.example.comtam

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comtam.models.Product
import com.example.comtam.models.User
import com.example.comtam.screens.Detail
import com.example.comtam.screens.Feedback
import com.example.comtam.screens.Login
import com.example.comtam.screens.Navigation
import com.example.comtam.screens.Register
import com.example.comtam.ui.theme.ComTamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComTamTheme {
                Body()
            }
        }
    }

    @Composable
    fun Body() {
        val loginScreen = Login()
        val registerScreen = Register()
        val navigationScreen = Navigation()
        val detailScreen = Detail()
        val feedbackScreen = Feedback()

        val shareValue: ShareValue = viewModel()
        val navController = rememberNavController()

        fun gotoScreen(screen: String) {
            navController.navigate(screen)
        }

        fun readShare(): User {
            val sharePref = getSharedPreferences("user", MODE_PRIVATE)
            return User(
                null,
                null,
                sharePref.getString("email", null),
                sharePref.getString("password", null)
            )
        }
        var userInfo by remember { mutableStateOf(readShare()) }
        fun writeShare(user: User) {
            val sharePref = getSharedPreferences("user", MODE_PRIVATE)
            with(sharePref.edit()) {
                putString("email", user.email)
                putString("password", user.password)
                apply()
            }
            userInfo = user
        }
        NavHost(
            navController = navController,
            startDestination = "login"
        ) {

            composable("login") {
                loginScreen.Container(
                    gotoScreen = { gotoScreen(it) },
                    shareValue = shareValue,
                )
            }
            composable("register") {
                registerScreen.Container(
                    gotoScreen = { gotoScreen(it) },
                    shareValue = shareValue,
                )
            }
            composable("navigation") {
                navigationScreen.Container(
                    gotoScreen = { gotoScreen(it) },
                    shareValue = shareValue,
                )
            }
            composable("detail") {
                detailScreen.Container(
                    gotoScreen = { gotoScreen(it) },
                    shareValue = shareValue,
                )
            }
            composable("feedback") {
                feedbackScreen.Container(
                    gotoScreen = { gotoScreen(it) },
                    shareValue = shareValue,
                )
            }
        }
    }
}

class ShareValue : ViewModel() {
    var user: User? = null
    var product: Product? = null
}

