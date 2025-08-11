package com.example.thoughtoftheday.utils

import androidx.compose.ui.graphics.Color

object ColorUtil {
    fun fromHexOrDefault(hex: String, fallback: Color = Color.LightGray): Color {
        return try {
            Color(android.graphics.Color.parseColor(hex))
        } catch (e: IllegalArgumentException) {
            fallback
        }
    }
}
