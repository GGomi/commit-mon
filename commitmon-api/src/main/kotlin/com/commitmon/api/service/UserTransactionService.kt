package com.commitmon.api.service

import com.commitmon.domain.entity.User
import com.commitmon.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserTransactionService(
    private val userRepository: UserRepository
) {
    fun findByUsername(username: String): User {
        return userRepository.findByUsername(username) ?: User.init(username)
    }
}