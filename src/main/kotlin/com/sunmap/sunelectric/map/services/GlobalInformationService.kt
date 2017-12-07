package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.dtos.GlobalInformationDTO
import com.sunmap.sunelectric.map.models.GlobalInformation
import com.sunmap.sunelectric.map.repositories.GlobalInformationRepository
import org.springframework.stereotype.Service

@Service
class GlobalInformationService(val globalInformationRepository: GlobalInformationRepository) {
    fun getAllInformation(): GlobalInformationDTO {
        return globalInformationRepository.findTopByOrderByIdDesc().toDto()
    }

    fun saveNewInformation(globalInformationDTO: GlobalInformationDTO): GlobalInformation {
        val globalInformation = GlobalInformation.fromDto(globalInformationDTO)
        return globalInformationRepository.save(globalInformation)
    }
}