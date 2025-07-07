package com.summerproject.Tabithaa.Tabithaa.dto

data class BookmarkUpdateRequest(
    val title: String?,
    val url: String?,
    val folder : String?,
    val description: String?,
    val tags :List<String>?

)
