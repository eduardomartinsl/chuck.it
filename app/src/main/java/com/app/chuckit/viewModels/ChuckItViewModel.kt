package com.app.chuckit.viewModels

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.chuckit.R
import com.app.chuckit.component
import com.app.chuckit.models.ChuckNorrisFact
import com.app.chuckit.repository.NorrisRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject


class ChuckItViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var norrisRepository: NorrisRepository

    init {
        getApplication<Application>().component.inject(this)
    }

    private val _chuckNorrisFacts = MutableLiveData<List<ChuckNorrisFact>>()
    val chuckNorrisFacts: LiveData<List<ChuckNorrisFact>>
        get() = _chuckNorrisFacts

    private val _isLoadingFacts = MutableLiveData(false)
    val isLoadingFacts: LiveData<Boolean>
        get() = _isLoadingFacts

    private val _isLoadingCategories = MutableLiveData(false)
    val isLoadingCategories: LiveData<Boolean>
        get() = _isLoadingCategories

    private val _areCategoriesAvaliable = MutableLiveData<Boolean?>(null)
    val areCategoriesAvaliable: LiveData<Boolean?>
        get() = _areCategoriesAvaliable

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>>
        get() = _categories

    private val _searchSugestions = MutableLiveData<List<String>>()
    val searchSugestions: LiveData<List<String>>
        get() = _searchSugestions

    private val _loadingFactsError = MutableLiveData<String?>()
    val loadingFactsError: LiveData<String?>
        get() = _loadingFactsError

    fun loadSearchSugestionsAndCategories() {
        loadSearchSugestions()
        getAllCategories()
    }

    fun searchChuckNorrisFactsWithQuery(query: String) {
        viewModelScope.launch {
            _isLoadingFacts.postValue(true)
            try {

                val chuckNorrisFacts = norrisRepository.searchChuckNorrisFactsWithQuery(query)
                _chuckNorrisFacts.postValue(chuckNorrisFacts)
                if (chuckNorrisFacts.isEmpty()) {
                    val emptySearchResult =
                        getApplication<Application>().resources.getString(R.string.empty_search_result)
                    loadingFactsErrorPostValue(emptySearchResult)
                }

            } catch (exception: Exception) {

                val genericProblemMessage =
                    getApplication<Application>().resources.getString(R.string.generic_problem)
                loadingFactsErrorPostValue(genericProblemMessage)

            } catch (exception: UnknownHostException) {

                val connectionProblemMessage =
                    getApplication<Application>().resources.getString(R.string.connection_problem)
                loadingFactsErrorPostValue(connectionProblemMessage)

            } finally {
                _isLoadingFacts.postValue(false)
            }
        }
    }

    private fun loadingFactsErrorPostValue(emptySearchResult: String) {
        _loadingFactsError.postValue(emptySearchResult)
        viewModelScope.launch {
            delay(500)
            _loadingFactsError.postValue(null)
        }
    }

    private fun loadSearchSugestions() {
        viewModelScope.launch {
            val searchSugestions = norrisRepository.loadSearchSugestions()
            _searchSugestions.postValue(searchSugestions)
        }
    }

    fun getAllCategories() {
        viewModelScope.launch {
            _isLoadingCategories.postValue(true)
            try {
                val categories = norrisRepository.getCategories()
                _categories.postValue(categories)
                _areCategoriesAvaliable.postValue(true)
            } catch (e: Exception) {
                _areCategoriesAvaliable.postValue(false)
                delay(500)
                _areCategoriesAvaliable.postValue(null)
            } finally {
                _isLoadingCategories.postValue(false)
            }
        }
    }

    fun saveSearchSugestion(sugestion: String) {
        viewModelScope.launch {
            norrisRepository.saveSearchSugestion(sugestion)
        }
    }

    fun getAllNorrisFacts() {
        viewModelScope.launch {
            _chuckNorrisFacts.postValue(norrisRepository.getAllNorrisFacts())
        }
    }
}