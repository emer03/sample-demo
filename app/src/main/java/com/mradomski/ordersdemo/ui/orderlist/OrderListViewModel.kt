package com.mradomski.ordersdemo.ui.orderlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mradomski.ordersdemo.network.OrderApi
import com.mradomski.ordersdemo.network.OrderProperty
import kotlinx.coroutines.launch

class OrderListViewModel : ViewModel() {
    private val _orders = MutableLiveData<List<OrderProperty>>()
    val orders: LiveData<List<OrderProperty>>
        get() = _orders

    fun fetchOders() {
        viewModelScope.launch {
            _orders.value = OrderApi.restService.getOrders()
        }
    }
}