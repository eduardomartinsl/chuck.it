package com.app.chuckit.utils

import com.app.chuckit.utils.Constants.TEXT_LARGE_SIZE
import com.app.chuckit.utils.Constants.TEXT_NORMAL_SIZE
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class NorrisFactHelperTest {
    @Test
    fun `garante que se o tamanho do text eh maior que 80, tamanho da fonte eh normal`() {
        val textSize = NorrisFactHelper.getTextSize(100)
        assertThat(textSize).isEqualTo(TEXT_NORMAL_SIZE)
    }

    @Test
    fun `garante que se o tamanho do text eh menor que 80, tamanho da fonte eh grande`() {
        val textSize = NorrisFactHelper.getTextSize(60)
        assertThat(textSize).isEqualTo(TEXT_LARGE_SIZE)
    }

    @Test
    fun `garante que se o tamanho do text eh igual a 80, tamanho da fonte eh normal`() {
        val textSize = NorrisFactHelper.getTextSize(80)
        assertThat(textSize).isEqualTo(TEXT_NORMAL_SIZE)
    }
}