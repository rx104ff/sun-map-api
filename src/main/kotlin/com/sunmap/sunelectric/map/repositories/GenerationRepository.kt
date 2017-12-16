package com.sunmap.sunelectric.map.repositories

import com.sunmap.sunelectric.map.models.Generation
import org.springframework.data.jpa.repository.JpaRepository

interface GenerationRepository : JpaRepository<Generation, Long> {
    override fun findAll(): List<Generation>
    fun findAllByCity(city: String): List<Generation>
}