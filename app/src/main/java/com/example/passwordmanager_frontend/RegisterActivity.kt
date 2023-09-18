package com.example.passwordmanager_frontend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.passwordmanager_frontend.api.ApiService
import com.example.passwordmanager_frontend.model.ClientRegistrationData
import com.example.passwordmanager_frontend.util.Constants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)

        findViewById<Button> (R.id.register_button).setOnClickListener {
            tryRegister()
        }

        findViewById<Button> (R.id.registerBack_button).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}

fun tryRegister() {
    val TAG = "tryRegister"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) // Use 10.0.2.2 to access your local server
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService = retrofit.create(ApiService::class.java)

    val customerData = ClientRegistrationData("John7", "jo", "ohhhh")

    val call = apiService.tryRegister(customerData)

    call.enqueue(object : Callback<ClientRegistrationData> {
        override fun onResponse(call: Call<ClientRegistrationData>, response: Response<ClientRegistrationData>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                // Handle the response here
            } else {
                // Handle an error response here
            }
        }
        override fun onFailure(call: Call<ClientRegistrationData>, t: Throwable) {
            // Handle network or request failure here
            Log.e(TAG, "Network request failed", t)
        }
    })
}