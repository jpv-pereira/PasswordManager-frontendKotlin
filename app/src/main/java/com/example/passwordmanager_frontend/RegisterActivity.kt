package com.example.passwordmanager_frontend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.example.passwordmanager_frontend.api.ApiService
import com.example.passwordmanager_frontend.model.ClientRegistrationData
import com.example.passwordmanager_frontend.util.Constants.Companion.BASE_URL
import com.example.passwordmanager_frontend.util.ErrorLogger
import com.example.passwordmanager_frontend.util.Notification
import com.example.passwordmanager_frontend.util.NotificationType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : ComponentActivity() {
    private lateinit var notification: Notification
    private lateinit var errorLogger: ErrorLogger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)
        notification = Notification(this)
        errorLogger = ErrorLogger()

        findViewById<Button> (R.id.register_button).setOnClickListener {
            val username = findViewById<EditText> (R.id.register_username).text.toString()
            val email = findViewById<EditText> (R.id.register_email).text.toString()
            val password = findViewById<EditText> (R.id.register_password).text.toString()
            Log.e("Button click", "I've been clicked once")

            tryRegister(username, email, password)
        }

        findViewById<Button> (R.id.registerBack_button).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun tryRegister(username: String, email: String, password: String) {
        val TAG = "tryRegister"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL) // 10.0.2.2 local server
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)

        val customerData = ClientRegistrationData(username, email, password)

        val call = apiService.tryRegister(customerData)

        call.enqueue(object : Callback<ClientRegistrationData> {
            override fun onResponse(call: Call<ClientRegistrationData>, response: Response<ClientRegistrationData>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    notification.showPopup("Registered Successfully", NotificationType.Success)

                } else {
                    val errorMessage = response.errorBody()?.let { errorLogger.logError(it) }
                    if(!errorMessage.isNullOrEmpty()) {
                        notification.showPopup(errorMessage, NotificationType.Failure)
                    } else {
                        notification.showPopup("Registration Failed", NotificationType.Failure)
                    }
                }
            }
            override fun onFailure(call: Call<ClientRegistrationData>, t: Throwable) {
                notification.showPopup("Critical Failure", NotificationType.Success)
                Log.e(TAG, "Network request failed", t)
            }
        })
    }


}

