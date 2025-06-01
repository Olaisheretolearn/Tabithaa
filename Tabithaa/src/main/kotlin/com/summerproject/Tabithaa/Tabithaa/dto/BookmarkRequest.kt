package com.summerproject.Tabithaa.Tabithaa.dto

data class BookmarkRequest(
    val url: String,
    val title: String,
    val folder: String? = null,
    val tags: List<String> = listOf()
)
