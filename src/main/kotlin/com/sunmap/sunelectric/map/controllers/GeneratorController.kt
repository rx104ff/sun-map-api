package com.sunmap.sunelectric.map.controllers

import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.dtos.GeneratorAccountDTO
import com.sunmap.sunelectric.map.dtos.SuccessDTO
import com.sunmap.sunelectric.map.services.GeneratorService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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

    @PostMapping("")
    fun saveGenerator(@Valid @RequestBody generatorAccountDTO: GeneratorAccountDTO): SuccessDTO {
        generatorService.saveNewGenerator(generatorAccountDTO)
        return SuccessDTO("Generator Is Successfully Saved")
    }
}