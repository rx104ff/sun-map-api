package com.sunmap.sunelectric.map.controllers

import com.sunmap.sunelectric.map.dtos.CityInformationDTO
import com.sunmap.sunelectric.map.services.CityInformationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/city")
class CityInformationController(val cityInformationService: CityInformationService) {

    @GetMapping("/{cityName}")
    fun getCityInformationByName(@PathVariable cityName: String): CityInformationDTO {
        return cityInformationService.getCityInformationByName(cityName)
    }
}