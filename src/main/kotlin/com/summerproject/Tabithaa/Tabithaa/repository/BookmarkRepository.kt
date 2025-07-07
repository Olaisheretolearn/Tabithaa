package com.summerproject.Tabithaa.Tabithaa.repository

import com.summerproject.Tabithaa.Tabithaa.model.Bookmark
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface BookmarkRepository :MongoRepository<Bookmark, ObjectId> {
    fun findByUserId(userId: String): List<Bookmark>

}