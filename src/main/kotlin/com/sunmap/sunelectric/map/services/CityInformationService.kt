package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.dtos.CityInformationDTO
import com.sunmap.sunelectric.map.repositories.CityInformationRepository
import org.springframework.stereotype.Service

@Service
class CityInformationService(val cityInformationRepository: CityInformationRepository) {
    fun getCityInformationByName(cityName: String): CityInformationDTO {
        val cityInformation = cityInformationRepository.findByName(cityName)
        return CityInformationDTO(cityInformation.name)
    }
}