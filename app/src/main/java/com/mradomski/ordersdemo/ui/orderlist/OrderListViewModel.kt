package com.mradomski.ordersdemo.ui.orderlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mradomski.ordersdemo.database.OrderDatabaseDao
import com.mradomski.ordersdemo.repository.OrderRepository
import kotlinx.coroutines.launch

class OrderListViewModel(
    private val database: OrderDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val orderRepository = OrderRepository(database)

    private val _disableRefresh = MutableLiveData<Boolean>()
    val disableRefresh: LiveData<Boolean>
        get() = _disableRefresh

    private val _showNetworkError = MutableLiveData<Boolean>()
    val showNetworkError: LiveData<Boolean>
        get() = _showNetworkError

    val orders = orderRepository.orders

    init {
        refreshOrders()
    }

    fun refreshOrders() {
        viewModelScope.launch {
            _disableRefresh.value = true
            try {
                orderRepository.refreshOrders()
            } catch (e: Exception) {
                _showNetworkError.value = true
            }
            _disableRefresh.value = false
        }
    }

    fun networkErrorCompleted() {
        _showNetworkError.value = false
    }
}