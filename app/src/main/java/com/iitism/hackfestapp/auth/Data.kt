package com.iitism.hackfestapp.auth

import androidx.annotation.Keep

@Keep
data class Data(
    val Country: String,
    val Domain: String,
    val Player_Mobile: Long,
    val Player_Name: String,
    val Player_Email: String,
    val Player_Organisation: String,
    val Player_Type: String,
    val man_hours : String,
    val attendance_counter:String,
    val Team_Id: String,
    val team_length: String,
    val rules:String,
    val Team_Name: String,
    val password: String,
    val problem_statement_and_solution: String,
)