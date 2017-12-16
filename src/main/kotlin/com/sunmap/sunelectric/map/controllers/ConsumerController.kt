package com.sunmap.sunelectric.map.controllers

import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.dtos.SuccessDTO
import com.sunmap.sunelectric.map.enums.SolarPlan
import com.sunmap.sunelectric.map.services.ConsumerService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/consumer")
class ConsumerController(val consumerService: ConsumerService) {
    @GetMapping("")
    fun getAllConsumer(): List<ConsumerAccountDTO> {
        return consumerService.getAllConsumers()
    }

    @GetMapping("/address/{consumerAddress}")
    fun getConsumerByAddress(@PathVariable consumerAddress: String): ConsumerAccountDTO {
        return consumerService.getConsumerByAddress(consumerAddress)
    }

    @GetMapping("/mssl/{mssl}")
    fun getConsumerByMssl(@PathVariable mssl: String): ConsumerAccountDTO {
        return consumerService.getConsumerByMssl(mssl)
    }

    @PostMapping("")
    fun saveConsumer(@Valid @RequestBody consumerAccountDTO: ConsumerAccountDTO): SuccessDTO {
        consumerService.saveNewConsumer(consumerAccountDTO)
        return SuccessDTO("Consumer is successfully saved")
    }

    @PutMapping("/{mssl}/{solarPlan}")
    fun updateConsumerPlan(@PathVariable mssl: String, @PathVariable solarPlan: SolarPlan): SuccessDTO {
        consumerService.updateConsumerPlan(mssl, solarPlan)
        return SuccessDTO("Consumer's plan is successfully updated")
    }

    @PutMapping("/mssl/{mssl}")
    fun removeConsumerByMssl(@PathVariable mssl: String): SuccessDTO {
        consumerService.removeConsumerByMssl(mssl)
        return SuccessDTO("Consumer is successfully deleted")
    }

    @GetMapping("/countPlans")
    fun countConsumerPlans(): Map<SolarPlan?, Int> {
        return consumerService.countForPlans()
    }
}