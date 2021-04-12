package com.app.chuckit.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.app.chuckit.db.NorrisDatabase
import com.app.chuckit.db.entities.CategoryEntity
import com.app.chuckit.db.entities.NorrisFactsEntity
import com.app.chuckit.db.entities.SearchHistoryEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class NorrisDaoTest {

    // InstantTaskExecutorRule garante que os jobs são feitos sequencialmente.
    // Evita o erro
    // IllegalStateException: This job has not completed yet at kotlinx.coroutines.JobSupport
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var norrisDatabase: NorrisDatabase
    private lateinit var norrisDao: NorrisDao

    @Before
    fun initializeNorrisDatabase() {
        // inMemoryDatabaseBuilder utilizado para criar um banco de dados volátil
        // allowMainThreadQueries permite com que o builder seja construído na MainThread
        norrisDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NorrisDatabase::class.java
        ).allowMainThreadQueries().build()

        norrisDao = norrisDatabase.norrisDao()
    }

    @After
    fun closeNorrisDatabase() {
        norrisDatabase.close()
    }

    @Test
    fun garante_que_database_nao_possui_chuck_norris_facts() = runBlockingTest {
        val norrisFactsList = norrisDao.selectAllNorrisFacts()
        assertThat(norrisFactsList).isEmpty()
    }

    @Test
    fun garante_que_chuckNorrisFact_foi_inserido_em_database() = runBlockingTest {

        val norrisFact = NorrisFactsEntity(
            id = "id1",
            categories = mutableListOf(),
            createdAt = "data de criação",
            iconURL = "url do Icone",
            updatedAt = "Data de update",
            url = "url",
            value = "valor"
        )

        norrisDao.insertNorrisFact(norrisFact)

        val norrisFactsList = norrisDao.selectAllNorrisFacts()
        assertThat(norrisFactsList).contains(norrisFact)
    }

    @Test
    fun garante_que_chuckNorrisFact_foi_removido_de_database() = runBlockingTest {

        val norrisFact = NorrisFactsEntity(
            id = "id1",
            categories = mutableListOf(),
            createdAt = "data de criação",
            iconURL = "url do Icone",
            updatedAt = "Data de update",
            url = "url",
            value = "valor"
        )

        norrisDao.insertNorrisFact(norrisFact)
        norrisDao.deleteNorrisFact(norrisFact)

        val norrisFactsList = norrisDao.selectAllNorrisFacts()

        assertThat(norrisFactsList).doesNotContain(norrisFact)
    }

    @Test
    fun garante_que_search_sugestion_foi_inserido_em_database() = runBlockingTest {

        val searchSugestion = SearchHistoryEntity(
            id = 1,
            value = "searchSugestionValueTest"
        )

        norrisDao.insertSearchHistory(searchSugestion)

        val searchSugestions = norrisDao.selectAllSearchHistory()

        assertThat(searchSugestions).contains(searchSugestion)
    }

    @Test
    fun garante_que_categories_foram_inseridas_em_database() = runBlockingTest {

        val categories = listOf(
            CategoryEntity(id = 1, value = "categoria 1"),
            CategoryEntity(id = 2, value = "categoria 2"),
            CategoryEntity(id = 3, value = "categoria 3")
        )

        for (category in categories)
            norrisDao.insertCategories(category)

        val categoriesFromPersistence = norrisDao.selectAllCategories()

        assertThat(categoriesFromPersistence).isEqualTo(categories)
    }

    @Test
    fun garante_que_chuck_norris_facts_esta_limpo_apos_deletar_registros() = runBlockingTest {
        val Norrisfacts = listOf(
            NorrisFactsEntity(
                id = "id1",
                categories = mutableListOf(),
                createdAt = "data de criação",
                iconURL = "url do Icone",
                updatedAt = "Data de update",
                url = "url",
                value = "valor"
            ),
            NorrisFactsEntity(
                id = "id2",
                categories = mutableListOf(),
                createdAt = "data de criação",
                iconURL = "url do Icone",
                updatedAt = "Data de update",
                url = "url",
                value = "valor"
            ),
            NorrisFactsEntity(
                id = "id3",
                categories = mutableListOf(),
                createdAt = "data de criação",
                iconURL = "url do Icone",
                updatedAt = "Data de update",
                url = "url",
                value = "valor"
            )
        )

        for (norrisFact in Norrisfacts)
            norrisDao.insertNorrisFact(norrisFact)

        norrisDao.deleteAllFromNorrisFact()

        val norrisFactsFromPersistance = norrisDao.selectAllNorrisFacts()

        assertThat(norrisFactsFromPersistance).isEmpty()
    }

    @Test
    fun garante_que_search_sugestion_nao_foi_inserido_duas_vezes() = runBlockingTest {
        val searchSugestion = SearchHistoryEntity(
            id = 1,
            value = "searchSugestionValueTest"
        )

        norrisDao.insertSearchHistory(searchSugestion)
        norrisDao.insertSearchHistory(searchSugestion)

        val searchSugestions = norrisDao.selectAllSearchHistory()

        assertThat(searchSugestions).containsNoDuplicates()
    }
}