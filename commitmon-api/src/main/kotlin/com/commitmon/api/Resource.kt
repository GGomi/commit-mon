package com.commitmon.api

import com.commitmon.api.service.ChooseCommitmonService
import com.commitmon.api.service.CommitCalculateService
import com.commitmon.api.support.RestSupport
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/commit-mon")
class CommitMonApi(
    private val chooseCommitmonService: ChooseCommitmonService
): RestSupport() {
    @GetMapping("/test", produces = [MediaType.IMAGE_GIF_VALUE])
    fun test(
        @RequestParam("username", required = true) name: String
    ): ResponseEntity<*> {
        return dataResponse(chooseCommitmonService.getCommitMon(name))
    }
}