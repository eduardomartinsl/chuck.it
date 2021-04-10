package com.app.chuckit.utils

object SearchSugestionsHelper {

    fun reverseOrderSearchSugestions(searchSugestions: List<String>) : List<String>{
        return searchSugestions.asReversed()
    }

}