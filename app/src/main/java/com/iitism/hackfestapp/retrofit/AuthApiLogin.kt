package com.iitism.hackfestapp.retrofit

import com.iitism.hackfestapp.auth.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApiLogin {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("Player_Email") teamName:String,
        @Field("password")password:String
    ): LoginResponse
}