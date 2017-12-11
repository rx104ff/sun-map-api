package com.sunmap.sunelectric.map.dtos

import com.sunmap.sunelectric.map.enums.Duration


data class GenerationDTO(
        val hourlySolarGeneration: Long? = null,
        val duration: Duration? =null,
        val city: String? = null,
        val dateTime: String? = null

)