package com.mradomski.ordersdemo

import com.mradomski.ordersdemo.network.OrderProperty
import com.mradomski.ordersdemo.database.toOrder
import org.junit.Assert.assertEquals
import org.junit.Test

class UnitTests {

    private val orderProperty = OrderProperty(
        1,
        "Padu",
        "Some long description.\thttp://mradomski.com/",
        "http://image.pl/",
        "1410-07-15"
    )

    @Test
    fun toOrder_CorrectConversion_True() {
        val order = orderProperty.toOrder()
        assertEquals("Padu", order.title)
        assertEquals("Some long description.", order.description)
        assertEquals("http://mradomski.com/", order.url)
        assertEquals("http://image.pl/", order.imageUrl)
        assertEquals("1410-07-15", order.modificationDate)
    }
}
