package com.sunmap.sunelectric.map.models

import com.sunmap.sunelectric.map.dtos.GenerationDTO
import javax.persistence.*

@Entity
data class Generation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        val hourlySolarGeneration: Long? = null,

        @Column(nullable = false)
        val city: String? = null,

        @Column(nullable = false)
        val dateTime: String? = null

) {
    fun toDto(): GenerationDTO {
        return GenerationDTO(
                hourlySolarGeneration = hourlySolarGeneration,
                city = city,
                dateTime = dateTime
        )
    }

    companion object {
        fun fromDto(dto: GenerationDTO): Generation {
            return Generation(
                    hourlySolarGeneration = dto.hourlySolarGeneration,
                    dateTime = dto.dateTime
            )
        }
    }

}