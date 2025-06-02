package com.summerproject.Tabithaa.Tabithaa.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
@Document("users")
data class User(
    @Id val id :ObjectId?=null,
    val email:String,
    val hashedPassword :String,
    val avatarUrl :String?=null,
    val createdAt: Instant = Instant.now()
)
