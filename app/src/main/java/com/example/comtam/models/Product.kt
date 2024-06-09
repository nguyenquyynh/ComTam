package com.example.comtam.models

import com.example.asm.ApiClient
import retrofit2.Call

data class Product (
    var id: Int?,
    var name: String?,
    var image: List<String>?,
    var description: String?,
    var price: Double?,
    var evaluate: Double?,
    var ship : String?,
    var time : String?,
    var quantity: Int?,
    var tag : List<String>?,
    var feedback : List<Feedback>?
)

