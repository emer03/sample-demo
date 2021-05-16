package com.mradomski.ordersdemo.ui.orderlist.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mradomski.ordersdemo.R
import com.mradomski.ordersdemo.repository.Order
import com.mradomski.ordersdemo.ui.orderlist.OrderListFragmentDirections

class OrderAdapter : RecyclerView.Adapter<OrderViewHolder>(), OrderViewHolder.OrderClickListener {

    var data = listOf<Order>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.order_list_item_view, parent, false) as TextView
        return OrderViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = data[position]
        holder.title.text = item.title
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onOrderClick(view: View?, position: Int) {
        view?.findNavController()
            ?.navigate(OrderListFragmentDirections.actionOrderListFragmentToWebViewFragment(data[position].url))
    }
}