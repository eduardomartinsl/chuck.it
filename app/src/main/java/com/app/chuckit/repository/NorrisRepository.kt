package com.app.chuckit.repository

import com.app.chuckit.db.dao.NorrisDao
import com.app.chuckit.models.ChuckNorrisFact
import com.app.chuckit.models.ChuckNorrisResultByQuery
import com.app.chuckit.repository.interfaces.BaseNorrisRepository
import com.app.chuckit.services.NorrisService
import com.app.chuckit.utils.Resource
import java.lang.Exception
import javax.inject.Inject

// TODO: Seria interessante reduzir essa repetição de código
class NorrisRepository @Inject constructor(
    private val norrisService: NorrisService,
    private val norrisDao: NorrisDao
) : BaseNorrisRepository {

    /////SERVICE////////////////////////////////////////////////////////////////////////////////////

    override suspend fun getRandomJoke(): Resource<ChuckNorrisFact> {
        return try {
            val response = norrisService.getRandomJoke()

            if (response.isSuccessful) {
                response.body().let {
                    return@let Resource.success(it)
                }
            }
            //
            else {
                Resource.error("Erro desconhecido", data = null)
            }

        } catch (e: Exception) {
            Resource.error(
                "Não foi possível encontrar o servidor. " +
                        "Verifique sua conexão e tente novamente",
                data = null
            )
        }
    }

    override suspend fun getRandomJokeByCategory(category: String): Resource<ChuckNorrisFact> {

        return try {
            val response = norrisService.getRandomJokeByCategory(category)

            if (response.isSuccessful) {
                response.body().let {
                    return@let Resource.success(it)
                }
            }
            //
            else {
                Resource.error("Erro desconhecido", data = null)
            }
        } catch (e: Exception) {
            Resource.error(
                "Não foi possível encontrar o servidor. " +
                        "Verifique sua conexão e tente novamente",
                data = null
            )
        }
    }

    override suspend fun getJokeCategories(): Resource<List<String>> {

        return try {
            val response = norrisService.getJokeCategories()

            if (response.isSuccessful) {
                response.body().let {
                    return@let Resource.success(it)
                }
            }
            //
            else {
                Resource.error("Erro desconhecido", data = null)
            }
        } catch (e: Exception) {
            Resource.error(
                "Não foi possível encontrar o servidor. " +
                        "Verifique sua conexão e tente novamente",
                data = null
            )
        }
    }

    override suspend fun getJokesWithQhery(query: String): Resource<ChuckNorrisResultByQuery> {

        return try {
            val response = norrisService.getJokesWithQhery(query)

            if (response.isSuccessful) {
                response.body().let {
                    return@let Resource.success(it)
                }
            }
            //
            else {
                Resource.error("Erro desconhecido", data = null)
            }
        } catch (e: Exception) {
            Resource.error(
                "Não foi possível encontrar o servidor. " +
                        "Verifique sua conexão e tente novamente",
                data = null
            )
        }
    }

    /////DAO////////////////////////////////////////////////////////////////////////////////////////

    override suspend fun selectAllChuckNorrisFacts() {
        norrisDao.selectAllChuckNorrisFacts()
    }
}