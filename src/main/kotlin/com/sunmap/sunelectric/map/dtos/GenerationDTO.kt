package com.sunmap.sunelectric.map.dtos

import com.sunmap.sunelectric.map.enums.Duration
import java.time.LocalDateTime


data class GenerationDTO(
        val hourlySolarGeneration: Long? = null,
        val duration: Duration? =null,
        val city: String? = null,
        val dateTime: LocalDateTime? = null

)