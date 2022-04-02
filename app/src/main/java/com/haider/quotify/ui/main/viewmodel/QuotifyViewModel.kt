package com.haider.quotify.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haider.quotify.data.model.QuoteList
import com.haider.quotify.data.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotifyViewModel(private val repository: QuoteRepository): ViewModel() {

    private var index = 0


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(2)
        }
    }

    val quotes: LiveData<QuoteList> = repository.quotes
}