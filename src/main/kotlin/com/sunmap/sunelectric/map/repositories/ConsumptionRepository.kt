package com.sunmap.sunelectric.map.repositories

import com.sunmap.sunelectric.map.enums.Duration
import com.sunmap.sunelectric.map.models.CityConsumption
import org.springframework.data.jpa.repository.JpaRepository

interface ConsumptionRepository : JpaRepository<CityConsumption, Long> {
    override fun findAll(): List<CityConsumption>
    fun findAllByCity(city: String): List<CityConsumption>
    fun findByDurationAndCityOrderByDateTimeDesc(city: String, duration: Duration): List<CityConsumption>
    fun findTop24ByDurationAndCityOrderByDateTimeDesc(city: String, duration: Duration): List<CityConsumption>
    fun findTop30ByDurationAndCityOrderByDateTimeDesc(city: String, duration: Duration): List<CityConsumption>
}