package com.commitmon.api.service

import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Service
class CommitCalculateService(
    private val userTransactionService: UserTransactionService,
    private val commitSearchService: CommitSearchService
) {
    fun calculate(username: String): Int {
        val user = userTransactionService.findByUsername(username)
        val targetDate = user.checkPoint.truncatedTo(ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val commitList = commitSearchService.getRecentCommit(username, targetDate)

        return if (commitList.isNotEmpty()) {
            val commitDateGroupingList = commitList.groupingBy { it }.eachCount()
            val dateList = commitDateGroupingList.keys.toList()
            calculatePoint(dateList)
        } else {
            0
        }

    }

    private fun calculatePoint(dateList: List<ZonedDateTime>): Int {
        var point = 0
        var target = dateList[0]
        var comboPoint = 0

        dateList.forEach {
            if (target.plusDays(1) == it) {
                comboPoint += comboPoint + 1
            } else {
                point += comboPoint + 1
                comboPoint = 0
            }

            target = it
        }

        return point
    }
}