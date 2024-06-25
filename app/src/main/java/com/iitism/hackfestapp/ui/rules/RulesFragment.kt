package com.iitism.hackfestapp.ui.rules

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.iitism.hackfestapp.R
import com.iitism.hackfestapp.databinding.FragmentRulesBinding

class RulesFragment : Fragment() {

    private lateinit var binding: FragmentRulesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRulesBinding.inflate(inflater)

        val sharedPref=this.activity?.getSharedPreferences("myPref", Context.MODE_PRIVATE)

        binding.webview.webViewClient = WebViewClient()
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.settings.setSupportZoom(true)
        binding.webview.loadUrl(sharedPref?.getString("rules","").toString())

        return binding.root
    }

}