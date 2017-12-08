package com.sunmap.sunelectric.map.controllers

import com.sunmap.sunelectric.map.dtos.GlobalInformationDTO
import com.sunmap.sunelectric.map.dtos.SuccessDTO
import com.sunmap.sunelectric.map.services.GlobalInformationService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/global")
class GlobalInformationController(val globalInformationService: GlobalInformationService) {
    @GetMapping("")
    fun getGlobalInformation(): GlobalInformationDTO{
        return globalInformationService.getAllInformation()
    }

    @PostMapping("")
    fun saveGlobalInformation(): SuccessDTO {
        return SuccessDTO(successMessage = "Global information successfully saved.")
    }
}