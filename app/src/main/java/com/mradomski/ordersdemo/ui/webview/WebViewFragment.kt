package com.mradomski.ordersdemo.ui.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mradomski.ordersdemo.R
import com.mradomski.ordersdemo.databinding.WebViewFragmentBinding

class WebViewFragment : Fragment() {

    private lateinit var binding: WebViewFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.web_view_fragment,
            container,
            false
        )
        binding.webView.webViewClient = WebViewClient();

        arguments?.get("url")?.let {
            val args = WebViewFragmentArgs.fromBundle(requireArguments())
            binding.webView.loadUrl(args.url)
        }

        return binding.root
    }
}
