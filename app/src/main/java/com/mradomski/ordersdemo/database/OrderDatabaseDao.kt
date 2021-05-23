package com.mradomski.ordersdemo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mradomski.ordersdemo.database.OrderDatabase.Companion.ORDER_ID_COLUMN
import com.mradomski.ordersdemo.database.OrderDatabase.Companion.ORDER_TABLE

@Dao
interface OrderDatabaseDao {
    @Insert
    suspend fun insert(orderEntities: List<OrderEntity>)

    @Update
    suspend fun update(orderEntity: OrderEntity)

    @Query("DELETE FROM $ORDER_TABLE")
    suspend fun clear()

    @Query("SELECT * FROM $ORDER_TABLE ORDER BY $ORDER_ID_COLUMN ASC")
    fun getAll(): LiveData<List<OrderEntity>>
}
