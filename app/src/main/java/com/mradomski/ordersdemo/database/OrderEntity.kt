package com.mradomski.ordersdemo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mradomski.ordersdemo.database.OrderDatabase.Companion.ORDER_DESCRIPTION_COLUMN
import com.mradomski.ordersdemo.database.OrderDatabase.Companion.ORDER_ID_COLUMN
import com.mradomski.ordersdemo.database.OrderDatabase.Companion.ORDER_IMAGE_URL_COLUMN
import com.mradomski.ordersdemo.database.OrderDatabase.Companion.ORDER_MODIFICATION_DATE_COLUMN
import com.mradomski.ordersdemo.database.OrderDatabase.Companion.ORDER_TABLE
import com.mradomski.ordersdemo.database.OrderDatabase.Companion.ORDER_TITLE_COLUMN
import com.mradomski.ordersdemo.database.OrderDatabase.Companion.ORDER_URL_COLUMN
import com.mradomski.ordersdemo.domain.Order

@Entity(tableName = ORDER_TABLE)
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = ORDER_ID_COLUMN) var orderId: Long = 0,
    @ColumnInfo(name = ORDER_TITLE_COLUMN) val title: String = "",
    @ColumnInfo(name = ORDER_DESCRIPTION_COLUMN) val description: String = "",
    @ColumnInfo(name = ORDER_URL_COLUMN) val url: String = "",
    @ColumnInfo(name = ORDER_IMAGE_URL_COLUMN) val imageUrl: String = "",
    @ColumnInfo(name = ORDER_MODIFICATION_DATE_COLUMN) val modificationDate: String = ""
)

fun OrderEntity.asDomainModel(): Order {
    return Order(
        this.orderId,
        this.title,
        this.description,
        this.url,
        this.imageUrl,
        this.modificationDate
    )
}
