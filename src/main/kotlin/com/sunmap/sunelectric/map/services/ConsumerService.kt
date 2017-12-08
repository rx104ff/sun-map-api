package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.enums.SolarPlan
import com.sunmap.sunelectric.map.models.ConsumerAccount
import com.sunmap.sunelectric.map.repositories.ConsumerAccountRepository
import org.springframework.stereotype.Service

@Service
class ConsumerService(val consumerAccountRepository: ConsumerAccountRepository) {
    fun getConsumerByAddress(address: String): ConsumerAccountDTO {
        return consumerAccountRepository.findByAddress(address).toDto()
    }

    fun getConsumerByMssl(mssl: String): ConsumerAccountDTO {
        return consumerAccountRepository.findByMssl(mssl).toDto()
    }

    fun getAllConsumers(): List<ConsumerAccountDTO> {
        return consumerAccountRepository.findAll().map { it.toDto() }
    }

    fun saveNewConsumer(consumerAccountDTO: ConsumerAccountDTO): ConsumerAccount {
        return consumerAccountRepository.save(ConsumerAccount.fromDto(consumerAccountDTO))
    }

    fun updateConsumerPlan(mssl:String, solarPlan: SolarPlan): ConsumerAccount {
        val consumerAccount = consumerAccountRepository.findByMssl(mssl)
        consumerAccount.solarPlan = solarPlan
        return consumerAccountRepository.save(consumerAccount)
    }
}