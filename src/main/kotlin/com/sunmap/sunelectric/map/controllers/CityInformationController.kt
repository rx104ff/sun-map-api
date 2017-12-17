package com.sunmap.sunelectric.map.controllers

import com.sunmap.sunelectric.map.dtos.CityInformationDTO
import com.sunmap.sunelectric.map.dtos.SuccessDTO
import com.sunmap.sunelectric.map.services.CityInformationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/city")
class CityInformationController(val cityInformationService: CityInformationService) {

    @GetMapping("/{cityName}")
    fun getCityInformationByName(@PathVariable cityName: String): CityInformationDTO {
        return cityInformationService.getCityInformationByName(cityName)
    }

    @PostMapping("/{cityName}")
    fun saveCityInformation(@PathVariable cityName: String): SuccessDTO {
        cityInformationService.saveCityInformation(cityName)
        return SuccessDTO(cityName + " has been saved")
    }

    @PutMapping("{cityName}")
    fun removeCityInformation(@PathVariable cityName: String): SuccessDTO {
        cityInformationService.removeCityInformatinByName(cityName)
        return SuccessDTO(cityName + " has been removed")
    }
}