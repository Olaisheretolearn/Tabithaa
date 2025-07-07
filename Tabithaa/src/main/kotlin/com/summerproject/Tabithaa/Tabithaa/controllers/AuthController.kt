package com.summerproject.Tabithaa.Tabithaa.controllers

import com.summerproject.Tabithaa.Tabithaa.dto.AuthResponse
import com.summerproject.Tabithaa.Tabithaa.dto.UserLoginRequest
import com.summerproject.Tabithaa.Tabithaa.security.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthenticationService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: UserLoginRequest): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(authService.login(request.email, request.password))
    }
}
