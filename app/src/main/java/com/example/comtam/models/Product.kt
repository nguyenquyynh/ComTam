package com.example.comtam.models

data class Product (
    val id: Int?,
    val name: String?,
    val image: List<String>?,
    val description: String?,
    val price: Double?,
    val evaluate: Double?,
    val ship : String?,
    val time : String?,
    val qunatity: Int?,
    val tag : List<String>?,
    val feedback : List<Feedback>?
)