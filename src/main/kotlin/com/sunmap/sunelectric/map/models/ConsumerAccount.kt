package com.sunmap.sunelectric.map.models

import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.enums.SolarPlan
import javax.persistence.*

@Entity
data class ConsumerAccount(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var address: String? = null,

        @Column(nullable = false)
        var solarPlan: SolarPlan? = null
) {
    fun toDto(): ConsumerAccountDTO {
        return ConsumerAccountDTO(
                address = address,
                solarPlan = solarPlan.toString()
        )
    }
}