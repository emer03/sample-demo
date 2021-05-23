package com.mradomski.ordersdemo.network

import com.mradomski.ordersdemo.database.OrderEntity
import com.squareup.moshi.Json

data class OrderProperty(
    val orderId: Long,
    val title: String,
    val description: String,
    @Json(name = "image_url") val imageUrl: String,
    val modificationDate: String
)

fun OrderProperty.asDatabaseModel(): OrderEntity {
    return OrderEntity(
        orderId = this.orderId,
        title = this.title,
        description = this.description.substringBeforeLast("\t"),
        url = this.description.substringAfterLast("\t"),
        imageUrl = this.imageUrl,
        modificationDate = this.modificationDate
    )
}
