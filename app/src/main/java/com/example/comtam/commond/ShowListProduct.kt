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
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.comtam.R
import com.example.comtam.models.Product

@Composable
fun ShowlistMain(list: MutableList<Product>, context: Context) {
    Column {
        Text(text = "aaaa")

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow {
            items(list, key = { it.id!! }){ item ->
                RenderItemfood(item, context)
                Spacer(modifier = Modifier.width(21.dp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

    }
}

@Composable
fun RenderItemfood(item: Product, context: Context){
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

            }
    ){
        Column {
            Box{
                Image(painter =painter,
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
                            verticalAlignment = Alignment.Bottom
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
                        .width(58.dp)
                        .height(48.dp)
                        .padding(vertical = 10.dp, horizontal = 16.dp)
                        .background(Color.White, CircleShape)
                        .clickable {

                        },
                        contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(
                                id = if(item.quantity != 1) R.drawable.unheart
                                else R.drawable.heart),
                            contentDescription = "heart",
                            modifier = Modifier
                                .width(23.dp)
                                .height(23.dp)
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
                        items(item.tag){data ->
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