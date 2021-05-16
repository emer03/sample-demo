package com.mradomski.ordersdemo.ui.orderlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mradomski.ordersdemo.repository.OrderDatabaseDao
import com.mradomski.ordersdemo.repository.OrderRepository
import kotlinx.coroutines.launch

class OrderListViewModel(
    private val database: OrderDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val _disableRefresh = MutableLiveData<Boolean>()
    val disableRefresh: LiveData<Boolean>
        get() = _disableRefresh

    val orders = database.getAll()

    init {
        if (orders.value?.isEmpty() != false) {
            fetchOrders()
        }
    }

    fun fetchOrders() {
        viewModelScope.launch {
            _disableRefresh.value = true
            try {
                val newOrders = OrderRepository().getAllOrders()
                database.clear()
                database.insert(newOrders)
            } catch (e: Exception) {
                //show error to user
            }
            _disableRefresh.value = false
        }
    }
}