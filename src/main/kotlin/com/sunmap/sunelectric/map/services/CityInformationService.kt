package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.dtos.CityInformationDTO
import com.sunmap.sunelectric.map.models.CityInformation
import com.sunmap.sunelectric.map.repositories.CityInformationRepository
import com.sunmap.sunelectric.map.services.helpers.GeoCodeService
import org.springframework.stereotype.Service

@Service
class CityInformationService(val cityInformationRepository: CityInformationRepository,
                             val geoCodeService: GeoCodeService) {
    fun getCityInformationByName(cityName: String): CityInformationDTO {
        return cityInformationRepository.findByName(cityName).toDto()
    }

    fun saveCityInformation(cityName: String): CityInformation {
        val mapCoordinates = geoCodeService.getCoordinates(cityName)
        return cityInformationRepository.save(CityInformation(name = cityName, mapCoordinates = mapCoordinates))
    }

    fun removeCityInformatinByName(cityName: String): Long {
        return cityInformationRepository.removeByName(cityName)
    }
}