package com.commitmon.api.service

import com.commitmon.api.dto.CommitMonResponse
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Service
class CommitCalculateService(
    private val userTransactionService: UserTransactionService,
    private val commitSearchService: CommitSearchService
) {
    fun calculate(username: String): CommitMonResponse {
        val user = userTransactionService.findByUsername(username)

        val targetDate = user.checkPoint.truncatedTo(ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val commitList = commitSearchService.getRecentCommit(username, targetDate)
        var isLvUp = false

        if (commitList.isNotEmpty()) {
            val commitDateGroupingList = commitList.groupingBy { it }.eachCount()
            val dateList = commitDateGroupingList.keys.toList()
            val calculatedPoint = calculatePoint(dateList)


            if (calculatedPoint != user.point) {
                if (user.level.isMeetTheLvUpCondition(calculatedPoint)) {
                    user.lvUp()
                    isLvUp = true
                } else {
                    user.updatePoint(calculatedPoint)
                }

                userTransactionService.save(user)
            }
        }

        return CommitMonResponse(
            isLvUp = isLvUp,
            level = user.level,
            point = user.point
        )

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

        if (comboPoint != 0) {
            point += comboPoint
        }

        return point
    }
}