package com.app.chuckit.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.app.chuckit.repository.NorrisRepository
import javax.inject.Inject

class ChuckNorrisFactsViewModel : ViewModel() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var norrisRepository: NorrisRepository

}