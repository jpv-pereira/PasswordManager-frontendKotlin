package com.example.passwordmanager_frontend.util

import android.graphics.Color
import com.example.passwordmanager_frontend.R

enum class NotificationType(val notificationType: Int) {
    Success(R.layout.notification_layout_success),
    Failure(R.layout.notification_layout_failure)
}