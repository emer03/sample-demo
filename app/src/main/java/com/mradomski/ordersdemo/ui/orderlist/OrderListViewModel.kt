package com.mradomski.ordersdemo.ui.orderlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mradomski.ordersdemo.repository.OrderDatabaseDao
import com.mradomski.ordersdemo.repository.OrderRepository
import kotlinx.coroutines.launch

class OrderListViewModel(
    private val database: OrderDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    val orders = database.getAll()

    init {
        fetchOrders()
    }

    private fun fetchOrders() {
        viewModelScope.launch {
            val newOrders = OrderRepository().getAllOrders()
            database.clear()
            database.insert(newOrders)
        }
    }
}