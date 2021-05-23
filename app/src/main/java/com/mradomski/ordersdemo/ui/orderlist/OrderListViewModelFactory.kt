package com.mradomski.ordersdemo.ui.orderlist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mradomski.ordersdemo.database.OrderDatabaseDao

class OrderListViewModelFactory(
    private val dataSource: OrderDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderListViewModel::class.java)) {
            return OrderListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}