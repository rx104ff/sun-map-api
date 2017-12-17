package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.enums.Duration
import com.sunmap.sunelectric.map.repositories.ConsumptionRepository
import org.springframework.stereotype.Service

@Service
class CityConsumptionService(
        val consumptionRepository: ConsumptionRepository) {
    fun getTotalHourlyConsumptionByCity(city: String): Int {
        val totalConsumptions: List<Long?> = consumptionRepository.findTop24ByDurationAndCityOrderByDateTimeDesc(city, Duration.Hour).map { it.totalConsumption }
        return totalConsumptions.sumBy { it!!.toInt() }
    }

    fun getSolarHourlyConsumptionByCity(city: String): Int {
        val solarConsumptions: List<Long?> = consumptionRepository.findTop24ByDurationAndCityOrderByDateTimeDesc(city, Duration.Hour).map { it.solarConsumption }
        return solarConsumptions.sumBy { it!!.toInt() }
    }

    fun getSolarDailyConsumptionByCity(city: String): Int {
        val solarConsumptions: List<Long?> = consumptionRepository.findTop30ByDurationAndCityOrderByDateTimeDesc(city, Duration.Day).map { it.solarConsumption }
        return solarConsumptions.sumBy { it!!.toInt() }
    }

    fun getTotalDailyConsumptionByCity(city: String): Int {
        val totalConsumptions: List<Long?> = consumptionRepository.findTop30ByDurationAndCityOrderByDateTimeDesc(city, Duration.Day).map { it.totalConsumption }
        return totalConsumptions.sumBy { it!!.toInt() }
    }

    fun getSolarMonthlyConsumptionByCity(city: String): Int {
        val solarConsumptions: List<Long?> = consumptionRepository.findTop24ByDurationAndCityOrderByDateTimeDesc(city, Duration.Month).map { it.solarConsumption }
        return solarConsumptions.sumBy { it!!.toInt() }
    }

    fun getTotalMonthlyConsumptionByCity(city: String): Int {
        val totalConsumptions: List<Long?> = consumptionRepository.findTop24ByDurationAndCityOrderByDateTimeDesc(city, Duration.Month).map { it.totalConsumption }
        return totalConsumptions.sumBy { it!!.toInt() }
    }

    fun getSolarYearlyConsumptionByCity(city: String): Int {
        val solarConsumptions: List<Long?> = consumptionRepository.findByDurationAndCityOrderByDateTimeDesc(city, Duration.Year).map { it.solarConsumption }
        return solarConsumptions.sumBy { it!!.toInt() }
    }

    fun getTotalYearlyConsumptionByCity(city: String): Int {
        val totalConsumptions: List<Long?> = consumptionRepository.findByDurationAndCityOrderByDateTimeDesc(city, Duration.Year).map { it.totalConsumption }
        return totalConsumptions.sumBy { it!!.toInt() }
    }
}