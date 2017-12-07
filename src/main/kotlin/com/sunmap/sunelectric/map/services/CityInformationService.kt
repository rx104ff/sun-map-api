package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.dtos.CityInformationDTO
import com.sunmap.sunelectric.map.repositories.CityInformationRepository
import org.springframework.stereotype.Service

@Service
class CityInformationService(val cityInformationRepository: CityInformationRepository) {
    fun getCityInformationByName(cityName: String): CityInformationDTO {
        return cityInformationRepository.findByName(cityName).toDto()
    }
}