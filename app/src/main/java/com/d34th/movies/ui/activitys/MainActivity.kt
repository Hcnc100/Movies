package com.d34th.movies.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.d34th.movies.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Movies)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}