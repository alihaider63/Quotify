package com.haider.quotify.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haider.quotify.data.api.QuoteService
import com.haider.quotify.data.model.QuoteList

class QuoteRepository(val quoteService: QuoteService) {

    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList> = quotesLiveData

    suspend fun getQuotes(page: Int) {
        val result = quoteService.getQuotes(page)
        if (result?.body() != null) {
            quotesLiveData.postValue(result.body())
        }
    }
}