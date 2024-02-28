package com.fourinone.finalproject.block2.cielohotel.MyAPI

import com.fourinone.finalproject.block2.cielohotel.MyDataClass.ApiResponse
import com.fourinone.finalproject.block2.cielohotel.MyDataClass.RegistrationRequest
import com.fourinone.finalproject.block2.cielohotel.MyDataClass.SignInRequest
import com.fourinone.finalproject.block2.cielohotel.MyDataClass.Users
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserServices {
    @GET("users")
    fun getData(): Call<ApiResponse>

    @POST("users")
    fun registerUser(@Body request: RegistrationRequest): Call<ApiResponse>
}
