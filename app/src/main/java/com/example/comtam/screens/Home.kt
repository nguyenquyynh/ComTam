package com.example.comtam.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.comtam.R
import com.example.comtam.commond.ShowlistMain
import com.example.comtam.models.Feedback
import com.example.comtam.models.Product
import java.io.Serializable


class Home {
    @Preview(showBackground = true)
    @Composable
    fun Container () {
        var list by remember { mutableStateOf(
            mutableListOf(
                Product(
                    1,
                    "McDonald’s",
                    listOf("https://cdn.pixabay.com/photo/2020/03/28/14/51/french-fries-4977354_640.jpg"),
                    "Free delivery",
                    20.5,
                    2.5,
                    "Free delivery",
                    "10-15 mins",
                    120,
                    tag = listOf("Burger","Chicken","Fast Food"),
                    feedback = listOf(
                        Feedback(null,null,null,null, null)
                    ),
                ),
                Product(
                    2,
                    "McDonald’s",
                    listOf("https://cdn.pixabay.com/photo/2020/03/28/14/51/french-fries-4977354_640.jpg"),
                    "Free delivery",
                    20.5,
                    2.5,
                    "Free delivery",
                    "10-15 mins",
                    120,
                    tag = listOf("Burger","Chicken","Fast Food"),
                    feedback = listOf(
                        Feedback(null,null,null,null, null)
                    ),
                ),
                Product(
                    3,
                    "McDonald’s",
                    listOf("https://cdn.pixabay.com/photo/2020/03/28/14/51/french-fries-4977354_640.jpg"),
                    "Free delivery",
                    20.5,
                    2.5,
                    "Free delivery",
                    "10-15 mins",
                    120,
                    tag = listOf("Burger","Chicken","Fast Food"),
                    feedback = listOf(
                        Feedback(null,null,null,null, null)
                    ),
                ),
            )
        )}
        val context = LocalContext.current
        var textSearch by remember { mutableStateOf("") }

        Column(modifier = Modifier.fillMaxSize()) {
            Header()

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)){
                OutlinedTextField(
                    value = textSearch,
                    onValueChange = {textSearch = it},
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = {
                        Text(text = "Search",
                            color = Color(0x33000000)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color(0x33000000),
                        unfocusedIndicatorColor = Color(0x33000000)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    trailingIcon = {
                        Image(painter = painterResource(id = R.drawable.search),
                            contentDescription = "",
                            modifier = Modifier.size(35.dp))
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.padding(horizontal = 24.dp)){
                item {
                    Column{

                        ShowlistMain(list, context)

                        ShowlistMain(list, context)

                        ShowlistMain(list, context)
                        
                    }
                }
            }
        }
    }

    @Composable
    fun Header(){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, start = 25.dp, end = 25.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween){

            Box(modifier = Modifier
                .size(38.dp)
                .border(1.dp, Color(0x33000000), RoundedCornerShape(13.dp))
                .background(Color.White, RoundedCornerShape(13.dp))
                .shadow(
                    10.dp,
                    clip = true,
                    shape = RoundedCornerShape(13.dp),
                    spotColor = Color.White
                ),
                contentAlignment = Alignment.Center
            ){
                Image(painter = painterResource(id = R.drawable.menu),
                    contentDescription = "",
                    modifier = Modifier
                        .width(16.dp)
                        .height(8.dp))
            }


            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Deliver to",
                        fontSize = 14.sp,
                        color = Color(0xFF8C9099),
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Image(painter = painterResource(id = R.drawable.dropdown),
                        contentDescription = "",
                        modifier = Modifier
                            .width(8.dp)
                            .height(5.dp)
                    )
                }

                Text(text = "Choose your city and state",
                    fontSize = 15.sp,
                    color = Color(0xFFFE724C),
                    fontWeight = FontWeight.Medium
                )
            }

            Image(painter = painterResource(id = R.drawable.sefi),
                contentDescription = "",
                modifier = Modifier.size(38.dp))
        }
    }

}