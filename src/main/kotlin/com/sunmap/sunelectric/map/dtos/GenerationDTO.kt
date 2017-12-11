package com.sunmap.sunelectric.map.dtos

data class GenerationDTO(
        val hourlySolarGeneration: Long? = null,
        val city: String? = null,
        val dateTime: String? = null

)