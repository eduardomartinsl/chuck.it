package com.app.chuckit.viewModels

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.*
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

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>>
        get() = _categories

    private val _searchSugestions = MutableLiveData<List<String>>()
    val searchSugestions: LiveData<List<String>>
        get() = _searchSugestions

    fun searchChuckNorrisFactsWithQuery(query: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val chuckNorrisFacts = norrisRepository.searchChuckNorrisFactsWithQuery(query)
                _chuckNorrisFacts.postValue(chuckNorrisFacts)
            } catch (exception: Exception) {

            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun loadSearchSugestionsAndCategories(){
        loadSearchSugestions()
        getAllCategories()
    }

    private fun loadSearchSugestions() {
        viewModelScope.launch {
            val searchSugestions = norrisRepository.loadSearchSugestions()
            _searchSugestions.postValue(searchSugestions)
        }
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            val categories = norrisRepository.getAllCategories()
            _categories.postValue(categories)
        }
    }

    fun saveSearchSugestion(sugestion: String) {
        viewModelScope.launch {
            norrisRepository.saveSearchSugestion(sugestion)
        }
    }

}