package com.sunmap.sunelectric.map.models

import com.sunmap.sunelectric.map.dtos.ConsumptionDTO
import javax.persistence.*

@Entity
data class Consumption(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        val hourlySolarConsumption: Long? = null,

        @Column(nullable = false)
        val hourlyTotalConsumption: Long? = null,

        @Column(nullable = false)
        val city: String? = null,

        @Column(nullable = false)
        val dateTime: String? = null


) {
    fun toDto(): ConsumptionDTO {
        return ConsumptionDTO(
                hourlySolarConsumption = hourlySolarConsumption,
                hourlyTotalConsumption = hourlyTotalConsumption,
                city = city,
                dateTime = dateTime
        )
    }

    companion object {
        fun fromDto(dto: ConsumptionDTO): Consumption {
            return Consumption(
                    hourlyTotalConsumption = dto.hourlyTotalConsumption,
                    hourlySolarConsumption = dto.hourlySolarConsumption,
                    dateTime = dto.dateTime
            )
        }
    }

}