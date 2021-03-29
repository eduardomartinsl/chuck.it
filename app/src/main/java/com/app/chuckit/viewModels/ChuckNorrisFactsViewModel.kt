package com.app.chuckit.viewModels

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.chuckit.component
import com.app.chuckit.models.ChuckNorrisFact
import com.app.chuckit.repository.NorrisRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChuckNorrisFactsViewModel(application: Application) : AndroidViewModel(application) {

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
}