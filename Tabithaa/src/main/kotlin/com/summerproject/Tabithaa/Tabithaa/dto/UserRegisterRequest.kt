package com.summerproject.Tabithaa.Tabithaa.dto

data class UserRegisterRequest(
    val firstName:String,
    val email:String,
    val password:String,
    val avatarUrl:String?=null,

    )
