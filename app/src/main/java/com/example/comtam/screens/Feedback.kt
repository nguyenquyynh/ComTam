package com.example.comtam.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.comtam.ShareValue
import com.example.comtam.ui.theme.Orange
import com.example.comtam.ui.theme.Orange2
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.example.comtam.R

class Feedback {
    @Composable
    fun Container (gotoScreen : (String) -> Unit,
                   shareValue : ShareValue) {
        BodyReview()
    }
}
@Preview(showBackground = true)
@Composable
fun BodyReview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        Column {
            ImageSlider(
                images = listOf(
                    "https://cdn.tgdd.vn/Products/Images/42/324893/honor-x8b-thiet-ke.jpg",
                    "https://cdn.tgdd.vn/Products/Images/42/324893/honor-x8b-thiet-ke.jpg",
                    "https://cdn.tgdd.vn/Products/Images/42/324893/honor-x8b-thiet-ke.jpg"
                )
            )
            Infornation()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Infornation(){
    var comment by remember { mutableStateOf(TextFieldValue("")) }
    var star by remember { mutableStateOf(false) }
    var star2 by remember { mutableStateOf(false) }
    var star3 by remember { mutableStateOf(false) }
    var star4 by remember { mutableStateOf(false) }
    var star5 by remember { mutableStateOf(false) }

    Box(modifier = Modifier.padding(10.dp)
    ){
        Column {
            Text(text = "Ground Beef Tacos",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(0.3f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(painter = painterResource(id = R.drawable.star),
                            contentDescription = "star",
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp))
                        Text(text = "4.5",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(text = "(30+)",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }

                }
                Box( modifier = Modifier
                    .fillMaxWidth(0.4f)
                ){
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(painter = painterResource(id = R.drawable.motobike), contentDescription = "tick",
                            modifier = Modifier
                                .height(15.dp)
                                .width(15.dp))

                        Text(text = "Free delivery",
                            fontSize = 11.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 7.dp))
                    }
                }
                Box( modifier = Modifier
                    .fillMaxWidth(0.6f)
                ){
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(painter = painterResource(id = R.drawable.oclock), contentDescription = "tick",
                            modifier = Modifier
                                .height(15.dp)
                                .width(15.dp))

                        Text(text = "10-15 mins",
                            fontSize = 11.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 7.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center  // Align content inside Box to the center
            ) {
                Row(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = "Good",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium,
                        color = Orange2,
                    )
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth(0.7f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Image(painter = painterResource(id = if (star) R.drawable.star else R.drawable.unstar), contentDescription = "star",
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .clickable {
                                star = !star
                            })
                    Image(painter = painterResource(id = if (star2) R.drawable.starchoose else R.drawable.unstar), contentDescription = "star",
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .clickable {
                                star2 = !star2
                            })
                    Image(painter = painterResource(id = if (star3) R.drawable.starchoose else R.drawable.unstar), contentDescription = "star",
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .clickable {
                                star3 = !star3
                            })
                    Image(painter = painterResource(id = if (star4) R.drawable.starchoose else R.drawable.unstar), contentDescription = "star",
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .clickable {
                                star4 = !star4
                            })
                    Image(painter = painterResource(id = if (star5) R.drawable.starchoose else R.drawable.unstar), contentDescription = "star",
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .clickable {
                                star5 = !star5
                            })
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = comment,
                onValueChange = { comment = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                        .border(
                            width = 1.dp, // Độ dày của viền
                            color = Color.Gray, // Màu viền
                            shape = RoundedCornerShape(10.dp),
                        ),
                textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
                colors = TextFieldDefaults.colors(
                    cursorColor = Color.Black, // Màu con trỏ
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(40.dp))
            ElevatedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.elevatedButtonColors(
                    contentColor = Color.White,
                    containerColor = Orange


                ),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp)

            ) {
                Text(text = "Rate",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp)
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(images: List<String>) {
    val pagerState = rememberPagerState()
    var isHeart by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)

        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp)
                    .zIndex(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Box(modifier = Modifier
                    .height(35.dp)
                    .width(35.dp)
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                ){
                    Row(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.back), contentDescription = "back",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Box(modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .clickable {
                        isHeart = !isHeart
                    },
                ){
                    Row(
                        modifier = Modifier.padding(7.dp)
                    ) {
                        Image(painter = painterResource(id = if(isHeart) R.drawable.favouritechoose else R.drawable.favourite), contentDescription = "back",
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
            }
            HorizontalPager(
                state = pagerState,
                count = images.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) { page ->
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(images[page])
                        .size(Size.ORIGINAL)
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                activeColor = Orange,
                inactiveColor = Color.Gray
            )
        }


    }
}