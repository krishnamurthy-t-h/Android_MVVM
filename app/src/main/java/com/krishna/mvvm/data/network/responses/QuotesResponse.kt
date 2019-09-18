package com.krishna.mvvm.data.network.responses

import com.krishna.mvvm.data.db.entities.Quote

data class QuotesResponse(
    val isSuccessful:Boolean,
    val quotes:List<Quote>
)