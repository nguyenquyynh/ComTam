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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.comtam.R
import coil.size.Size
import com.example.comtam.ShareValue
import com.example.comtam.commond.ShowlistMain
import com.example.comtam.models.Feedback
import com.example.comtam.models.Product
import com.example.comtam.ui.theme.Green
import com.example.comtam.ui.theme.Orange
import com.example.comtam.ui.theme.TextGray
import com.example.comtam.ui.theme.WhiteTr
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.pager.HorizontalPagerIndicator

class Detail {
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun Container(
        gotoScreen: (String) -> Unit,
        shareValue: ShareValue
    ) {
        var quantity by remember { mutableStateOf(1) }
        var product =
//            shareValue.product
            Product(
                12,
                "SP Demo",
                image = listOf(
                    "https://cdn.pixabay.com/photo/2017/09/30/15/10/plate-2802332_1280.jpg",
                    "https://cdn.pixabay.com/photo/2018/04/07/15/03/pizza-3298685_1280.jpg",
                    "https://cdn.pixabay.com/photo/2020/04/29/03/30/pizza-5107039_1280.jpg"
                ),
                "Brown the beef better. Lean ground beef – I like to use 85% lean angus. Garlic – use fresh  chopped. Spices – chili powder, cumin, onion powder.",
                12.4,
                3.5,
                "Free delivery",
                "10 - 15 minutes",
                10,
                listOf("Burger", "Chicken", "Rice"),
                listOf(
                    Feedback(
                        1,
                        "ABadia Cloria",
                        4,
                        "Brown the beef better. Lean ground beef – I like to use 85% lean angus. Garlic – use fresh  chopped. Spices – chili powder, cumin, onion powder.",
                        "22/12/2022"
                    ),
                    Feedback(
                        1,
                        "ABadia Cloria",
                        4,
                        "Brown the beef better. Lean ground beef – I like to use 85% lean angus. Garlic – use fresh  chopped. Spices – chili powder, cumin, onion powder.",
                        "22/12/2022"
                    )
                )
            )
        val pagestate = rememberPagerState()
        val feedbackstate = rememberPagerState()
        val list = listOf(product)
        fun addQuantity(value: Int) {
            if (quantity + value > 0 && quantity + value <= product.quantity!!) {
                quantity += value
            }
        }

        @Composable
        fun imageOnline(link: String): AsyncImagePainter {
            var paint = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(link)
                    .size(Size.ORIGINAL)
                    .build()
            )
            return paint
        }


        //UI
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            //Content conatiner
            LazyColumn(Modifier.weight(13f)) {
                item {
                    Box(
                        Modifier
                            .fillMaxWidth()
                    ) {
                        //Button container
                        Row {
                            //Button back
                            Box() {}
                            //Button heart
                            Box() {}
                        }
                        //Content
                        Column(Modifier.fillMaxWidth()) {
                            Box(Modifier.fillMaxWidth()) {
                                //SlideShow
                                HorizontalPager(
                                    modifier = Modifier.fillMaxWidth(),
                                    state = pagestate,
                                    count = if (product?.image!!.size != 0) product?.image!!.size else 3
                                ) {
                                    Image(
                                        painter = imageOnline(if (product.image!![it] != null) product.image!![it] else "https://thepizzacompany.vn/images/thumbs/000/0002212_sf-cocktail-test_300.png"),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
                                            .clip(shape = RoundedCornerShape(10.dp)),
                                        contentScale = ContentScale.Crop,
                                    )
                                }
                                HorizontalPagerIndicator(
                                    pagerState = pagestate,
                                    activeColor = Orange,
                                    indicatorWidth = 15.dp,
                                    indicatorHeight = 7.dp,
                                    indicatorShape = RoundedCornerShape(100.dp),
                                    inactiveColor = WhiteTr,
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .padding(10.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                            //Product name
                            if (product != null) {
                                product.name?.let {
                                    Text(
                                        text = it,
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                //Evaluate
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.star),
                                        contentDescription = "",
                                        Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        text = "${product?.evaluate}",
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        text = "(${product?.quantity}+)",
                                        fontSize = 14.sp,
                                        color = TextGray
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ship),
                                        contentDescription = "",
                                        Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        text = "${product?.ship}",
                                        color = TextGray,
                                        fontSize = 14.sp
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(id = R.drawable.clock),
                                        contentDescription = "",
                                        Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        text = "${product?.time}",
                                        color = TextGray,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = "${product?.price} $",
                                    color = Green,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                Row {
                                    Image(painter = painterResource(id = R.drawable.minus),
                                        contentDescription = "",
                                        Modifier
                                            .size(30.dp)
                                            .clickable { addQuantity(-1) })
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = "${quantity}",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Light
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Image(painter = painterResource(id = R.drawable.plus),
                                        contentDescription = "",
                                        Modifier
                                            .size(30.dp)
                                            .clickable { addQuantity(1) })
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "${product.description}",
                                fontSize = 14.sp,
                                color = TextGray,
                                lineHeight = 15.sp
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            HorizontalPager(
                                state = feedbackstate,
                                count = product.feedback?.size!!
                            ) {
                                renderFeedback(product.feedback!![it])
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            ShowlistMain(
                                list = list.toMutableList(),
                                context = LocalContext.current,
                                "Restaurant’ss Featured Partner"
                            )
                            ShowlistMain(
                                list = list.toMutableList(),
                                context = LocalContext.current,
                                "Other Meal"
                            )
                        }
                    }
                }
            }
            //Add cart container
            Row(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Red, shape = RoundedCornerShape(10.dp))
                        .height(60.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Add to cart",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .background(
                            Color.Transparent,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
                        .size(60.dp),
                    contentAlignment = Alignment.Center
                ){
                    Image(painter = painterResource(id = R.drawable.warning), contentDescription = "", modifier = Modifier.size(40.dp))
                }
            }
        }
    }

    @Composable
    fun renderFeedback(item: Feedback) {
        Column(
            Modifier
                .width(370.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.logo), contentDescription = "")
                Column {
                    Text(text = "${item.name}", fontWeight = FontWeight.Bold)
                    Text(text = "Rank Gold")
                }
                Text(text = "${item.createdAt}")
                Text(text = "${item.evaluate}", fontWeight = FontWeight.Bold)
                Image(
                    painter = painterResource(id = R.drawable.edit),
                    modifier = Modifier.size(30.dp),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "${item.content}",
                Modifier.padding(start = 40.dp),
                lineHeight = 16.sp,
                fontSize = 14.sp
            )
        }

    }
}