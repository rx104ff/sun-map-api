package com.sunmap.sunelectric.map.controllers

import com.sunmap.sunelectric.map.dtos.GlobalInformationDTO
import com.sunmap.sunelectric.map.services.GlobalInformationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/global")
class GlobalInformationController(val globalInformationService: GlobalInformationService) {
    @GetMapping("")
    fun getGlobalInformation(): GlobalInformationDTO{
        return globalInformationService.getAllInformation()
    }
}