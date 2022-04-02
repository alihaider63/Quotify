package com.haider.quotify.data.model

data class QuoteList(
    val count: Int,
    val totalCount: Int,
    val page: Int,
    val totalPages: Int,
    val lastItemIndex: Int,
    val results: List<Result>
)
