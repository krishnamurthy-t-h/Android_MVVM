package com.krishna.mvvm.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.krishna.mvvm.data.repositary.QuotesRepositary
import com.krishna.mvvm.util.lazyDeferred

class QuotesViewModel(
    repositary: QuotesRepositary
) : ViewModel() {

    val quotes by lazyDeferred {
        repositary.getQuotes()
    }
}
