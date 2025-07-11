package com.summerproject.Tabithaa.Tabithaa.security

import com.summerproject.Tabithaa.Tabithaa.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmailIgnoreCase(email)
            ?: throw UsernameNotFoundException("User with email $email not found")

        return UserPrincipal(user)
    }
}
