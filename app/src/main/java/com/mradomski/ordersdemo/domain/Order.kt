package com.mradomski.ordersdemo.domain

data class Order(
    val orderId: Long,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val modificationDate: String
)
