package com.commitmon.api.service

import com.commitmon.domain.entity.User

class UserInfoService(
    private val userTransactionService: UserTransactionService
) {
    fun isJoined(nickName: String): User {
        return userTransactionService.findByNickName(nickName) ?: User(nickname = nickName)
    }

}