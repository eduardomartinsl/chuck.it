package com.app.chuckit.viewModels

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.chuckit.component
import com.app.chuckit.models.ChuckNorrisFact
import com.app.chuckit.repository.NorrisRepository
import kotlinx.coroutines.launch
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

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>>
        get() = _categories

    private val _searchSugestions = MutableLiveData<List<String>>()
    val searchSugestions: LiveData<List<String>>
        get() = _searchSugestions

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

            } catch (exception: Exception) {

                Log.e("searchException", exception.toString())
            } finally {
                _isLoadingFacts.postValue(false)
            }
        }
    }

    private fun loadSearchSugestions() {
        viewModelScope.launch {
            val searchSugestions = norrisRepository.loadSearchSugestions()
            _searchSugestions.postValue(searchSugestions)
        }
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            _isLoadingCategories.postValue(true)
            val categories = norrisRepository.getCategories()
            _categories.postValue(categories)
            _isLoadingCategories.postValue(false)
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