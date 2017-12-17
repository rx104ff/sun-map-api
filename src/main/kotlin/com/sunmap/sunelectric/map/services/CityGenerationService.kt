package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.repositories.GenerationRepository
import org.springframework.stereotype.Service

@Service
class CityGenerationService(
        val generationRepository: GenerationRepository) {

    fun getSolarHourlyGenerationByCity(city: String): Int{
        val solarConsumptions: List<Long?> = generationRepository.findAllByCity(city).map { it.solarGeneration }
        return solarConsumptions.sumBy { it!!.toInt() }
    }
}