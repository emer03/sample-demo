package com.mradomski.ordersdemo.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://recruitment-task.futuremind.dev/"
private const val ORDER_PATH = "recruitment-task"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface OrderApiService {
    @GET(ORDER_PATH)
    suspend fun getOrders(): List<OrderProperty>
}

object OrderApi {
    val restService: OrderApiService by lazy { retrofit.create(OrderApiService::class.java) }
}
