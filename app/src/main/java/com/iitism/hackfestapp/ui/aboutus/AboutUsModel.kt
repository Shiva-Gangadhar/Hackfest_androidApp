package com.iitism.hackfestapp.ui.aboutus

import androidx.annotation.Keep

@Keep
data class AboutUsModel(
    val _id: String,
    val name: String,
    val position: String,
    val image: String,
    val insta_url: String,
    val linkedin_url: String,
    val id: String,
)