package com.commitmon.domain.entity;

import com.commitmon.domain.entity.enum.CommitMonLevel
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "user")
class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        var id: Long? = null,

        @Column(name = "username", nullable = false)
        val username: String,

        @Enumerated(EnumType.STRING)
        @Column(name = "level", nullable = false)
        val level: CommitMonLevel = CommitMonLevel.LEVEL_0,

        @Column(name = "point", nullable = false)
        val point: Long = 0,

        @Column(name = "check_point", nullable = false)
        var checkPoint: ZonedDateTime = ZonedDateTime.now()

) : AbstractBaseAuditEntity() {
    companion object {
        fun init(username: String): User {
            return User(
                    username = username
            )
        }
    }
}
