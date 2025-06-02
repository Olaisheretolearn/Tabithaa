package com.summerproject.Tabithaa.Tabithaa.dto

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

data class UserRegister(
    val email:String,
    val password:String,
    val avatarUrl:String?=null,

    )
