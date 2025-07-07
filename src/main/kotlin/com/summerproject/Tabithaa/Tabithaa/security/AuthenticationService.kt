package com.summerproject.Tabithaa.Tabithaa.security

import com.summerproject.Tabithaa.Tabithaa.dto.AuthResponse
import com.summerproject.Tabithaa.Tabithaa.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
    private val encoder: Encoder
) {
    fun login(email: String, password: String): AuthResponse {
        val user = userRepository.findByEmailIgnoreCase(email)
            ?: throw RuntimeException("User not found")

        if (!encoder.matches(password, user.hashedPassword)) {
            throw RuntimeException("Invalid credentials")
        }

        val accessToken = jwtService.generateAccessToken(user.id!!.toHexString())
        val refreshToken = jwtService.generateRefreshToken(user.id!!.toHexString())

        return AuthResponse(accessToken, refreshToken)
    }
}
