package com.sunmap.sunelectric.map.repositories

import com.sunmap.sunelectric.map.models.CityInformation
import org.springframework.data.jpa.repository.JpaRepository

interface CityInformationRepository : JpaRepository<CityInformation, Long> {
    fun findByName(name: String): CityInformation
}