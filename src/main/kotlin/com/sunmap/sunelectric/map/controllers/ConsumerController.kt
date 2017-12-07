package com.sunmap.sunelectric.map.controllers

import com.sunmap.sunelectric.map.dtos.GeneratorAccountDTO
import com.sunmap.sunelectric.map.services.GeneratorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/consumer")
class ConsumerController() {
    @GetMapping("")
    fun getAllConsumer(): List<GeneratorAccountDTO>{
        return listOf()
    }
}