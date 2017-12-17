package com.sunmap.sunelectric.map.models

import com.sunmap.sunelectric.map.dtos.CityConsumptionDTO
import com.sunmap.sunelectric.map.enums.Duration
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class CityConsumption(
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
    fun toDto(): CityConsumptionDTO {
        return CityConsumptionDTO(
                solarConsumption = solarConsumption,
                totalConsumption = totalConsumption,
                duration =  duration,
                city = city,
                dateTime = dateTime
        )
    }

    companion object {
        fun fromDto(dtoCity: CityConsumptionDTO): CityConsumption {
            return CityConsumption(
                    totalConsumption = dtoCity.totalConsumption,
                    solarConsumption = dtoCity.solarConsumption,
                    duration = dtoCity.duration,
                    dateTime = dtoCity.dateTime
            )
        }
    }

}