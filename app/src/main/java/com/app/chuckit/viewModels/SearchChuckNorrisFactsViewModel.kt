package com.app.chuckit.viewModels

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.chuckit.component
import com.app.chuckit.repository.NorrisRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchChuckNorrisFactsViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var norrisRepository: NorrisRepository

    init {
        getApplication<Application>().component.inject(this)
        getAllCategories()
        loadSearchSugestions()
    }

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>>
        get() = _categories

    private val _searchSugestions = MutableLiveData<List<String>>()
    val SearchSugestions : LiveData<List<String>>
        get() = _searchSugestions

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

    fun saveSearchSugestion(sugestion: String){
        viewModelScope.launch {
            norrisRepository.saveSearchSugestion(sugestion)
        }
    }
}