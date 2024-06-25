package com.iitism.hackfestapp.ui.profilefragment

import androidx.annotation.Keep
import com.iitism.hackfestapp.auth.Data

@Keep
data class ProfileResponse(
    val `data`: Data,
    val message: String
)