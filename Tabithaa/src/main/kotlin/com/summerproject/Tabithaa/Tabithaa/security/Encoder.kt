package com.summerproject.Tabithaa.Tabithaa.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class Encoder {
    private val bcrypt = BCryptPasswordEncoder()

    fun encode(raw:String):String{
        return bcrypt.encode(raw)
    }

    fun matches(raw:String, hashed:String ):Boolean{
        return bcrypt.matches(raw,hashed)
    }
}