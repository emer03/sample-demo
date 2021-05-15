package com.mradomski.ordersdemo.network

import com.squareup.moshi.Json

data class OrderProperty(
    val orderId: Int,
    val title: String,
    val description: String,
    @Json(name = "image_url") val imageUrl: String,
    val modificationDate: String
)
