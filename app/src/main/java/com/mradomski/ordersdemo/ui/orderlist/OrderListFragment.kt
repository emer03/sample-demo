package com.mradomski.ordersdemo.ui.orderlist

import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mradomski.ordersdemo.R
import com.mradomski.ordersdemo.database.OrderDatabase
import com.mradomski.ordersdemo.databinding.OrderListFragmentBinding
import com.mradomski.ordersdemo.domain.Order
import com.mradomski.ordersdemo.ui.orderlist.recyclerview.OrderAdapter
import com.mradomski.ordersdemo.ui.webview.WebViewFragmentDirections

class OrderListFragment : Fragment() {

    private lateinit var binding: OrderListFragmentBinding
    private lateinit var viewModel: OrderListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initialize(inflater, container)
        configureRecyclerView()
        initializeBindings()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.order_list_menu, menu)
        val refreshItem = menu.findItem(R.id.action_refresh) ?: null
        viewModel.disableRefresh.observe(viewLifecycleOwner) { disabled ->
            refreshItem?.isEnabled = !disabled
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                viewModel.refreshOrders()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initialize(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.order_list_fragment, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = OrderDatabase.getInstance(application).orderDatabaseDao
        val viewModelFactory = OrderListViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OrderListViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun configureRecyclerView() {
        val adapter = OrderAdapter(this::orderListClickListener)
        binding.orderList.adapter = adapter
        viewModel.orders.observe(viewLifecycleOwner) {
            it?.let {
                adapter.data = it
            }
        }
    }

    private fun initializeBindings() {
        viewModel.showNetworkError.observe(viewLifecycleOwner) {
            if (it) {
                Snackbar.make(binding.orderList, R.string.network_error, Snackbar.LENGTH_SHORT)
                    .show()
                viewModel.networkErrorCompleted()
            }
        }

        viewModel.displayOrder.observe(viewLifecycleOwner) {
            if (it != null) {
                showOrderView(it)
                viewModel.displayOrderCompleted()
            }
        }
    }

    private fun orderListClickListener(order: Order) {
        viewModel.setDisplayOrder(order)
    }

    private fun showOrderView(order: Order) {
        val isMasterDetailView = resources.getBoolean(R.bool.isTablet)
        if (isMasterDetailView) {
            Navigation.findNavController(context as Activity, R.id.nav_host_detail_fragment)
                .navigate(WebViewFragmentDirections.actionWebViewFragment2Self(order.url))
        } else {
            view?.findNavController()?.navigate(
                OrderListFragmentDirections.actionOrderListFragmentToWebViewFragment(order.url)
            )
        }
    }
}
