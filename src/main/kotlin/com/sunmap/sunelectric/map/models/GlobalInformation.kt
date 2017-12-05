package com.sunmap.sunelectric.map.models

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType


@Entity
data class GlobalInformation(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var totalConsumption: Long? = null,

        @Column(nullable = false)
        var totalGeneration: Long? = null,

        @Column(nullable = false)
        var carbonCredit: Long? = null
)