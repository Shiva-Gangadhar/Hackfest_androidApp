package com.iitism.hackfestapp.changePassword

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.room.InvalidationTracker
import com.iitism.hackfestapp.MainActivity
import com.iitism.hackfestapp.R
import com.iitism.hackfestapp.auth.Refractor.BaseFragment
import com.iitism.hackfestapp.auth.Refractor.LoginViewModel
import com.iitism.hackfestapp.auth.User
import com.iitism.hackfestapp.auth.authActivity
import com.iitism.hackfestapp.databinding.FragmentChangePasswordBinding
import com.iitism.hackfestapp.retrofit.Resource

class ChangePassword :BaseFragment<ChangeViewModel,FragmentChangePasswordBinding,ChangePassRespo>(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val intent=Intent(this.context, MainActivity::class.java)
        val progressBar=binding.changepasswordProgressBar.loadingCard

        viewModel.changeResponse.observe(viewLifecycleOwner,Observer {
            when(it){
                is Resource.Success->{
                    val sharedPref=this.activity?.getSharedPreferences("myPref",Context.MODE_PRIVATE)
                    Log.d("ChangePassword", it.value.message.toString())
                    Toast.makeText(requireContext(),"Password Changed Successfully", Toast.LENGTH_LONG).show()
                    val visibility=if(progressBar.visibility==View.GONE) View.VISIBLE
                    else View.GONE
                    progressBar.visibility=visibility
                    val edit=sharedPref?.edit()
                    edit?.clear()
                    edit?.apply()
                    startActivity(Intent(this.context, authActivity::class.java))
                    this.activity?.finish()
                }
                is Resource.Failure->{
                    val sharedPref=this.activity?.getSharedPreferences("myPref",Context.MODE_PRIVATE)
                    Toast.makeText(requireContext(),"Password Changed Successfully", Toast.LENGTH_LONG).show()
                    val visibility=if(progressBar.visibility==View.GONE) View.VISIBLE
                    else View.GONE
                    progressBar.visibility=visibility
                    val edit=sharedPref?.edit()
                    edit?.clear()
                    edit?.apply()
                    startActivity(Intent(this.context,authActivity::class.java))
                    this.activity?.finish()
                }
            }
        })
        binding.changePassButton.setOnClickListener {
            val sharedPref=this.activity?.getSharedPreferences("myPref", Context.MODE_PRIVATE)
            val email= sharedPref?.getString("email","");
            Log.d("email",email.toString())
            val oldPassword=binding.OldPasswordEdit.text.toString()
            val newPassword=binding.NewPasswordEdit.text.toString()
            val visibility=if(progressBar.visibility==View.GONE) View.VISIBLE
            else View.GONE
            progressBar.visibility=visibility

            if(oldPassword.isEmpty()){
                Toast.makeText(context,"Old Password is required",Toast.LENGTH_LONG).show()
                progressBar.visibility=View.INVISIBLE
            }
            else if(newPassword.isEmpty()){
                Toast.makeText(context,"New Password is required",Toast.LENGTH_LONG).show()
                progressBar.visibility=View.INVISIBLE
            }else{
                if (email != null) {
                    val sharedPref=this.activity?.getSharedPreferences("myPref",Context.MODE_PRIVATE)
                    if(sharedPref?.getString("password","")!=oldPassword) {
                        Toast.makeText(requireContext(), "Incorrect password", Toast.LENGTH_LONG).show()
                        progressBar.visibility=View.INVISIBLE
                    }
                    else
                        viewModel.changepassword(email,oldPassword,newPassword)
                }
            }
        }
    }
    override fun getViewModel()=ChangeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentChangePasswordBinding.inflate(inflater,container,false)

    override fun getFragmentRepository()= ChangePassRespo(remoteDataSource.BuildApi(PasswordChange::class.java))

}