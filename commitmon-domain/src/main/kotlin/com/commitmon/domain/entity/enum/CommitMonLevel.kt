package com.commitmon.domain.entity.enum

enum class CommitMonLevel(
    val pointGauge: Int
) {
    LEVEL_0(40),
    LEVEL_1(60),
    LEVEL_2(100),
    LEVEL_3(120),
    LEVEL_4(9999999)
}