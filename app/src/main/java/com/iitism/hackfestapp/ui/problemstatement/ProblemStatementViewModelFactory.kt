package com.iitism.hackfestapp.ui.problemstatement

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iitism.hackfestapp.ui.aboutus.AboutUsRepository

class ProblemStatementViewModelFactory constructor(private val repository: AboutUsRepository,private val context : Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(ProblemStatementViewModel::class.java) -> ProblemStatementViewModel(this.repository,this.context) as T
            else->throw IllegalArgumentException("ViewModel not Found")
        }
    }
}