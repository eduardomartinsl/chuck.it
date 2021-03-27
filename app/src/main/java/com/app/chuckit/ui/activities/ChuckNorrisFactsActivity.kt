package com.app.chuckit.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.app.chuckit.R
import com.app.chuckit.component
import com.app.chuckit.viewModels.ChuckNorrisFactsViewModel

// TODO: Por enquanto vou deixar duas activities.
//  Mais pra frente vou migrar as telas para fragments
class ChuckNorrisFactsActivity : AppCompatActivity() {

    private val chuckNorrisFactsViewModel by viewModels<ChuckNorrisFactsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chuck_norris_facts)

        application.component.inject(this)
    }
}