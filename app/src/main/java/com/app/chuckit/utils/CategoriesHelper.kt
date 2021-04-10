package com.app.chuckit.utils

object CategoriesHelper {
    fun shuffleAndTakeFirstEightElements(categories: List<String>) =
        categories.shuffled().take(8)
}