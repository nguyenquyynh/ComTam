package com.example.comtam.commond

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.comtam.R
import com.example.comtam.ShareValue
import com.example.comtam.models.Product
import com.example.comtam.ui.theme.Green

@Composable
fun ShowlistMain(list: List<Product>?, context: Context, title: String, gotoScreen : (String) -> Unit, shareValue : ShareValue) {
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "${title}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "See All", fontSize = 12.sp, fontWeight = FontWeight.Normal, color = Green)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow {
            items(list!!, key = { it.id!! }){ item ->
                RenderItemfood(item, context, gotoScreen = {gotoScreen(it)}, shareValue = shareValue)
                Spacer(modifier = Modifier.width(21.dp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

    }
}

@Composable
fun RenderItemfood(item: Product, context: Context, gotoScreen : (String) -> Unit, shareValue : ShareValue){
    var love by remember {
        mutableStateOf(false)
    }

    val painter = rememberAsyncImagePainter(
        model =  ImageRequest.Builder(context)
            .data(item.image)
            .size(Size.ORIGINAL)
            .build()
    )

    Box(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(15.dp))
            .width(266.dp)
            .clickable {
                shareValue.product = item
                gotoScreen("detail")
            }
    ){
        Column {
            Box{
                AsyncImage(model = if(!item.image.isNullOrEmpty()) item.image?.get(0) else "https://cdn.pixabay.com/photo/2023/09/25/19/58/piran-8275931_1280.jpg",
                    contentDescription = "food",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(136.dp)
                        .background(
                            Color.Blue,
                            RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                        )
                        .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .background(Color.White, RoundedCornerShape(15.dp)),
                    ){
                        Row(
                            modifier = Modifier
                                .padding(7.dp),
                            verticalAlignment = Alignment.Bottom,
                        ){
                            Text(text = item.evaluate.toString(),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Thin,
                                fontStyle = FontStyle.Italic
                            )

                            Spacer(modifier = Modifier.width(5.dp))

                            Image(painter = painterResource(id = R.drawable.star),
                                contentDescription = "star",
                                modifier = Modifier.size(16.dp))

                            Spacer(modifier = Modifier.width(5.dp))

                            Text(text = "(${item.evaluate})", fontSize = 9.sp,
                                color = Color(0xFF9796A1)
                            )
                        }
                    }

                    Box(modifier = Modifier
                        .size(48.dp)
                        .padding(10.dp)
                        .background(Color.White, CircleShape)
                        .clickable {
                            love = !love
                        },
                        contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(
                                id = if(!love) R.drawable.unheart
                                else R.drawable.heart),
                            contentDescription = "heart",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

            }



            Column(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 12.dp)
            ) {
                Row{
                    Text(text = item.name+"",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 16.sp)

                    Spacer(modifier = Modifier.width(15.dp))

                    Image(painter = painterResource(id = R.drawable.check),
                        contentDescription = "check",
                        modifier = Modifier
                            .width(12.dp)
                            .height(12.dp))

                }

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.ship),
                        contentDescription = "delivery",
                        modifier = Modifier
                            .width(16.dp)
                            .height(12.dp))
                    Spacer(modifier = Modifier.width(3.dp))

                    Text(text = item.ship+"",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF7E8392)
                    )

                    Spacer(modifier = Modifier.width(21.dp))

                    Image(painter = painterResource(id = R.drawable.clock),
                        contentDescription = "clock",
                        modifier = Modifier
                            .width(16.dp)
                            .height(12.dp))
                    Spacer(modifier = Modifier.width(3.dp))

                    Text(text = item.time+"",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF7E8392)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                if(item.tag?.size!! > 0){
                    LazyRow {
                        items(item.tag!!){ data ->
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFF6F6F6), RoundedCornerShape(5.dp))
                                    .padding(5.dp)
                            ) {
                                Text(text = data,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Gray)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }


            }


        }
    }
}