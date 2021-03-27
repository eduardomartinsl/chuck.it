package com.app.chuckit.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.app.chuckit.db.NorrisDatabase
import com.app.chuckit.db.entities.ChuckNorrisFactsEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//@SmallTest se referem a testes unitários
//@MediumTest se referem a testes integrados
//@LargeTest se referem a testes de UI

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
    fun closeNorrisDatabase(){
        norrisDatabase.close()
    }

    @Test
    fun garante_que_database_esta_vazia() = runBlockingTest {
        val chuckNorrisFactsList = norrisDao.selectAllChuckNorrisFacts()
        assertThat(chuckNorrisFactsList).isEmpty()
    }

    @Test
    fun garante_que_chuckNorrisFact_foi_inserido_em_database()  = runBlockingTest {

        val chuckNorrisFact = ChuckNorrisFactsEntity(
            id = "id1",
            categories =  mutableListOf(),
            createdAt = "data de criação",
            iconURL = "url do Icone",
            updatedAt = "Data de update",
            url = "url",
            value = "valor"
        )

        norrisDao.insertChuckNorrisFact(chuckNorrisFact)

        val chuckNorrisFactsList = norrisDao.selectAllChuckNorrisFacts()
        assertThat(chuckNorrisFactsList).contains(chuckNorrisFact)
    }

    @Test
    fun garante_que_chuckNorrisFact_foi_removido_de_database()  = runBlockingTest {

        val chuckNorrisFact = ChuckNorrisFactsEntity(
            id = "id1",
            categories =  mutableListOf(),
            createdAt = "data de criação",
            iconURL = "url do Icone",
            updatedAt = "Data de update",
            url = "url",
            value = "valor"
        )

        norrisDao.insertChuckNorrisFact(chuckNorrisFact)
        norrisDao.deleteChuckNorrisFact(chuckNorrisFact)

        val chuckNorrisFactsList = norrisDao.selectAllChuckNorrisFacts()

        assertThat(chuckNorrisFactsList).doesNotContain(chuckNorrisFact)
    }
}