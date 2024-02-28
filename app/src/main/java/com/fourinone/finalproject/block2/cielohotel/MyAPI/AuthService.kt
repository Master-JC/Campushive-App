package com.fourinone.finalproject.block2.cielohotel.MyAPI

import com.fourinone.finalproject.block2.cielohotel.MyDataClass.ApiResponse
import com.fourinone.finalproject.block2.cielohotel.MyDataClass.SignInRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("signin")
    fun signIn(@Body request: SignInRequest): Call<ApiResponse>
}