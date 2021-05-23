package com.mradomski.ordersdemo.repository

import com.mradomski.ordersdemo.database.OrderDatabaseDao
import com.mradomski.ordersdemo.database.toOrder
import com.mradomski.ordersdemo.network.OrderApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrderRepository(private val database: OrderDatabaseDao) {

    val orders = database.getAll()

    suspend fun refreshOrders() {
        withContext(Dispatchers.IO) {
            val newOrders = OrderApi.restService.getOrders().map { it.toOrder() }
            database.clear()
            database.insert(newOrders)
        }

    }

}
