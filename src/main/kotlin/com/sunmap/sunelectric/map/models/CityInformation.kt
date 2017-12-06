package com.sunmap.sunelectric.map.models

import javax.persistence.*

@Entity
data class CityInformation(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var name: String? = null
)