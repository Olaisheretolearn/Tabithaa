package com.summerproject.Tabithaa.Tabithaa.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document
data class Bookmark(
    @Id val id: String? = null,
    val userId: String,
    val title: String,
    val url: String,
    val tags: List<String> = listOf(),
    val folder: String? = null,
    val createdAt: Instant = Instant.now()
)


//designed the wireframes no cow