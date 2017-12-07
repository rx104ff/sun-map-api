package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.repositories.ConsumerAccountRepository
import org.springframework.stereotype.Service

@Service
class ConsumerService(val consumerAccountRepository: ConsumerAccountRepository) {
    fun getConsumerByAddress(address: String): ConsumerAccountDTO {
        return consumerAccountRepository.findByAddress(address).toDto()
    }

    fun getAllConsumers(): List<ConsumerAccountDTO> {
        return consumerAccountRepository.findAll().map{ it.toDto() }
    }
}