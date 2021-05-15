package com.mradomski.ordersdemo.ui.orderlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mradomski.ordersdemo.repository.Order
import com.mradomski.ordersdemo.repository.OrderRepository
import kotlinx.coroutines.launch

class OrderListViewModel : ViewModel() {
    private val _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>>
        get() = _orders

    init {
        fetchOrders()
    }

    fun fetchOrders() {
        viewModelScope.launch {
            _orders.value = OrderRepository().getAllOrders()
        }
    }
}