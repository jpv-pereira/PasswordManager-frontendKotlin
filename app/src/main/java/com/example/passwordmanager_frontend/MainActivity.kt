package com.example.passwordmanager_frontend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.passwordmanager_frontend.api.ApiService
import com.example.passwordmanager_frontend.model.ClientRegistrationData
import com.example.passwordmanager_frontend.model.TestData
import com.example.passwordmanager_frontend.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        findViewById<Button> (R.id.login_button).setOnClickListener {
            tryLogin(findViewById(R.id.test_text))
        }

        findViewById<Button> (R.id.go_register_button).setOnClickListener {
            //val intent = Intent(this, RegisterActivity::class.java)
            intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

    }
}

/*fun tryRegister(textView: TextView) {

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
                textView.text = "Response Success"
                // Handle the response here
            } else {
                textView.text = "Response Fail"
                // Handle an error response here
            }
        }
        override fun onFailure(call: Call<ClientRegistrationData>, t: Throwable) {
            // Handle network or request failure here
            textView.text = "FAFKAWFLWAFJLAFA"
        }
    })
}*/

fun tryLogin(textView: TextView) {
    val TAG = "omg"

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/") // Replace with your server URL
        .addConverterFactory(ScalarsConverterFactory.create()) // Use ScalarsConverterFactory for plain text response
        .build()
    val apiService = retrofit.create(ApiService::class.java)

    val call = apiService.tryTest()

    call.enqueue(object : Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                textView.text = "Response Success"
            } else {
                textView.text = "Response ????"
            }
        }

        override fun onFailure(call: Call<String>, t: Throwable) {
            textView.text = "Response Fail"
            Log.e(TAG, "Network request failed", t)
        }
    })
}