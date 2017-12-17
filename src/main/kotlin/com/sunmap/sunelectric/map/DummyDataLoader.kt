package com.sunmap.sunelectric.map

import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.dtos.GeneratorAccountDTO
import com.sunmap.sunelectric.map.repositories.ConsumerAccountRepository
import com.sunmap.sunelectric.map.services.CityInformationService
import com.sunmap.sunelectric.map.services.ConsumerService
import com.sunmap.sunelectric.map.services.GeneratorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
@Transactional
class DatabaseLoader @Autowired
constructor(private val consumerAccountRepository: ConsumerAccountRepository,
            private val consumerService: ConsumerService,
            private val generatorService: GeneratorService,
            private val cityInformationService: CityInformationService
) : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(vararg strings: String) {

        consumerService.saveNewConsumer(ConsumerAccountDTO(
                "145 Robinson Road, Singapore",
                "SolarPEAK",
                "SG0001",
                listOf("10 Anson Road, Singapore")

        ))

        generatorService.saveNewGenerator(GeneratorAccountDTO(
                "10 Anson Road, Singapore",
                listOf("145 Robinson Road, Singapore")
        ))

        cityInformationService.saveCityInformation("Singapore")
        cityInformationService.saveCityInformation("Tokyo")
        cityInformationService.saveCityInformation("Bangkok")
    }
}