package com.mradomski.ordersdemo.ui.orderlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mradomski.ordersdemo.R
import com.mradomski.ordersdemo.repository.Order

class OrderRecyclerAdapter : RecyclerView.Adapter<OrderViewHolder>() {

    var data = listOf<Order>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.order_list_item_view, parent, false) as TextView
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = data[position]
        holder.title.text = item.title
    }

    override fun getItemCount(): Int {
        return data.size
    }
}