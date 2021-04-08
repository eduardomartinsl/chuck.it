package com.app.chuckit.utils

import com.app.chuckit.utils.Constants.CHARACTER_LIMIT_TO_UPPERCASE
import com.app.chuckit.utils.Constants.TEXT_LARGE_SIZE
import com.app.chuckit.utils.Constants.TEXT_NORMAL_SIZE

object ChuckNorrisFactHelper {
    fun getTextSize(textLenght: Int): Float {
        return if (textLenght < CHARACTER_LIMIT_TO_UPPERCASE) {
            TEXT_LARGE_SIZE
        } else {
            TEXT_NORMAL_SIZE
        }
    }
}