package com.sunmap.sunelectric.map.models

import com.sunmap.sunelectric.map.dtos.CityInformationDTO
import javax.persistence.*

@Entity
data class CityInformation(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var name: String? = null,

        @ElementCollection
        @Column(nullable = false)
        var mapCoordinates: List<Double>? = null
) {
    fun toDto(): CityInformationDTO {
        return CityInformationDTO(
                name = name,
                mapCoordinates = mapCoordinates)
    }

    companion object {
        fun fromDto(dto: CityInformationDTO): CityInformation {
            return CityInformation(
                    mapCoordinates = dto.mapCoordinates,
                    name = dto.name
            )
        }
    }
}