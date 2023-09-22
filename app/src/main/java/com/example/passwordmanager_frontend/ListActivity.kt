package com.example.passwordmanager_frontend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.ScrollView
import androidx.activity.ComponentActivity
import com.example.passwordmanager_frontend.util.ErrorLogger
import com.example.passwordmanager_frontend.util.Notification

class ListActivity : ComponentActivity() {
    //private lateinit var notification: Notification
   // private lateinit var errorLogger: ErrorLogger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        //notification = Notification(this)
        //errorLogger = ErrorLogger()

        val inflater = LayoutInflater.from(this)
        val passwordEntry = inflater.inflate(R.layout.password_layout_entry, findViewById(R.id.password_scrollview))
        //findViewById<ScrollView>(R.id.password_scrollview).addView(passwordEntry)



    }
}