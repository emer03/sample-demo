package com.mradomski.ordersdemo.repository

import com.mradomski.ordersdemo.network.OrderProperty

data class Order(
    val orderId: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val modificationDate: String
)

fun OrderProperty.toOrder(): Order {
    return Order(this.orderId, this.title, this.description, this.imageUrl, this.modificationDate)
}