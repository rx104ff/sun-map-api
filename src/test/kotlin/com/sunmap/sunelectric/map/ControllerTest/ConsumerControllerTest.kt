package com.sunmap.sunelectric.map.ControllerTest

import com.sunmap.sunelectric.map.enums.SolarPlan
import com.sunmap.sunelectric.map.models.ConsumerAccount
import com.sunmap.sunelectric.map.repositories.ConsumerAccountRepository
import com.sunmap.sunelectric.map.repositories.GeneratorAccountRepository
import com.sunmap.sunelectric.map.utils.*
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ConsumerControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var consumerAccountRepository: ConsumerAccountRepository

    @Autowired
    lateinit var generatorAccountRepository: GeneratorAccountRepository

    @Test
    fun getAllConsumers() {
        consumerAccountRepository.deleteAll()
        val generatorAccount = generatorAccountRepository.save(GeneratorAccountBuilder().default())
        val consumerAccountOne = consumerAccountRepository.save(ConsumerAccountBuilder()
                .withGeneratorAccounts(listOf(generatorAccount)))
        val consumerAccountTwo = consumerAccountRepository.save(ConsumerAccountBuilder(address = "290 Robinson Road, Singapore")
                .withGeneratorAccounts(listOf(generatorAccount)))

        mockMvc
                .perform(MockMvcRequestBuilders.get("/consumer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address", Matchers.`is`(consumerAccountOne.address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].solarPlan", Matchers.`is`(consumerAccountOne.solarPlan!!.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].mapCoordinates", Matchers.`is`(consumerAccountOne.mapCoordinates)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].generatorAddress", Matchers.`is`(listOf(generatorAccount.address))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].address", Matchers.`is`(consumerAccountTwo.address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].solarPlan", Matchers.`is`(consumerAccountTwo.solarPlan!!.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].mapCoordinates", Matchers.`is`(consumerAccountTwo.mapCoordinates)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].generatorAddress", Matchers.`is`(listOf(generatorAccount.address))))
    }

    @Test
    fun getConsumerByAddress() {
        consumerAccountRepository.deleteAll()
        val consumerAccount = consumerAccountRepository.save(ConsumerAccountBuilder().default())

        mockMvc
                .perform(MockMvcRequestBuilders.get("/consumer/address/${consumerAccount.address}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.`is`(consumerAccount.address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.solarPlan", Matchers.`is`(consumerAccount.solarPlan!!.toString())))

    }

    @Test
    fun getConsumerByMssl() {
        consumerAccountRepository.deleteAll()
        val generatorAccountOne = GeneratorAccountBuilder().default()
        val generatorAccountTwo = GeneratorAccountBuilder(address = "146 Robison, Singapore").default()
        val generatorAccountThree = GeneratorAccountBuilder(address = "147 Robison, Singapore").default()
        val consumerAccount = consumerAccountRepository.save(ConsumerAccountBuilder(mssl = "SG9002").withGeneratorAccounts(
                listOf(generatorAccountOne, generatorAccountTwo, generatorAccountThree)
        ))

        mockMvc
                .perform(MockMvcRequestBuilders.get("/consumer/mssl/${consumerAccount.mssl}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.`is`(consumerAccount.address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.solarPlan", Matchers.`is`(consumerAccount.solarPlan!!.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mssl", Matchers.`is`(consumerAccount.mssl)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.generatorAddress[0]", Matchers.`is`(consumerAccount.generatorAccounts[0].address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.generatorAddress[1]", Matchers.`is`(consumerAccount.generatorAccounts[1].address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.generatorAddress[2]", Matchers.`is`(consumerAccount.generatorAccounts[2].address)))

    }

    @Test
    fun saveNewConsumer() {
        val consumerDTO = ConsumerAccountDTOBuilder().default()

        mockMvc
                .perform(MockMvcRequestBuilders.post("/consumer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Helper.serializeToJson(consumerDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.successMessage", Matchers.`is`("Consumer is successfully saved")))

    }

    @Test
    fun updateConsumerPlan() {
        consumerAccountRepository.deleteAll()
        val consumerAccount = consumerAccountRepository.save(ConsumerAccountBuilder().default())
        val solarPlan = SolarPlan.SolarLITE

        mockMvc
                .perform(MockMvcRequestBuilders.put("/consumer/${consumerAccount.mssl}/$solarPlan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.successMessage", Matchers.`is`("Consumer's plan is successfully updated")))

        mockMvc
                .perform(MockMvcRequestBuilders.get("/consumer/mssl/${consumerAccount.mssl}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.solarPlan", Matchers.`is`(solarPlan.toString())))
    }

    @Test
    fun removeConsumer() {
        val consumerAccount = consumerAccountRepository.save(ConsumerAccountBuilder().default())

        mockMvc
                .perform(MockMvcRequestBuilders.put("/consumer/mssl/${consumerAccount.mssl}/")
                        .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.successMessage", Matchers.`is`("Consumer is successfully deleted")))
    }

    @Test
    fun countSolarPlans() {
        consumerAccountRepository.deleteAll()
        consumerAccountRepository.save(ConsumerAccount(10, "145 Robison, Singapore", SolarPlan.SolarLITE, "SG0001"))
        consumerAccountRepository.save(ConsumerAccount(20, "290 Robison, Singapore", SolarPlan.SolarPEAK, "SG0002"))
        consumerAccountRepository.save(ConsumerAccount(30, "145 Robison, Singapore", SolarPlan.SolarFLEX, "SG0003"))
        consumerAccountRepository.save(ConsumerAccount(40, "290 Robison, Singapore", SolarPlan.SolarFLEX, "SG0004"))
        consumerAccountRepository.save(ConsumerAccount(50, "145 Robison, Singapore", SolarPlan.SolarLITE, "SG0005"))
        consumerAccountRepository.save(ConsumerAccount(60, "290 Robison, Singapore", SolarPlan.SolarPEAK, "SG0006"))
        consumerAccountRepository.save(ConsumerAccount(70, "145 Robison, Singapore", SolarPlan.SolarLITE, "SG0007"))
        consumerAccountRepository.save(ConsumerAccount(80, "290 Robison, Singapore", SolarPlan.SolarPEAK, "SG0008"))
        consumerAccountRepository.save(ConsumerAccount(90, "145 Robison, Singapore", SolarPlan.SolarLITE, "SG0009"))

        mockMvc
                .perform(MockMvcRequestBuilders.get("/consumer/countPlans")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.SolarFLEX", Matchers.equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.SolarPEAK", Matchers.equalTo(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.SolarLITE", Matchers.equalTo(4)))


    }
}