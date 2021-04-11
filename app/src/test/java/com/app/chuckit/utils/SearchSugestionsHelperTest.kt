package com.app.chuckit.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SearchSugestionsHelperTest {

    @Test
    fun `garante que lista de searchSugestions eh invertida`() {

        val searchSugestions = listOf(
            "Star Wars",
            "github",
            "trump",
        )

        val reversedSearchSugestions =
            SearchSugestionsHelper.reverseOrderSearchHistory(searchSugestions)

        assertThat(reversedSearchSugestions[0]).isEqualTo("trump")
    }
}