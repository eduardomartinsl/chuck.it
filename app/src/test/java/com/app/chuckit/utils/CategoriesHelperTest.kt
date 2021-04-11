package com.app.chuckit.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CategoriesHelperTest{

    private val categories = listOf(
        "animal",
        "career",
        "celebrity",
        "dev",
        "explicit",
        "fashion",
        "food",
        "history",
        "money",
        "movie",
        "music",
        "political",
        "religion",
        "science",
        "sport",
        "travel"
    )

    @Test
    fun `garante que lista de categorias eh embaralhada`(){
        val shuffledCategories = CategoriesHelper.shuffleAndTakeFirstEightElements(categories)

        assertThat(shuffledCategories).isNotEqualTo(categories)
    }

    @Test
    fun `garante que oito categorias sao retornadas aleatoriamente`(){
        val shuffledCategories = CategoriesHelper.shuffleAndTakeFirstEightElements(categories)

        assertThat(shuffledCategories).hasSize(8)
    }

    @Test
    fun `garante que lista de categorias vazias retorna #uncategorized`() {
        val categories = emptyList<String>()
        val formatedCategories = NorrisFactHelper.formatCategories(categories)

        assertThat(formatedCategories).isEqualTo("#uncategorized")
    }

    @Test
    fun `garante que categorias retornam string formatada`() {
        val formatedCategories = NorrisFactHelper.formatCategories(categories)
        assertThat(formatedCategories).isEqualTo("#animal #career #celebrity #dev #explicit #fashion #food #history #money #movie #music #political #religion #science #sport #travel ")
    }
}