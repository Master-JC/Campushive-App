package com.fourinone.finalproject.block2.cielohotel.SignInandSignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fourinone.finalproject.block2.cielohotel.MainActivity
import com.fourinone.finalproject.block2.cielohotel.MyAPI.AuthService
import com.fourinone.finalproject.block2.cielohotel.MyAPI.UserServices
import com.fourinone.finalproject.block2.cielohotel.MyDataClass.ApiResponse
import com.fourinone.finalproject.block2.cielohotel.MyDataClass.SignInRequest
import com.fourinone.finalproject.block2.cielohotel.R
import com.fourinone.finalproject.block2.cielohotel.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private val BASE_URL = "https://campushive-com.preview-domain.com/public/api/"
    private val authService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signIn(email, password)
            } else {
                // Display an error message if any field is empty
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun signIn(email: String, password: String) {
        val request = SignInRequest(email, password)
        authService.signIn(request).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Sign-in failed, handle response accordingly
                    Toast.makeText(
                        this@SignInActivity,
                        "Sign-in failed: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Handle failure to connect to the server
                Toast.makeText(
                    this@SignInActivity,
                    "Sign-in failed: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}