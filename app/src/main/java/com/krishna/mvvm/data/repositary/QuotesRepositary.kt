package com.krishna.mvvm.data.repositary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.krishna.mvvm.data.db.AppDatabase
import com.krishna.mvvm.data.db.entities.Quote
import com.krishna.mvvm.data.network.MyApi
import com.krishna.mvvm.data.network.SafeApiRequest
import com.krishna.mvvm.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepositary(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever{
            saveQuotes(it)
        }
    }

    suspend fun getQuotes():LiveData<List<Quote>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes(){
        if (isFetchNeeded()){
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded():Boolean{
        return true
    }

    private fun saveQuotes(quotes: List<Quote>){
        Coroutines.io{
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}