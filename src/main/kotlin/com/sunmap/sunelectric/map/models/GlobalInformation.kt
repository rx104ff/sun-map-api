package com.sunmap.sunelectric.map.models

import com.sunmap.sunelectric.map.dtos.GlobalInformationDTO
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Column
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
        var carbonCredit: Long? = null,

        @Column(nullable = false)
        var date: LocalDateTime? = null
) {
    fun toDto(): GlobalInformationDTO {
        return GlobalInformationDTO(
                totalConsumption = totalConsumption,
                totalGeneration = totalGeneration,
                carbonCredit = carbonCredit,
                date = date
        )
    }

    companion object {
        fun fromDto(dto: GlobalInformationDTO): GlobalInformation {
            return GlobalInformation(
                    totalConsumption = dto.totalConsumption,
                    totalGeneration = dto.totalGeneration,
                    carbonCredit = dto.carbonCredit,
                    date = dto.date
            )
        }
    }
}