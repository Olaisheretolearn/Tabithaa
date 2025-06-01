package com.summerproject.Tabithaa.Tabithaa.dto

data class BookmarkResponse(
    val id: String,
    val url: String,
    val title: String,
    val folder: String?,
    val tags: List<String>,
    val createdAt: String
)
