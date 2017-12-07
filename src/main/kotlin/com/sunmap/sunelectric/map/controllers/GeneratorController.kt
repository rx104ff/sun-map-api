package com.sunmap.sunelectric.map.controllers

import com.sunmap.sunelectric.map.dtos.GeneratorAccountDTO
import com.sunmap.sunelectric.map.services.GeneratorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/generator")
class GeneratorController(val generatorService: GeneratorService) {
    @GetMapping("")
    fun getAllGenerator(): List<GeneratorAccountDTO> {
        return generatorService.getAllGenerators()
    }

    @GetMapping("/{generatorAddress}")
    fun getConsumerByAddress(@PathVariable generatorAddress: String): GeneratorAccountDTO {
        return generatorService.getGeneratorByAddress(generatorAddress)
    }
}