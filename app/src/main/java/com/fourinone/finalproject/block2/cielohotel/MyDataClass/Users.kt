package com.fourinone.finalproject.block2.cielohotel.MyDataClass



data class Users(
    val id: Int,
    val name:String,
    val email:String,
    val phone:String,
    val password:String,
    val usertype:String,
    val email_verified_at: String,
    val two_factor_secret: String,
    val two_factor_recovery_codes: String,
    val two_factor_confirmed_at:String,
    val current_team_id: String,
    val remember_token: String,
    val profile_photo_path: String,
    val created_at:String,
    val updated_at: String
)
