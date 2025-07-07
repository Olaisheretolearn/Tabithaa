package com.summerproject.Tabithaa.Tabithaa.repository

import com.summerproject.Tabithaa.Tabithaa.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, ObjectId> {
    fun findByEmailIgnoreCase(user: String): User?

}