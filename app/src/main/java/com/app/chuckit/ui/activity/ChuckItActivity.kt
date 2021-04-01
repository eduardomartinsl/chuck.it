package com.app.chuckit.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.chuckit.R
import com.app.chuckit.component

class ChuckItActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chuck_it)

        application.component.inject(this)
    }
}