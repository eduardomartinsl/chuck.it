package com.app.chuckit.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.chuckit.R
import com.app.chuckit.component
import com.app.chuckit.viewModels.SearchChuckNorrisFactsViewModel

// TODO: Por enquanto vou deixar duas activities.
//  Mais pra frente vou migrar as telas para fragments
class SearchChuckNorrisFactsActivity : AppCompatActivity() {

    private val searchChuckNorrisFactsViewModel by viewModels<SearchChuckNorrisFactsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_chuck_norris_facts)

        application.component.inject(this)
    }
}