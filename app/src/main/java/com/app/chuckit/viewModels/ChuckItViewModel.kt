package com.app.chuckit.viewModels

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.chuckit.R
import com.app.chuckit.component
import com.app.chuckit.models.NorrisFact
import com.app.chuckit.repository.NorrisRepository
import com.app.chuckit.utils.SearchSugestionsHelper
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

    private val _norrisFacts = MutableLiveData<List<NorrisFact>>()
    val norrisFacts: LiveData<List<NorrisFact>>
        get() = _norrisFacts

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

    private val _searchHistory = MutableLiveData<List<String>>()
    val searchHistory: LiveData<List<String>>
        get() = _searchHistory

    private val _loadingFactsError = MutableLiveData<String?>()
    val loadingNorrisFactsError: LiveData<String?>
        get() = _loadingFactsError

    fun loadSearchHistoryAndCategories() {
        loadSearchHistory()
        getAllCategories()
    }

    fun searchNorrisFactsWithQuery(query: String) {
        viewModelScope.launch {
            _isLoadingFacts.postValue(true)
            try {

                val norrisFacts = norrisRepository.searchNorrisFactsWithQuery(query)
                _norrisFacts.postValue(norrisFacts)
                if (norrisFacts.isEmpty()) {
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

    private fun loadSearchHistory() {
        viewModelScope.launch {
            val searchSugestions =
                norrisRepository.loadSearchHistory().also { searchSugestions ->
                    SearchSugestionsHelper.reverseOrderSearchHistory(searchSugestions)
                }
            _searchHistory.postValue(searchSugestions)
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

    fun saveSearchHistory(searchStr: String) {
        viewModelScope.launch {
            norrisRepository.saveSearchHistory(searchStr)
        }
    }

    fun getAllNorrisFacts() {
        viewModelScope.launch {
            _norrisFacts.postValue(norrisRepository.getAllNorrisFacts())
        }
    }
}