package com.sunmap.sunelectric.map.utils

import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.dtos.GeneratorAccountDTO
import com.sunmap.sunelectric.map.dtos.GlobalInformationDTO
import com.sunmap.sunelectric.map.enums.SolarPlan
import com.sunmap.sunelectric.map.models.ConsumerAccount
import com.sunmap.sunelectric.map.models.GeneratorAccount
import com.sunmap.sunelectric.map.models.GlobalInformation
import org.apache.tomcat.jni.Time
import java.util.*
import java.time.LocalDateTime

data class ConsumerAccountBuilder(
        private val address: String = "145 Robison, Singapore",
        private val solarPlan: SolarPlan = SolarPlan.SolarPEAK
) {
    fun default(): ConsumerAccount {
        return ConsumerAccount(
                address = this.address,
                solarPlan = this.solarPlan
        )
    }
}

data class ConsumerAccountDTOBuilder(
        private val address: String = "145 Robison, Singapore",
        private val solarPlan: String = "SolarPEAK"
) {
    fun default(): ConsumerAccountDTO {
        return ConsumerAccountDTO(
                address = this.address,
                solarPlan = this.solarPlan
        )
    }
}

data class GeneratorAccountBuilder(
        private val address: String = "10 Anson, Singapore"
) {
    fun default(): GeneratorAccount {
        return GeneratorAccount(
                address = this.address
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