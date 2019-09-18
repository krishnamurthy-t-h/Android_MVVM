package com.krishna.mvvm.data.repositary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.krishna.mvvm.data.db.AppDatabase
import com.krishna.mvvm.data.db.entities.Quote
import com.krishna.mvvm.data.network.MyApi
import com.krishna.mvvm.data.network.SafeApiRequest
import com.krishna.mvvm.data.preferences.PreferenceProvider
import com.krishna.mvvm.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


private const val MINIMAL_VAL=6
class QuotesRepositary(
    private val api: MyApi,
    private val db: AppDatabase,
    private val pref: PreferenceProvider
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
        val lastSavedAt = pref.getLastSavedAt()
        if (lastSavedAt==null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))){
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime):Boolean{
        return ChronoUnit.HOURS.between(savedAt,LocalDateTime.now())> MINIMAL_VAL
    }

    private fun saveQuotes(quotes: List<Quote>){
        Coroutines.io{
            pref.saveLastSavedAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}