package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.enums.SolarPlan
import com.sunmap.sunelectric.map.models.ConsumerAccount
import com.sunmap.sunelectric.map.repositories.ConsumerAccountRepository
import com.sunmap.sunelectric.map.repositories.ConsumptionRepository
import com.sunmap.sunelectric.map.repositories.GeneratorAccountRepository
import com.sunmap.sunelectric.map.services.helpers.GeoCodeService
import org.json.JSONArray
import org.nield.kotlinstatistics.countBy
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import javax.validation.constraints.Null

@Service
class ConsumerService(val consumerAccountRepository: ConsumerAccountRepository,
                      val geoCodeService: GeoCodeService) {
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
        consumerAccountDTO.withCoordiantes(geoCodeService.getCoordinates(consumerAccountDTO.address!!))
        return consumerAccountRepository.save(ConsumerAccount.fromDto(consumerAccountDTO))
    }

    fun updateConsumerPlan(mssl: String, solarPlan: SolarPlan): ConsumerAccount {
        val consumerAccount = consumerAccountRepository.findByMssl(mssl)
        val updatedAccount = ConsumerAccount(address = consumerAccount.address,
                solarPlan = solarPlan,
                mapCoordinates = consumerAccount.mapCoordinates,
                generatorAccounts = consumerAccount.generatorAccounts,
                mssl = consumerAccount.mssl)
        consumerAccountRepository.delete(consumerAccount)
        return consumerAccountRepository.save(updatedAccount)
    }

    fun removeConsumerByMssl(mssl: String?): Long {
        return consumerAccountRepository.removeByMssl(mssl!!)
    }

    fun countForPlans(): Map<SolarPlan?, Int> {
        val consumers = consumerAccountRepository.findAll()
        return consumers.countBy(keySelector = { it.solarPlan })
    }
}