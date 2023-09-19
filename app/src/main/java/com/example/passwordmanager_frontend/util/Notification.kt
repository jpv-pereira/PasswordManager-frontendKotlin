package com.example.passwordmanager_frontend.util

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import com.example.passwordmanager_frontend.R

class Notification (private val context: Context) {
    fun showPopup(message: String, type: NotificationType) {
        val inflater = LayoutInflater.from(context)
        val popupView = inflater.inflate(type.notificationType, null)

        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        val popupMessage = popupView.findViewById<TextView>(R.id.notification_text)
        val closeButton = popupView.findViewById<Button>(R.id.notification_button)

        popupMessage.text = message

        popupWindow.setAnimationStyle(R.style.Animation)


        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }


        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)
    }

}