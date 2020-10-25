package com.commitmon.api.service

class ChooseCommitmonService(
    private val commitCalculateService: CommitCalculateService
) {
    fun getCommitMon(username: String) {
        val calcResult = commitCalculateService.calculate(username)
    }
}