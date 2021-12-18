package com.myprojects.registrationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val searchWord = getIntent().getStringExtra(Constants.SEARCH_WORD)
    }
}