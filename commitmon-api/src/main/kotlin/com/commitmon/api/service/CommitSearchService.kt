package com.commitmon.api.service

import com.commitmon.api.config.ApiAdvice
import com.commitmon.api.dto.CommitResponse
import com.commitmon.api.logger
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import java.lang.Exception
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Service
class CommitSearchService(
    private val apiAdvice: ApiAdvice<CommitResponse>
) {
    private val log by logger()

    private val url =
        "https://api.github.com/search/commits?q=author:##nickname## committer-date:>##targetDate##&sort=author-date&order=asc"

    fun getRecentCommit(username: String, targetDate: String): List<ZonedDateTime> {
        val header = HttpHeaders()
        header.set("accept", "application/vnd.github.cloak-preview+json")

        try {
            val response = apiAdvice.get(url.replace("##nickname##", username), header, CommitResponse::class.java).body

            if (response != null) {
                if (response.items.isNotEmpty()) {
                    return response.items.map {
                        ZonedDateTime.parse(
                            it.commit.author.date,
                            DateTimeFormatter.ISO_OFFSET_DATE_TIME
                        ).truncatedTo(ChronoUnit.DAYS)
                    }
                }
                return emptyList()
            } else {
                return emptyList()
            }
        } catch (e: Exception) {
            log.info("$username / ${e.message}")
            return emptyList()
        }


    }
}