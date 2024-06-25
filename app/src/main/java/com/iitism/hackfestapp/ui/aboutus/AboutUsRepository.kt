package com.iitism.hackfestapp.ui.aboutus

import com.iitism.hackfestapp.auth.Refractor.BaseRepository
import com.iitism.hackfestapp.ui.aboutus.retrofit.AboutUsApi
import com.iitism.hackfestapp.ui.profilefragment.ProfileResponse
import retrofit2.Response

class AboutUsRepository constructor(private val api: AboutUsApi) {
    suspend fun getAllOrganizers() = api.getAllOrganizers()

    suspend fun getAllNotices(end:String) = api.getAllNotices(endpoint = "announcement/"+end)

    suspend fun getAllProblems() = api.getAllProblems()

    suspend fun getProfile(email : String) : Response<ProfileResponse> = api.getProfile(email = email)

}