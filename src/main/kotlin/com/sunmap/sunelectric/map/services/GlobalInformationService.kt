package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.dtos.GlobalInformationDTO
import org.springframework.stereotype.Service

@Service
class GlobalInformationService () {
    fun getAllInformation(): GlobalInformationDTO {
        return GlobalInformationDTO(1000L, 2000L, 20L)
    }
}