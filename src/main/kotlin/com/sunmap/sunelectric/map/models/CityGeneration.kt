package com.sunmap.sunelectric.map.models

import com.sunmap.sunelectric.map.dtos.GenerationDTO
import com.sunmap.sunelectric.map.enums.Duration
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class CityGeneration(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        val solarGeneration: Long? = null,

        @Column(nullable = false)
        val duration: Duration? = null,

        @Column(nullable = false)
        val city: String? = null,

        @Column(nullable = false)
        val dateTime: LocalDateTime? = null

) {
    fun toDto(): GenerationDTO {
        return GenerationDTO(
                hourlySolarGeneration = solarGeneration,
                city = city,
                duration = duration,
                dateTime = dateTime
        )
    }

    companion object {
        fun fromDto(dto: GenerationDTO): CityGeneration {
            return CityGeneration(
                    solarGeneration = dto.hourlySolarGeneration,
                    duration = dto.duration,
                    dateTime = dto.dateTime
            )
        }
    }

}