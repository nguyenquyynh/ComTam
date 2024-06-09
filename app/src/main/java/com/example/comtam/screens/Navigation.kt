package com.example.comtam.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comtam.R
import com.example.comtam.ShareValue
import com.example.comtam.models.BottomNavigationItem
import com.example.comtam.models.User

class Navigation {
    @Composable
    fun Container (gotoScreen : (String) -> Unit,
                   shareValue : ShareValue,
                   writeShare:(user: User) -> Unit) {

        val HomeScreen = Home()
        val FavoriteScreen= Favorite()
        val AddressScreen = Address()
        val StoreScreen = Store()
        val CartScreen = Cart()

        val listBottom = listOf(
            BottomNavigationItem("Home", "home" , R.drawable.focushome, R.drawable.unfocushome),
            BottomNavigationItem("Favorite", "favorite" , R.drawable.heart, R.drawable.unfocusheart),
            BottomNavigationItem("Address", "address" , R.drawable.focusmap, R.drawable.unfocusmap),
            BottomNavigationItem("Store", "store" , R.drawable.focusstore, R.drawable.unfocusstore),
            BottomNavigationItem("Cart", "cart" , R.drawable.focuscart, R.drawable.unfocuscart),
        )

        val navController = rememberNavController()

        var selectedItem by rememberSaveable {
            mutableStateOf(0)
        }

        Scaffold(
            bottomBar = {
                NavigationBar(containerColor = Color.White) {
                    listBottom.forEachIndexed{
                            index,item ->
                        run {

                            NavigationBarItem(
                                selected = selectedItem == index,
                                onClick = {
                                    selectedItem = index
                                    navController.navigate(item.route){
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    Image(painter = painterResource(id = if(selectedItem == index) item.focusIcon else item.unFocusIcon),
                                        contentDescription = item.title,
                                        modifier = Modifier.size(24.dp))
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.White
                                ),
                            )
                        }
                    }
                }
            }
        ) {
                innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                Modifier.padding(innerPadding)
            ) {
                composable("home") { HomeScreen.Container(gotoScreen = {gotoScreen(it)}, shareValue = shareValue)}
                composable("favorite") {  FavoriteScreen.Container(gotoScreen = {gotoScreen(it)}, shareValue = shareValue)}
                composable("address") {  AddressScreen.Container(gotoScreen = {gotoScreen(it)}, shareValue = shareValue)}
                composable("store") {StoreScreen.Container(gotoScreen = {gotoScreen(it)}, shareValue = shareValue)}
                composable("cart") {CartScreen.Container(gotoScreen = {gotoScreen(it)}, shareValue = shareValue,writeShare={writeShare(it)})}
            }
        }
    }
}