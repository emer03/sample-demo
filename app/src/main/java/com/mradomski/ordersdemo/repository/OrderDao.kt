package com.mradomski.ordersdemo.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mradomski.ordersdemo.repository.OrderDatabase.Companion.ORDER_KEY
import com.mradomski.ordersdemo.repository.OrderDatabase.Companion.ORDER_TABLE

@Dao
interface OrderDao {
    @Insert
    fun insert(orders: List<Order>)

    @Update
    fun update(order: Order)

    @Query("SELECT * FROM $ORDER_TABLE ORDER BY $ORDER_KEY ASC")
    fun getAll(): List<Order>

    @Query("DELETE FROM $ORDER_TABLE")
    fun clear()
}