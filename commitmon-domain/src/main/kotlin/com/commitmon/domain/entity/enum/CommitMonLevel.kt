package com.commitmon.domain.entity.enum

enum class CommitMonLevel(
    val pointGauge: Int,
    val commitMonImg: String
) {
    LEVEL_0(40, "/gif/egg.gif"),
    LEVEL_1(60, "/gif/grass.gif"),
    LEVEL_2(100, ""),
    LEVEL_3(140, ""),
    LEVEL_4(9999999, "");

    fun isMeetTheLvUpCondition(point: Int): Boolean {
        return this.pointGauge <= point
    }

    fun lvUp(): CommitMonLevel {
        return values()[this.ordinal + 1]
    }
}