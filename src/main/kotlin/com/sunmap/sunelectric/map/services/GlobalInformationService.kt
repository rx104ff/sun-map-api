package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.dtos.GlobalInformationDTO
import com.sunmap.sunelectric.map.repositories.GlobalInformationRepository
import org.springframework.stereotype.Service

@Service
class GlobalInformationService(val globalInformationRepository: GlobalInformationRepository) {
    fun getAllInformation(): GlobalInformationDTO {
        val globalInformation = globalInformationRepository.findTopByOrderByIdDesc()
        return GlobalInformationDTO(globalInformation.totalConsumption, globalInformation.totalGeneration, globalInformation.carbonCredit)
    }
}