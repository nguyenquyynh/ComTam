package com.example.asm


import com.example.comtam.models.Product
import com.example.comtam.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun loginAPI(@Query("email") e : String, @Query("password") p : String) : Call<List<User>>
    @POST("users")
    fun registerAPI(@Body user : User) : Call<User>
    @GET("products")
    fun getListProductAPI() : Call<List<Product>>
    @GET("products/{id}")
    fun getProductAPI(@Path("id") id : Int) : Call<Product>
    @PUT("products/{id}")
    fun updateProductAPI(@Path("id") id : Int, @Body product : Product) : Call<Product>
}
