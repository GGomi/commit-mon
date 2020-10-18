package com.commitmon.domain.entity;

import com.commitmon.domain.entity.enum.CommitMonLevel
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "user")
class User constructor() : AbstractBaseAuditEntity() {

    @Column(name = "username", nullable = false)
    lateinit var username: String

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    lateinit var level: CommitMonLevel

    @Column(name = "point", nullable = false)
    var point: Int = 0
        private set

    @Column(name = "check_point", nullable = false)
    lateinit var checkPoint: ZonedDateTime
        private set

    private constructor(username: String): this() {
        this.username = username
    }

    companion object {
        fun init(username: String): User {
            return User(username).also {
                it.level = CommitMonLevel.LEVEL_0
                it.point = 0
                it.checkPoint = ZonedDateTime.now()
            }
        }
    }

    fun lvUp() {
        this.level = CommitMonLevel.values()[this.level.ordinal + 1]
        this.point = 0
        this.checkPoint = ZonedDateTime.now()
    }

    fun updatePoint(point: Int) {
        this.point = point
    }
}
