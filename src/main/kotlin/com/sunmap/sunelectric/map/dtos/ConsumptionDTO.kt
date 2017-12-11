package com.sunmap.sunelectric.map.dtos

import java.time.LocalDateTime

data class ConsumptionDTO(
        val hourlySolarConsumption: Long? = null,
        val hourlyTotalConsumption: Long? = null,
        val city: String? = null,
        val dateTime: String? = null

)