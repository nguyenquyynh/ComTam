package com.example.comtam.screens

import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.asm.ApiClient
import com.example.comtam.ShareValue
import com.example.comtam.ui.theme.Orange
import com.example.comtam.ui.theme.Orange2
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.example.comtam.R
import com.example.comtam.models.Feedback
import com.example.comtam.models.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class Feedback {
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun Container (gotoScreen : (String) -> Unit,
                   shareValue : ShareValue) {

        BodyReview(gotoScreen, shareValue)
    }
}
@Composable
fun BodyReview(gotoScreen: (String) -> Unit, shareValue: ShareValue) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        Column {
            ImageSlider(gotoScreen,
                images = listOf(
                    "https://thepizzacompany.vn/images/thumbs/000/0002212_sf-cocktail-test_300.png",
                    "https://thepizzacompany.vn/images/thumbs/000/0002212_sf-cocktail-test_300.png",
                    "https://thepizzacompany.vn/images/thumbs/000/0002212_sf-cocktail-test_300.png"
                )
            )
            Infornation(gotoScreen, shareValue)
        }
    }
}


@Composable
fun Infornation(gotoScreen: (String) -> Unit, shareValue: ShareValue) {

    var comment by remember { mutableStateOf(TextFieldValue("")) }
    var star by remember { mutableStateOf(false) }
    var star2 by remember { mutableStateOf(false) }
    var star3 by remember { mutableStateOf(false) }
    var star4 by remember { mutableStateOf(false) }
    var star5 by remember { mutableStateOf(false) }
    var clickCount by remember { mutableStateOf(0) }

    var product = shareValue.product

    fun updateProduct(product: Product) {
        val call = product?.id?.let { ApiClient.apiService.updateProductAPI(it, product) }
        if (call != null) {
            call.enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    Log.d("success","Thêm thành công")
                    gotoScreen("detail")
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    // Xử lý khi cập nhật thất bại
                }
            })
        }
    }
    //hàm ramdom ra chuỗi nhiều ký tự số làm id
    fun generateRandomNumberString(length: Int): String {
        val digits = "0123456789"
        return (1..length)
            .map { digits.random() }
            .joinToString("")
    }
    fun addFeedback() {
        val randomNumber = generateRandomNumberString(15)
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH bắt đầu từ 0
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val newFeedback = Feedback(
            id = randomNumber,
            name = "Jenifer Scarlet",
            evaluate = clickCount,
            content = "${comment.text}",
            createdAt = "${day}-${month}-${year}"
        )

        val call = shareValue?.product?.id?.let { ApiClient.apiService.getProductAPI(it) }
        call?.enqueue(object : Callback<Product> {
            override fun onResponse(
                call: retrofit2.Call<Product>,
                response: retrofit2.Response<Product>
            ) {
                if (response.isSuccessful) {
                    val product = response.body()
                    product?.let {
                        // Kiểm tra xem mảng feedback có được khởi tạo không
                        if (it.feedback == null) {
                            it.feedback = mutableListOf()
                        }
                        it.feedback = it.feedback!! + newFeedback
                        // Cập nhật sản phẩm lên server
                        updateProduct(it)
                    }
                }

            }

            override fun onFailure(call: retrofit2.Call<Product>, t: Throwable) {
                gotoScreen("navigation")
            }
        })
    }

    Box(modifier = Modifier.padding(10.dp)
    ){
        Column {
            Text(text = "${product?.name}",
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
                        Text(text = "${product?.evaluate}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(text = "(${product?.quantity}+)",
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

                        Text(text = "${product?.ship}",
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

                        Text(text = "${product?.time}",
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
                    Image(
                        painter = painterResource(id = if (star) R.drawable.star else R.drawable.unstar),
                        contentDescription = "star",
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .clickable {
                                star = !star
                                if (star) {
                                    star2= false
                                    star3= false
                                    star4= false
                                    star5= false
                                    clickCount = 1;
                                    Log.d("StarImage", "tăng :${clickCount}");
                                } else {
                                    star = false
                                    star2= false
                                    star3= false
                                    star4= false
                                    star5= false
                                    clickCount = 0;
                                    Log.d("StarImage", "giảm :${clickCount}");
                                }
                            }
                    )
                    Image(painter = painterResource(id = if (star2) R.drawable.starchoose else R.drawable.unstar), contentDescription = "star",
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .clickable {
                                star2 = !star2
                                if (star2) {
                                    star = true
                                    star3= false
                                    star4= false
                                    star5= false
                                    clickCount = 2;
                                    Log.d("StarImage", "tăng :${clickCount}");
                                } else {
                                    star = true
                                    star2= false
                                    star3= false
                                    star4= false
                                    star5= false
                                    clickCount = 1;
                                    Log.d("StarImage", "giảm :${clickCount}");
                                }
                            })
                    Image(painter = painterResource(id = if (star3) R.drawable.starchoose else R.drawable.unstar), contentDescription = "star",
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .clickable {
                                star3 = !star3
                                if (star3) {
                                    star = true
                                    star2 = true
                                    star4= false
                                    star5= false
                                    clickCount = 3;
                                    Log.d("StarImage", "tăng :${clickCount}");
                                } else {
                                    star = true
                                    star2= true
                                    star3= false
                                    star4= false
                                    star5= false
                                    clickCount = 2;
                                    Log.d("StarImage", "giảm :${clickCount}");
                                }
                            })
                    Image(painter = painterResource(id = if (star4) R.drawable.starchoose else R.drawable.unstar), contentDescription = "star",
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .clickable {
                                star4 = !star4
                                if (star4) {
                                    star = true
                                    star2 = true
                                    star3= true
                                    star5= false
                                    clickCount = 4;
                                    Log.d("StarImage", "tăng :${clickCount}");
                                } else {
                                    star = true
                                    star2= true
                                    star3= true
                                    star4= false
                                    star5= false
                                    clickCount = 3;
                                    Log.d("StarImage", "giảm :${clickCount}");
                                }
                            })
                    Image(painter = painterResource(id = if (star5) R.drawable.starchoose else R.drawable.unstar), contentDescription = "star",
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .clickable {
                                star5 = !star5
                                if (star5) {
                                    star = true
                                    star2 = true
                                    star3= true
                                    star4= true
                                    clickCount = 5;
                                    Log.d("StarImage", "tăng :${clickCount}");
                                } else {
                                    star = true
                                    star2= true
                                    star3= true
                                    star4= true
                                    star5= false
                                    clickCount = 4;
                                    Log.d("StarImage", "giảm :${clickCount}");
                                }
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
                onClick = { addFeedback() },
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
fun ImageSlider(gotoScreen: (String) -> Unit, images: List<String>) {
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
                    .clickable {
                        gotoScreen("detail")
                    }
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