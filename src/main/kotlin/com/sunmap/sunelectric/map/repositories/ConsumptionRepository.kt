package com.sunmap.sunelectric.map.repositories

import com.sunmap.sunelectric.map.enums.Duration
import com.sunmap.sunelectric.map.models.Consumption
import org.springframework.data.jpa.repository.JpaRepository

interface ConsumptionRepository : JpaRepository<Consumption, Long> {
    override fun findAll(): List<Consumption>
    fun findAllByCity(city: String): List<Consumption>
    fun findByDurationAndCityOrderByDateTimeDesc(city: String, duration: Duration): List<Consumption>
    fun findTop24ByDurationAndCityOrderByDateTimeDesc(city: String, duration: Duration): List<Consumption>
    fun findTop30ByDurationAndCityOrderByDateTimeDesc(city: String, duration: Duration): List<Consumption>
}