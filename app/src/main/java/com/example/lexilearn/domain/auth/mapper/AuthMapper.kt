package com.example.lexilearn.domain.auth.mapper

import com.example.lexilearn.data.auth.model.User as UserResponse
import com.example.lexilearn.domain.auth.model.User

fun UserResponse.toDomain(): User {
    return User(
        id = this.id,
        name = this.name,
        email = this.email,
        photo = this.photo,
        createdAt = this.createdAt
    )
}