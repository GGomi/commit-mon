package com.commitmon.api.service

import com.commitmon.api.exception.BusinessException
import com.commitmon.api.exception.ErrorCode
import com.commitmon.api.removeSpecialCharacter
import com.commitmon.domain.repository.UserRepository
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class ChooseCommitmonService(
    private val commitCalculateService: CommitCalculateService,
    private val userRepository: UserRepository
) {
    fun getCommitMon(username: String): InputStream? {
        val filteredUsername = username.removeSpecialCharacter()

        checkUsername(filteredUsername)

        val calcResult = commitCalculateService.calculate(username)
        return javaClass.getResourceAsStream(calcResult.level.commitMonImg)

    }

    private fun checkUsername(filteredUsername: String) {
        if (filteredUsername.isEmpty()) {
            throw BusinessException(ErrorCode.INVALID_INPUT_VALUE)
        }
    }
}