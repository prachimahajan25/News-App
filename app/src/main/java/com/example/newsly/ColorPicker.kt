package com.example.newsly

object ColorPicker{
    val colors= arrayOf("#FF5733", "#C70039", "#900C3F", "#581845", "#2E86C1", "#1E8449",
    "#27AE60", "#D4AC0D", "#F39C12", "#D35400", "#6C3483", "#A569BD",
    "#9B59B6", "#E74C3C", "#EA4335", "#FBBC05", "#8E44AD", "#3498DB",
    "#1ABC9C", "#2ECC71", "#FFA07A", "#FFD700", "#20B2AA", "#9370DB",
        )

    var colorIndex = 1
    fun getColor():String
    {
        return colors[colorIndex++% colors.size]
    }
}
