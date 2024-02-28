package com.fourinone.finalproject.block2.cielohotel.SignInandSignUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fourinone.finalproject.block2.cielohotel.MyAPI.UserServices
import com.fourinone.finalproject.block2.cielohotel.MyDataClass.ApiResponse
import com.fourinone.finalproject.block2.cielohotel.MyDataClass.RegistrationRequest
import com.fourinone.finalproject.block2.cielohotel.R
import com.fourinone.finalproject.block2.cielohotel.databinding.ActivitySignUpBinding
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private val BASE_URL = "https://campushive-com.preview-domain.com/public/api/"
    private val TAG = "SignUpActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val name = binding.etUsername.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() &&
                password.isNotEmpty() && confirmPassword.isNotEmpty()
            ) {
                if (password == confirmPassword) {
                    registerUser(name, email, phone, password)
                } else {
                    // Display an error message if passwords do not match
                    Log.e(TAG, "Passwords do not match")
                }
            } else {
                // Display an error message if any field is empty
                Log.e(TAG, "All fields are required")
            }
        }
    }

    private fun registerUser(name: String, email: String, phone: String, password: String) {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserServices::class.java)

        val request = RegistrationRequest(name, email, phone, password)

        api.registerUser(request).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    // Registration successful, handle response accordingly
                    // For example, navigate to the login screen
                    Log.d(TAG, "Registration successful")
                } else {
                    // Registration failed, handle response accordingly
                    Log.e(TAG, "Registration failed: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Handle failure to connect to the server
                Log.e(TAG, "Registration failed: ${t.message}", t)
            }
        })
    }



}