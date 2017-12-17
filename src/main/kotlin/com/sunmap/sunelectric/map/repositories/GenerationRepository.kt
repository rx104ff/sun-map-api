package com.sunmap.sunelectric.map.repositories

import com.sunmap.sunelectric.map.models.CityGeneration
import org.springframework.data.jpa.repository.JpaRepository

interface GenerationRepository : JpaRepository<CityGeneration, Long> {
    override fun findAll(): List<CityGeneration>
    fun findAllByCity(city: String): List<CityGeneration>
}