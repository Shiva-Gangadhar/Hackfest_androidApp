package com.iitism.hackfestapp.auth

import androidx.annotation.Keep

@Keep
data class LoginResponse(
    val `data`: Data,
    val message: String
)