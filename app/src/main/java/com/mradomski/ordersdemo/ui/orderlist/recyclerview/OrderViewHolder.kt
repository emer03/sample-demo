package com.mradomski.ordersdemo.ui.orderlist.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderViewHolder(val title: TextView, private val clickListener: OrderClickListener) :
    RecyclerView.ViewHolder(title), View.OnClickListener {

    init {
        title.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        clickListener.onOrderClick(v, adapterPosition)
    }

    interface OrderClickListener {
        fun onOrderClick(view :View?, position: Int)
    }
}