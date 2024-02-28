package com.fourinone.finalproject.block2.cielohotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.fourinone.finalproject.block2.cielohotel.MyAPI.UserServices
import com.fourinone.finalproject.block2.cielohotel.MyDataClass.ApiResponse
import com.fourinone.finalproject.block2.cielohotel.MyDataClass.Users
import com.fourinone.finalproject.block2.cielohotel.SignInandSignUp.SignInActivity
import com.fourinone.finalproject.block2.cielohotel.SignInandSignUp.SignUpActivity
import com.fourinone.finalproject.block2.cielohotel.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://campushive-com.preview-domain.com/public/api/"
    private val TAG:String = "Check-Response"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        installSplashScreen()
        setContentView(binding.root)

        getalluser()


        binding.button.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }



    private fun getalluser(){
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserServices::class.java)

        api.getData().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val users = apiResponse?.data
                    users?.let { userList ->
                        val myStringBuilder = StringBuilder()
                        for (user in userList) {
                            myStringBuilder.append("ID: ${user.id}\n")
                            myStringBuilder.append("Name: ${user.name}\n")
                            myStringBuilder.append("Email: ${user.email}\n")
                            myStringBuilder.append("User Type: ${user.usertype}\n")
                            myStringBuilder.append("Phone: ${user.phone}\n")
                            myStringBuilder.append("Created At: ${user.created_at}\n")
                            myStringBuilder.append("Updated At: ${user.updated_at}\n")
                            myStringBuilder.append("\n") // Add a separator between users
                        }
                        binding.txtId.text = myStringBuilder.toString()
                    }
                } else {
                    Log.e(TAG, "Unsuccessful response: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}", t)
            }
        })



    }
}
