package com.commitmon.api.service

import com.commitmon.api.config.ApiAdvice
import com.commitmon.api.dto.CommitResponse
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Service
class GithubCommitService(
    private val apiAdvice: ApiAdvice<CommitResponse>
) {
    private val url = "https://api.github.com/search/commits?q=author:##nickname##&sort=author-date"

    fun getRecentCommit(nickname: String): CommitResponse? {
        val header = HttpHeaders()
        header.set("accept", "application/vnd.github.cloak-preview+json")

        val response = apiAdvice.get(url.replace("##nickname##", nickname), header, CommitResponse::class.java).body

        if (response != null) {
            if (response.items.isNotEmpty()) {
                val time = response.items.map { ZonedDateTime.parse(it.commit.author.date, DateTimeFormatter.ISO_OFFSET_DATE_TIME).withZoneSameInstant(
                    ZoneId.of("Asia/Seoul")).toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) }
                print("hi")
            }
        }

        return response
    }
}