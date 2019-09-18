package com.krishna.mvvm.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krishna.mvvm.data.repositary.QuotesRepositary
import com.krishna.mvvm.data.repositary.UserRepositary

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(
    private val repositary: QuotesRepositary): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  QuotesViewModel(repositary) as T
    }

}