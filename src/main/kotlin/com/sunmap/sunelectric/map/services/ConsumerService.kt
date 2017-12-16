package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.enums.SolarPlan
import com.sunmap.sunelectric.map.models.ConsumerAccount
import com.sunmap.sunelectric.map.repositories.ConsumerAccountRepository
import com.sunmap.sunelectric.map.repositories.ConsumptionRepository
import com.sunmap.sunelectric.map.repositories.GeneratorAccountRepository
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
        consumerAccountDTO.withCoordiantes(getCoordinates(consumerAccountDTO.address!!))
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

    @Throws(Exception::class)
    private fun getCoordinates(address: String): List<Double> {
        val accessToken: String = "pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4M29iazA2Z2gycXA4N2pmbDZmangifQ.-g_vE53SD2WrJ6tFX7QHmA"

        val uri = URI(
                "https",
                "api.mapbox.com",
                "/v4/geocode/mapbox.places/$address.json",
                null)
        val url = uri.toURL()
        val obj = URL("https://${uri.host}${url.path}?access_token=$accessToken")
        val con = obj.openConnection() as HttpURLConnection

        con.requestMethod = "GET"

        val responseCode = con.responseCode

        val locationJson = BufferedReader(
                InputStreamReader(con.inputStream))

        val json: JSONArray = JSONArray(locationJson.readLines().toString())
        val cooridnateseArray = json.optJSONObject(0)
                .getJSONArray("features")
                .optJSONObject(0)
                .getJSONObject("geometry")
                .getJSONArray("coordinates")
        return listOf(cooridnateseArray.getDouble(0), cooridnateseArray.getDouble(1))
    }
}