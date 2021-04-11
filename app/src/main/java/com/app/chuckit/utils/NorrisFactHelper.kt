package com.app.chuckit.utils

import com.app.chuckit.utils.Constants.CHARACTER_LIMIT_TO_UPPERCASE
import com.app.chuckit.utils.Constants.TEXT_LARGE_SIZE
import com.app.chuckit.utils.Constants.TEXT_NORMAL_SIZE

object NorrisFactHelper {
    fun getTextSize(textLenght: Int): Float {
        return if (textLenght < CHARACTER_LIMIT_TO_UPPERCASE) {
            TEXT_LARGE_SIZE
        } else {
            TEXT_NORMAL_SIZE
        }
    }

    fun formatCategories(categories: List<String>): String {
        if (categories.isEmpty()) return "#uncategorized"

        val stringBuilder = StringBuilder()

        for (category in categories) {
            stringBuilder.append("#$category ")
        }
        return stringBuilder.toString()
    }
}