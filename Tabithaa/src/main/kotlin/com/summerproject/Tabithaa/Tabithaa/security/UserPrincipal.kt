package com.summerproject.Tabithaa.Tabithaa.security

import com.summerproject.Tabithaa.Tabithaa.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal(private val user: User) : UserDetails {
    fun getId(): String = user.id?.toHexString()
        ?: throw IllegalStateException("User ID is null")


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getPassword(): String = user.hashedPassword
    override fun getUsername(): String = user.email

    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}
