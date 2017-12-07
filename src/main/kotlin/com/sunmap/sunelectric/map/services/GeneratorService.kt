package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.dtos.GeneratorAccountDTO
import com.sunmap.sunelectric.map.repositories.GeneratorAccountRepository
import org.springframework.stereotype.Service

@Service
class GeneratorService(val generatorAccountRepository: GeneratorAccountRepository) {
    fun getGeneratorByAddress(address: String): GeneratorAccountDTO {
        return generatorAccountRepository.findByAddress(address).toDto()
    }

    fun getAllGenerators(): List<GeneratorAccountDTO> {
        return generatorAccountRepository.findAll().map{ it.toDto() }
    }
}