package com.example.passwordmanager_frontend.util

import android.util.Log
import com.example.passwordmanager_frontend.model.ErrorResponse
import com.google.gson.Gson
import okhttp3.ResponseBody

class ErrorLogger {
    fun logError(errorBody: ResponseBody): String? {
        try {
            val errorResponse = Gson().fromJson(errorBody.string(), ErrorResponse::class.java)
            val exception = errorResponse.exception
            val message = errorResponse.message

            Log.e("API Error", "Exception: $exception, Message: $message")

            return message
        } catch (exception: Exception) {
            Log.e("API Error", "Exception: $exception")
            return "API Error"
        }
    }
}