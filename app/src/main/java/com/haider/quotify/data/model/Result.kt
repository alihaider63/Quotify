package com.haider.quotify.data.model

data class Result(
    val tags: List<String>,
    val _id: String,
    val author: String,
    val content: String,
    val authorSlug: String,
    val length: Int,
    val dateAdded: String,
    val dateModified: String
)
