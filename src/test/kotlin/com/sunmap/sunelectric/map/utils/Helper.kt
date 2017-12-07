package com.sunmap.sunelectric.map.utils

import com.fasterxml.jackson.databind.ObjectMapper

object Helper {
    fun serializeToJson(objectDTO: Any): String {
        return ObjectMapper().writeValueAsString(objectDTO)
    }
}