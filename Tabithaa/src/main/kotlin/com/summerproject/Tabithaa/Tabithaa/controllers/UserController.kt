package com.summerproject.Tabithaa.Tabithaa.controllers

import com.summerproject.Tabithaa.Tabithaa.dto.UserRegisterRequest
import com.summerproject.Tabithaa.Tabithaa.dto.UserResponse
import com.summerproject.Tabithaa.Tabithaa.dto.UserUpdateRequest
import com.summerproject.Tabithaa.Tabithaa.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService : UserService
) {

    @PostMapping("/register")
    fun createUser(@RequestBody request :UserRegisterRequest):ResponseEntity<UserResponse>{
        val createdUser = userService.createUser(request)
        return ResponseEntity.ok(createdUser)
    }

    @GetMapping("/{id}")
    fun findUser(@PathVariable id :String ):ResponseEntity<UserResponse>{
        val user = userService.findUserById(id)
        return ResponseEntity.ok(user)
    }

    @PatchMapping("/{id}")
    fun updateUser(
        @PathVariable id:String,
        @RequestBody request :UserUpdateRequest
    ):ResponseEntity<UserResponse>{
        val updatedUser = userService.updateUser(id,request)
        return ResponseEntity.ok(updatedUser)
    }

    @DeleteMapping("/{id}")
    fun deactivateUser(@PathVariable id: String): ResponseEntity<UserResponse> {
        val deactivatedUser = userService.deactivateUser(id)
        return ResponseEntity.ok(deactivatedUser)
    }

}