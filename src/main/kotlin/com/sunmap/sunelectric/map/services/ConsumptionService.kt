package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.repositories.ConsumptionRepository
import org.springframework.stereotype.Service

@Service
class ConsumptionService(
        val consumptionRepository: ConsumptionRepository) {
    fun getTotalHourlyConsumptionByCity(city: String): Int {
        val totalConsumptions: List<Long?> = consumptionRepository.findAllByCity(city).map { it.hourlyTotalConsumption }
        return totalConsumptions.sumBy { it!!.toInt() }
    }

    fun getSolarHourlyConsumptionByCity(city: String): Int{
        val solarConsumptions: List<Long?> = consumptionRepository.findAllByCity(city).map { it.hourlySolarConsumption }
        return solarConsumptions.sumBy { it!!.toInt() }
    }
}