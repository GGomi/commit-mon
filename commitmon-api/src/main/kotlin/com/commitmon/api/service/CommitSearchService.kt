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
        "https://api.github.com/search/commits?q=author:##nickname## author-date:>=##targetDate##&sort=author-date&order=asc&per_page=100&page="

    fun getRecentCommit(username: String, targetDate: String): List<ZonedDateTime> {
        val list = mutableListOf<ZonedDateTime>()

        try {
            val url = url.replace("##nickname##", username).replace("##targetDate##", targetDate)
            var page = 2
            val response = callApi(username, url, 1)

            if (response != null) {
                val totalPage = if (response.totalCount % 100 > 0) {
                    (response.totalCount / 100) + 1
                } else {
                    response.totalCount / 100
                }

                if (response.items.isNotEmpty()) {
                    list.addAll(
                        response.items.map {
                            ZonedDateTime.parse(
                                it.commit.author.date,
                                DateTimeFormatter.ISO_OFFSET_DATE_TIME
                            ).truncatedTo(ChronoUnit.DAYS)
                        })
                }

                while (page <= totalPage) {
                    val result = callApi(username, url, page)

                    if (result != null) {
                        if (result.items.isNotEmpty()) {
                            list.addAll(
                                result.items.map {
                                    ZonedDateTime.parse(
                                        it.commit.author.date,
                                        DateTimeFormatter.ISO_OFFSET_DATE_TIME
                                    ).truncatedTo(ChronoUnit.DAYS)
                                })
                        }
                    }

                    page++
                }

                return list
            } else {
                return list
            }
        } catch (e: Exception) {
            log.info("$username / ${e.message}")
            return list
        }
    }

    fun callApi(username: String, url: String, page: Int): CommitResponse? {
        val header = HttpHeaders()
        header.set("accept", "application/vnd.github.cloak-preview+json")

        return apiAdvice.get("$url$page", header, CommitResponse::class.java).body
    }
}