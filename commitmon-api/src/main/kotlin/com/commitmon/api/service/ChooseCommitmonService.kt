package com.commitmon.api.service

import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class ChooseCommitmonService(
    private val commitCalculateService: CommitCalculateService
) {
    fun getCommitMon(username: String): InputStream? {
        val calcResult = commitCalculateService.calculate(username)
        return javaClass.getResourceAsStream(calcResult.level.commitMonImg)
    }
}