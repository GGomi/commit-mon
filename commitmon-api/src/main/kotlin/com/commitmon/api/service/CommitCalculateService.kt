package com.commitmon.api.service

import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Service
class CommitCalculateService(
    private val userTransactionService: UserTransactionService,
    private val commitSearchService: CommitSearchService
) {
    fun calculate(username: String): String? {
        val user = userTransactionService.findByUsername(username)
        val targetDate = user.checkPoint.truncatedTo(ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        val commitList = commitSearchService.getRecentCommit(username, targetDate)

        val groupingList = commitList.groupingBy { it }.eachCount()

        return null

    }
}