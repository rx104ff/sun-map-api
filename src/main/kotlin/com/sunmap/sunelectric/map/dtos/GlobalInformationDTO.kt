package com.sunmap.sunelectric.map.dtos

import java.time.LocalDateTime
import java.util.*

data class GlobalInformationDTO (
        val totalConsumption: Long? = null,
        val totalGeneration: Long? = null,
        val carbonCredit: Long? = null,
        val date: LocalDateTime? = null
)