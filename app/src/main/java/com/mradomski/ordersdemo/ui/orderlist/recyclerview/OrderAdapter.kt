package com.mradomski.ordersdemo.ui.orderlist.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mradomski.ordersdemo.databinding.OrderListItemViewBinding
import com.mradomski.ordersdemo.domain.Order

class OrderAdapter(private val clickListener: (order: Order) -> Unit) :
    RecyclerView.Adapter<OrderViewHolder>(), OrderViewHolder.OrderClickListener {

    var data = listOf<Order>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = OrderListItemViewBinding.inflate(layoutInflater, parent, false)
        return OrderViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onOrderClick(view: View?, position: Int) {
        onClickListener(view, position)
    }

    private fun onClickListener(view: View?, position: Int) {
        clickListener(data[position])
    }
}
