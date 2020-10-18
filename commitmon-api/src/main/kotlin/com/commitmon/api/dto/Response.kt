package com.commitmon.api.dto

import com.commitmon.domain.entity.enum.CommitMonLevel

class CommitMonResponse(
    val isLvUp: Boolean,
    val level: CommitMonLevel,
    val point: Int
)