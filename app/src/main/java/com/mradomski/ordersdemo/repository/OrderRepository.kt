package com.mradomski.ordersdemo.repository

import com.mradomski.ordersdemo.network.OrderApi

class OrderRepository {

    suspend fun getAllOrders(): List<Order> {
        return OrderApi.restService.getOrders().map { it.toOrder() }
    }

}
