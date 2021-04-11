package com.app.chuckit.utils

object SearchSugestionsHelper {

    fun reverseOrderSearchHistory(searchSugestions: List<String>) : List<String>{
        return searchSugestions.asReversed()
    }

}