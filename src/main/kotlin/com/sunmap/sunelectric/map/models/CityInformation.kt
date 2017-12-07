package com.sunmap.sunelectric.map.models

import com.sunmap.sunelectric.map.dtos.CityInformationDTO
import javax.persistence.*

@Entity
data class CityInformation(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var name: String? = null
) {
    fun toDto(): CityInformationDTO {
        return CityInformationDTO(name = name)
    }
}