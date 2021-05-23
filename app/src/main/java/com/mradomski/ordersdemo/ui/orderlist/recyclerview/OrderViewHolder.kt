package com.mradomski.ordersdemo.ui.orderlist.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mradomski.ordersdemo.databinding.OrderListItemViewBinding
import com.mradomski.ordersdemo.database.Order

class OrderViewHolder(
    private val binding: OrderListItemViewBinding,
    private val clickListener: OrderClickListener
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    init {
        binding.root.setOnClickListener(this)
    }

    fun bind(item: Order) {
        binding.order = item
        binding.executePendingBindings()
    }

    override fun onClick(v: View?) {
        clickListener.onOrderClick(v, adapterPosition)
    }

    interface OrderClickListener {
        fun onOrderClick(view: View?, position: Int)
    }
}