package com.summerproject.Tabithaa.Tabithaa.dto

data class UserRegisterRequest(
    val email:String,
    val password:String,
    val avatarUrl:String?=null,

    )
