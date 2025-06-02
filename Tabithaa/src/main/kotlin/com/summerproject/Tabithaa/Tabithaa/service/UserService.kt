package com.summerproject.Tabithaa.Tabithaa.service

import com.summerproject.Tabithaa.Tabithaa.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

}