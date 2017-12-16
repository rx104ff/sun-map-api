package com.sunmap.sunelectric.map

import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.dtos.GeneratorAccountDTO
import com.sunmap.sunelectric.map.enums.SolarPlan
import com.sunmap.sunelectric.map.repositories.ConsumerAccountRepository
import com.sunmap.sunelectric.map.services.ConsumerService
import com.sunmap.sunelectric.map.services.GenerationService
import com.sunmap.sunelectric.map.services.GeneratorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component


@Component
class DatabaseLoader @Autowired
constructor(private val consumerAccountRepository: ConsumerAccountRepository,
            private val consumerService: ConsumerService,
            private val generatorService: GeneratorService
) : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(vararg strings: String) {

        consumerService.saveNewConsumer(ConsumerAccountDTO(
                "145 Robinson Road, Singapore",
                "SolarPEAK",
                "SG0001",
                listOf("10 Anson Road, Singapore"),
                listOf(103.808053, 1.351616)

        ))

        generatorService.saveNewGenerator(GeneratorAccountDTO(
                "10 Anson Road, Singapore",
                listOf("145 Robinson Road, Singapore"),
                listOf(103.81, 1.27)
        ))
    }
}