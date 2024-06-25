package com.iitism.hackfestapp.ui.profilefragment

import ProfileViewModelFactory
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.iitism.hackfestapp.R
import com.iitism.hackfestapp.auth.authActivity
import com.iitism.hackfestapp.databinding.FragmentProfileBinding
import com.iitism.hackfestapp.retrofit.Resource
import com.iitism.hackfestapp.ui.aboutus.AboutUsRepository
import com.iitism.hackfestapp.ui.aboutus.retrofit.RetrofitInstance
import com.iitism.hackfestapp.ui.problemstatement.ProblemStatementViewModel
import com.iitism.hackfestapp.ui.problemstatement.ProblemStatementViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_profile, container, false)
        binding=FragmentProfileBinding.bind(view)


        val sharedPref=this.activity?.getSharedPreferences("myPref",Context.MODE_PRIVATE)
        binding.teamName.text=sharedPref?.getString("teamName","")
        Log.d("sharedPref",sharedPref?.getString("teamName","").toString())
        val email = sharedPref?.getString("email","") ?: ""
        binding.Email.text= email
        binding.Organization.text=sharedPref?.getString("playerOrganization","")
        binding.Mobile.text=sharedPref?.getLong("playerMobile",0).toString()
        binding.nameText.text=sharedPref?.getString("playerName","").toString()
        binding.Attendance.text="Attendence : "+sharedPref?.getString("attendance","").toString()+" / "+sharedPref?.getString("team_length","").toString()
        binding.manHours.text="Man Hours : "+sharedPref?.getString("manHours","").toString()

        binding.logout.setOnClickListener {
            val edit=sharedPref?.edit()
            edit?.clear()
            edit?.apply()
            startActivity(Intent(this.context,authActivity::class.java))
            this.activity?.finish()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this, ProfileViewModelFactory(
                AboutUsRepository(
                    RetrofitInstance.api
                ),
                requireContext()
            )
        ).get(ProfileViewModel::class.java)
        val sharedPref=this.activity?.getSharedPreferences("myPref",Context.MODE_PRIVATE)
        val editor=sharedPref?.edit()
        val email = sharedPref?.getString("email","") ?: ""
        viewModel.profileResponse.observe(viewLifecycleOwner, Observer { it ->
            editor?.apply {
                putString("attendance",it.data.attendance_counter)
                putString("manHours", it.data.man_hours)
                apply()
            }
            binding.Attendance.text="Attendence : "+sharedPref?.getString("attendance","").toString()+" / "+sharedPref?.getString("team_length","").toString()
            binding.manHours.text="Man Hours : "+sharedPref?.getString("manHours","").toString()
        })

        binding.btnRefresh.setOnClickListener{
            binding.loadingCard.loadingCard.visibility=View.VISIBLE;
            lifecycleScope.launch {
                binding.btnRefresh.visibility = View.GONE
                if(viewModel.isNetworkAvailable()){
                    val response = viewModel.getProfileData(email)
                    var mess="Update Unsuccessfull"
                    if(response.isSuccessful){
                        mess="Update Successfull"
                    }
                    Toast.makeText(context, mess, Toast.LENGTH_SHORT).show()
                    Log.d("response", viewModel.profileResponse.toString())
                }
                else {
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, "No Network", Toast.LENGTH_SHORT).show()
                    }
                }
                binding.loadingCard.loadingCard.visibility=View.INVISIBLE;
                binding.btnRefresh.visibility = View.VISIBLE
            }
        }

    }

}