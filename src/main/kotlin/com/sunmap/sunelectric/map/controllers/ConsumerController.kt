package com.sunmap.sunelectric.map.controllers

import com.fasterxml.jackson.databind.util.JSONPObject
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.oracle.javafx.jmx.json.JSONDocument
import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.dtos.SuccessDTO
import com.sunmap.sunelectric.map.enums.SolarPlan
import com.sunmap.sunelectric.map.services.ConsumerService
import org.springframework.web.bind.annotation.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.validation.Valid
import org.springframework.http.HttpHeaders.USER_AGENT
import jdk.nashorn.internal.runtime.ScriptingFunctions.readLine
import org.apache.commons.math3.analysis.function.Add
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type
import java.net.URI


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