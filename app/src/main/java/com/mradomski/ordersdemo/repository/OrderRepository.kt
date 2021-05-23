package com.mradomski.ordersdemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mradomski.ordersdemo.database.OrderDatabaseDao
import com.mradomski.ordersdemo.database.asDomainModel
import com.mradomski.ordersdemo.domain.Order
import com.mradomski.ordersdemo.network.OrderApi
import com.mradomski.ordersdemo.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrderRepository(private val database: OrderDatabaseDao) {

    val orders: LiveData<List<Order>> =
        Transformations.map(database.getAll()) { list -> list.map { it.asDomainModel() } }

    suspend fun refreshOrders() {
        withContext(Dispatchers.IO) {
            val newOrders = OrderApi.restService.getOrders().map { it.asDatabaseModel() }
            database.clear()
            database.insert(newOrders)
        }
    }
}
