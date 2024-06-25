package com.iitism.hackfestapp.ui.problemstatement

import androidx.annotation.Keep

@Keep
data class ProblemStatementModel(
    val name: String,
    val image_logo: String ,
    val problem_url: String
)