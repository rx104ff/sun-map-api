package com.sunmap.sunelectric.map.utils

import com.sunmap.sunelectric.map.dtos.CityInformationDTO
import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.dtos.GeneratorAccountDTO
import com.sunmap.sunelectric.map.dtos.GlobalInformationDTO
import com.sunmap.sunelectric.map.enums.Duration
import com.sunmap.sunelectric.map.enums.SolarPlan
import com.sunmap.sunelectric.map.models.*
import java.time.LocalDateTime

data class ConsumerAccountBuilder(
        private val address: String = "145 Robinson Road, Singapore",
        private val solarPlan: SolarPlan = SolarPlan.SolarPEAK,
        private val mssl: String = "SG0001",
        private val mapCoordinates: List<Double>? = listOf(103.808053, 1.351616)
) {
    fun default(): ConsumerAccount {
        return ConsumerAccount(
                address = this.address,
                solarPlan = this.solarPlan,
                mapCoordinates = mapCoordinates,
                mssl = this.mssl
        )
    }

    fun withCoordinates(coordinates: List<Double>): ConsumerAccount {
        return ConsumerAccount(
                address = this.address,
                solarPlan = this.solarPlan,
                mapCoordinates = coordinates,
                mssl = this.mssl
        )
    }

    fun withGeneratorAccounts(generatorAccounts: List<GeneratorAccount>): ConsumerAccount {
        return ConsumerAccount(
                address = this.address,
                solarPlan = this.solarPlan,
                mssl = this.mssl,
                mapCoordinates = this.mapCoordinates,
                generatorAccounts = generatorAccounts
        )
    }
}

data class ConsumerAccountDTOBuilder(
        private val address: String = "145 Robison, Singapore",
        private val solarPlan: String = "SolarPEAK",
        private val mssl: String = "SG0001"
) {
    fun default(): ConsumerAccountDTO {
        return ConsumerAccountDTO(
                address = this.address,
                solarPlan = this.solarPlan,
                mssl = this.mssl
        )
    }
    fun withCoordinates(coordinates: List<Double>): ConsumerAccountDTO {
        return ConsumerAccountDTO(
                address = this.address,
                solarPlan = this.solarPlan,
                mapCoordinates = coordinates,
                mssl = this.mssl
        )
    }
}

data class GeneratorAccountBuilder
(
        private val id: Long? = null,
        private val address: String = "10 Anson Road, Singapore",
        private val mapCoordinates: List<Double>? = listOf(103.81, 1.27)
) {
    fun default(): GeneratorAccount {
        return GeneratorAccount(
                id = this.id,
                address = this.address
        )
    }

    fun withCoordinates(coordinates: List<Double>): GeneratorAccount {
        return GeneratorAccount(
                address = this.address,
                mapCoordinates = coordinates
        )
    }

    fun withConsumerAccounts(consumerAccounts: List<ConsumerAccount>): GeneratorAccount {
        return GeneratorAccount(
                address = this.address,
                consumerAccounts = consumerAccounts
        )
    }
}

data class GeneratorAccountDTOBuilder(
        private val address: String = "10 Anson Road, Singapore",
        private val consumerAddress: List<String> =  listOf("145 Robinson Road, Singapore"),
        private val mapCoordinates: List<Double>? = null
) {
    fun default(): GeneratorAccountDTO {
        return GeneratorAccountDTO(
                consumerAddress = consumerAddress,
                address = this.address
        )
    }

    fun withCoordinates(coordinates: List<Double>): GeneratorAccountDTO {
        return GeneratorAccountDTO (
                address = this.address,
                consumerAddress = consumerAddress,
                mapCoordinates = coordinates
        )
    }
}

data class GlobalInformationDTOBuilder(
        private val totalConsumption: Long = 100000,
        private val totalGeneration: Long = 20000,
        private val carbonCredit: Long = 50,
        private val date: LocalDateTime? = null
) {
    fun default(): GlobalInformationDTO {
        return GlobalInformationDTO(
                totalConsumption = this.totalConsumption,
                totalGeneration = this.totalGeneration,
                carbonCredit = this.carbonCredit,
                date = this.date
        )
    }
}

data class GlobalInformationBuilder(
        private val totalConsumption: Long = 100000,
        private val totalGeneration: Long = 20000,
        private val carbonCredit: Long = 50,
        private val date: LocalDateTime? = null
) {
    fun default(): GlobalInformation {
        return GlobalInformation(
                totalConsumption = this.totalConsumption,
                totalGeneration = this.totalGeneration,
                carbonCredit = this.carbonCredit,
                date = this.date
        )
    }
}

data class ConsumptionBuilder(
        private val totalConsumption: Long = 100000,
        private val solarConsumption: Long = 20000,
        private val city: String = "Singapore",
        private val duration: Duration? = null,
        private val dateTime: LocalDateTime? = null
) {
    fun default(): CityConsumption {
        return CityConsumption(
                totalConsumption = this.totalConsumption,
                solarConsumption = this.solarConsumption,
                duration = this.duration,
                city = this.city,
                dateTime = this.dateTime
        )
    }
}

data class GenerationBuilder(
        private val solarGeneration: Long = 20000,
        private val city: String = "Singapore",
        private val duration: Duration? = null,
        private val dateTime: LocalDateTime? = null
) {
    fun default(): CityGeneration {
        return CityGeneration(
                solarGeneration = this.solarGeneration,
                city = this.city,
                duration = this.duration,
                dateTime = this.dateTime
        )
    }
}


data class CityInformationBuilder(
        private val name: String = "Singapore",
        private val mapCoordinates: List<Double> = listOf(103.808053, 1.351616)
) {
    fun default(): CityInformation {
        return CityInformation(
                name = name
//                mapCoordinates = mapCoordinates
        )
    }
}

data class CityInformationDTOBuilder(
        private val name: String = "Singapore",
        private val mapCoordinates: List<Double> = listOf(103.808053, 1.351616)
) {
    fun default(): CityInformationDTO {
        return CityInformationDTO(
                name = name,
                mapCoordinates = mapCoordinates
        )
    }
}