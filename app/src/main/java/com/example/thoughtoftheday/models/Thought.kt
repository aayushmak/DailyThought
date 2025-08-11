package com.example.thoughtoftheday.models

import java.util.UUID

data class Thought(
    val id: String = UUID.randomUUID().toString(),
    val content: String = "",
    val createdBy: String = "",
    val createdAt: Any = System.currentTimeMillis(),
    val hexColor: String = "#FFFFFF",
    val emoji: String? = null
) {
    companion object {
        fun emojiFromColor(hex: String): String {
            return when (hex.uppercase()) {
                "#FFD700", "#FFEB3B" -> "🌞"
                "#ADD8E6", "#87CEEB" -> "🌧️"
                "#98FB98", "#90EE90" -> "🌱"
                "#FFC0CB", "#FF69B4" -> "💖"
                "#A9A9A9", "#808080" -> "🫥"
                "#FFA07A", "#FF6347" -> "🔥"
                "#9370DB", "#8A2BE2" -> "💭"
                else -> "😌"
            }
        }
    }
}

