package com.sunmap.sunelectric.map.controllers

import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.dtos.GeneratorAccountDTO
import com.sunmap.sunelectric.map.dtos.SuccessDTO
import com.sunmap.sunelectric.map.services.ConsumerService
import com.sunmap.sunelectric.map.services.GeneratorService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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

    @GetMapping("/{mssl}")
    fun getConsumerByMssl(@PathVariable mssl: String): ConsumerAccountDTO {
        return consumerService.getConsumerByMssl(mssl)
    }

    @PostMapping("")
    fun saveConsumer(@Valid @RequestBody consumerAccountDTO: ConsumerAccountDTO ): SuccessDTO {
        consumerService.saveNewConsumer(consumerAccountDTO)
        return SuccessDTO("Consumer Is Successfully Saved")
    }
}