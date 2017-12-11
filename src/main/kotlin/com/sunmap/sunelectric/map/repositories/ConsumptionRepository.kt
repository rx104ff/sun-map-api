package com.sunmap.sunelectric.map.repositories

import com.sunmap.sunelectric.map.models.Consumption
import org.springframework.data.jpa.repository.JpaRepository

interface ConsumptionRepository : JpaRepository<Consumption, Long> {
    override fun findAll(): List<Consumption>
    fun findAllByCity(city: String): List<Consumption>
}