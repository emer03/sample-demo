package com.mradomski.ordersdemo.repository

import androidx.lifecycle.MutableLiveData
import com.mradomski.ordersdemo.network.OrderApi

class OrderRepository {

    val orders = MutableLiveData<List<Order>>()

    suspend fun getAllOrders(): List<Order> {
        return OrderApi.restService.getOrders().map { it.toOrder() }
    }

}