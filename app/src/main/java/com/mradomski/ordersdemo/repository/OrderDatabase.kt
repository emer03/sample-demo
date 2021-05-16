package com.mradomski.ordersdemo.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Order::class], version = 1, exportSchema = false)
abstract class OrderDatabase : RoomDatabase() {

    abstract val orderDao: OrderDao

    companion object {
        private const val DATABASE_NAME = "order_database"
        const val ORDER_TABLE = "order_table"
        const val ORDER_KEY = "orderId"
        const val ORDER_TITLE_COLUMN = "title"
        const val ORDER_DESCRIPTION_COLUMN = "description"
        const val ORDER_URL_COLUMN = "url"
        const val ORDER_IMAGE_URL_COLUMN = "image_url"
        const val ORDER_MODIFICATION_DATE_COLUMN = "modification_date"

        @Volatile
        private var INSTANCE: OrderDatabase? = null

        fun getInstance(context: Context): OrderDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        OrderDatabase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}