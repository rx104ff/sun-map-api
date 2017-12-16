package com.sunmap.sunelectric.map.services

import com.sunmap.sunelectric.map.dtos.GeneratorAccountDTO
import com.sunmap.sunelectric.map.models.GeneratorAccount
import com.sunmap.sunelectric.map.repositories.GeneratorAccountRepository
import com.sunmap.sunelectric.map.services.helpers.GeoCodeService
import org.json.JSONArray
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL

@Service
class GeneratorService(val generatorAccountRepository: GeneratorAccountRepository,
                       val geoCodeService: GeoCodeService) {
    fun getGeneratorByAddress(address: String): GeneratorAccountDTO {
        return generatorAccountRepository.findByAddress(address).toDto()
    }

    fun getAllGenerators(): List<GeneratorAccountDTO> {
        return generatorAccountRepository.findAll().map { it.toDto() }
    }

    fun saveNewGenerator(generatorAccountDTO: GeneratorAccountDTO): GeneratorAccount {
        generatorAccountDTO.withCoordiantes(geoCodeService.getCoordinates(generatorAccountDTO.address!!))
        return generatorAccountRepository.save(GeneratorAccount.fromDto(generatorAccountDTO))
    }

    fun removeGeneratorById(id: Long): Long{
        return generatorAccountRepository.removeById(id)
    }
}
