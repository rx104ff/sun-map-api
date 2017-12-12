package com.sunmap.sunelectric.map.utils

import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.dtos.GeneratorAccountDTO
import com.sunmap.sunelectric.map.dtos.GlobalInformationDTO
import com.sunmap.sunelectric.map.enums.Duration
import com.sunmap.sunelectric.map.enums.SolarPlan
import com.sunmap.sunelectric.map.models.*
import java.time.LocalDateTime

data class ConsumerAccountBuilder(
        private val address: String = "145 Robison, Singapore",
        private val solarPlan: SolarPlan = SolarPlan.SolarPEAK,
        private val mssl: String = "SG0001"
) {
    fun default(): ConsumerAccount {
        return ConsumerAccount(
                address = this.address,
                solarPlan = this.solarPlan,
                mssl = this.mssl
        )
    }

    fun withGeneratorAccounts(generatorAccounts: List<GeneratorAccount>): ConsumerAccount {
        return ConsumerAccount(
                address = this.address,
                solarPlan = this.solarPlan,
                mssl = this.mssl,
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
}

data class GeneratorAccountBuilder
(
        private val id: Long? = null,
        private
        val address: String = "10 Anson, Singapore"
) {
    fun default(): GeneratorAccount {
        return GeneratorAccount(
                id = this.id,
                address = this.address
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
        private val address: String = "10 Anson, Singapore"
) {
    fun default(): GeneratorAccountDTO {
        return GeneratorAccountDTO(
                address = this.address
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
    fun default(): Consumption {
        return Consumption(
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
    fun default(): Generation {
        return Generation(
                solarGeneration = this.solarGeneration,
                city = this.city,
                duration = this.duration,
                dateTime = this.dateTime
        )
    }
}