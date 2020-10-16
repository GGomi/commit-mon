package com.commitmon.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        var id: Long? = null,

        @Column(name = "nickname", nullable = false)
        val nickname: String,

        @Column(name = "level", nullable = false)
        val level: Long? = 0,

        @Column(name = "point", nullable = false)
        val point: Long? = 0

) : AbstractBaseAuditEntity()
