package com.iitism.hackfestapp.ui.adminscanqr

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.zxing.integration.android.IntentIntegrator
import com.iitism.hackfestapp.R
import com.iitism.hackfestapp.databinding.FragmentAdminScanQrBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONException

class AdminScanQrFragment : Fragment() {

    companion object {
        fun newInstance() = AdminScanQrFragment()
    }

    private lateinit var viewModel: AdminScanQrViewModel
    private lateinit var binding : FragmentAdminScanQrBinding
    private lateinit var qrScanIntegrator : IntentIntegrator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminScanQrBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,AdminScanQrViewModelFactory(requireContext()))[(AdminScanQrViewModel::class.java)]
        qrScanIntegrator = IntentIntegrator.forSupportFragment(this)

        binding.scanQrButton.setOnClickListener {
            binding.loadingCard.loadingCard.visibility=View.VISIBLE
            binding.scanQrButton.visibility=View.INVISIBLE
            viewModel.setupScanner(qrScanIntegrator)
            viewModel.performAction(qrScanIntegrator)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            // If QRCode has no data.
            if (result.contents == null) {
                binding.loadingCard.loadingCard.visibility=View.INVISIBLE
                binding.scanQrButton.visibility=View.VISIBLE
                Toast.makeText(activity, "No Result Found", Toast.LENGTH_LONG).show()
            } else {
                // If QRCode contains data.
                try {
                    // Converting the data to json format
                    val url = result.contents.toString()
                    Log.d("Scan Qr", url)
//                    viewModel.markInOut(url)
//                    viewModel.setupScanner(qrScanIntegrator)
//                    viewModel.performAction(qrScanIntegrator)
                    lifecycleScope.launch(Dispatchers.IO) {
                        if(viewModel.isNetworkAvailable()){
                            val response = viewModel.markInOut(scannedtext = url)
                            if( response != null && response.isSuccessful){
                                Log.d("AdminScanQR","Gate Pass : ${response.body()?.message}")
                                lifecycleScope.launch (Dispatchers.Main){
                                    Toast.makeText(context, "Gate Pass : ${response.body()?.message}\"", Toast.LENGTH_SHORT).show()
                                }
                                viewModel.setupScanner(qrScanIntegrator)
                                viewModel.performAction(qrScanIntegrator)
                            }
                            else{
                                lifecycleScope.launch(Dispatchers.Main) {
                                    if(url[0]=='i')
                                        Toast.makeText(context, "Total Team Present", Toast.LENGTH_SHORT).show()
                                    else
                                        Toast.makeText(context, "Total Team Absent", Toast.LENGTH_SHORT).show()
                                }
                                viewModel.setupScanner(qrScanIntegrator)
                                viewModel.performAction(qrScanIntegrator)
                            }
                        }
                        else {
                            lifecycleScope.launch(Dispatchers.Main) {
                                Toast.makeText(context, "No Network", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    binding.loadingCard.loadingCard.visibility=View.INVISIBLE
                    binding.scanQrButton.visibility=View.VISIBLE
                    Toast.makeText(activity, result.contents, Toast.LENGTH_LONG).show()
                }
            }
        } else {
            binding.loadingCard.loadingCard.visibility=View.INVISIBLE
            binding.scanQrButton.visibility=View.VISIBLE
            super.onActivityResult(requestCode, resultCode, data)
        }
    }




}