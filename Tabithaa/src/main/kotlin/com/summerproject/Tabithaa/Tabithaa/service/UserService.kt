package com.summerproject.Tabithaa.Tabithaa.service

import com.summerproject.Tabithaa.Tabithaa.dto.UserRegisterRequest
import com.summerproject.Tabithaa.Tabithaa.dto.UserResponse
import com.summerproject.Tabithaa.Tabithaa.dto.UserUpdateRequest
import com.summerproject.Tabithaa.Tabithaa.model.User
import com.summerproject.Tabithaa.Tabithaa.repository.UserRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Service
class UserService(
    private val userRepository: UserRepository
) {
        fun createUser(request:UserRegisterRequest):UserResponse{
            val user = User(
               email= request.email,
                hashedPassword = request.password,
                avatarUrl = request.avatarUrl
            )
            return userRepository.save(user).toDto()
        }

    fun findUserById(userId :String):UserResponse {
        val user =  userRepository.findById(ObjectId(userId))
            .orElseThrow {
                RuntimeException("User not found")}
                return user.toDto()

    }

    fun deactivateUser(userId: String): UserResponse {
        val user = userRepository.findById(ObjectId(userId))
            .orElseThrow { RuntimeException("User not found") }

        val updated = user.copy(isActive = false)
        return userRepository.save(updated).toDto()
    }

    fun updateUser(userId: String, update: UserUpdateRequest): UserResponse {
        val user = userRepository.findById(ObjectId(userId))
            .orElseThrow { RuntimeException("User not found") }

        val updated = user.copy(
            email = update.email ?: user.email,
            avatarUrl = update.avatarUrl ?: user.avatarUrl
        )

        return userRepository.save(updated).toDto()
    }



    private fun User.toDto(): UserResponse {
        return UserResponse(
            id = this.id?.toHexString() ?: "no-id",
            email = this.email,
            avatarUrl = this.avatarUrl,
            createdAt = this.createdAt.toString()
        )
    }


}