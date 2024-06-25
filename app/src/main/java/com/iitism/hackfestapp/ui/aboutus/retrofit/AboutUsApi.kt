package com.iitism.hackfestapp.ui.aboutus.retrofit

import com.iitism.hackfestapp.auth.LoginResponse
import com.iitism.hackfestapp.ui.aboutus.AboutUsModel
import com.iitism.hackfestapp.ui.adminscanqr.retrofit.adminScanResponse
import com.iitism.hackfestapp.ui.noticeboardfragment.NoticeBoardModel
import com.iitism.hackfestapp.ui.problemstatement.ProblemStatementModel
import com.iitism.hackfestapp.ui.profilefragment.ProfileResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AboutUsApi {

    @GET("organizing")
    suspend fun getAllOrganizers() : Response<List<AboutUsModel>>

    @GET("{endpoint}")
    suspend fun getAllNotices(
        @Path(value = "endpoint",encoded = true)endpoint:String?
    ) : Response<List<NoticeBoardModel>>

    @GET("problem")
    suspend fun getAllProblems() : Response<List<ProblemStatementModel>>

    @FormUrlEncoded
    @POST("man_hours")
    suspend fun getProfile(
        @Field("Player_Email") email:String
    ): Response<ProfileResponse>
}