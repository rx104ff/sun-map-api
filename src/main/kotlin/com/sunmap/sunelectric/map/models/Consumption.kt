package com.sunmap.sunelectric.map.models

import com.sunmap.sunelectric.map.dtos.ConsumptionDTO
import com.sunmap.sunelectric.map.enums.Duration
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Consumption(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        val solarConsumption: Long? = null,

        @Column(nullable = false)
        val totalConsumption: Long? = null,

        @Column(nullable = false)
        val duration: Duration? = null,

        @Column(nullable = false)
        val consumptionDuration: Duration? = null,

        @Column(nullable = false)
        val city: String? = null,

        @Column(nullable = false)
        val dateTime: LocalDateTime? = null


) {
    fun toDto(): ConsumptionDTO {
        return ConsumptionDTO(
                solarConsumption = solarConsumption,
                totalConsumption = totalConsumption,
                duration =  duration,
                city = city,
                dateTime = dateTime
        )
    }

    companion object {
        fun fromDto(dto: ConsumptionDTO): Consumption {
            return Consumption(
                    totalConsumption = dto.totalConsumption,
                    solarConsumption = dto.solarConsumption,
                    duration = dto.duration,
                    dateTime = dto.dateTime
            )
        }
    }

}