package com.sunmap.sunelectric.map.controllers

import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.dtos.GeneratorAccountDTO
import com.sunmap.sunelectric.map.services.ConsumerService
import com.sunmap.sunelectric.map.services.GeneratorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/consumer")
class ConsumerController(val consumerService: ConsumerService) {
    @GetMapping("")
    fun getAllConsumer(): List<ConsumerAccountDTO> {
        return consumerService.getAllConsumers()
    }

    @GetMapping("/{consumerAddress}")
    fun getConsumerByAddress(@PathVariable consumerAddress: String): ConsumerAccountDTO {
        return consumerService.getConsumerByAddress(consumerAddress)
    }
}