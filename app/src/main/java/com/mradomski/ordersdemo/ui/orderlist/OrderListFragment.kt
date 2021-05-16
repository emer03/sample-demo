package com.mradomski.ordersdemo.ui.orderlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mradomski.ordersdemo.R
import com.mradomski.ordersdemo.databinding.OrderListFragmentBinding
import com.mradomski.ordersdemo.repository.OrderDatabase
import com.mradomski.ordersdemo.ui.orderlist.recyclerview.OrderAdapter

class OrderListFragment : Fragment() {

    private lateinit var binding: OrderListFragmentBinding
    private lateinit var viewModel: OrderListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.order_list_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = OrderDatabase.getInstance(application).orderDatabaseDao
        val viewModelFactory = OrderListViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OrderListViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        val adapter = OrderAdapter()
        binding.orderList.adapter = adapter

        viewModel.orders.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }
}
