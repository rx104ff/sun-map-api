package com.sunmap.sunelectric.map.ControllerTest

import com.sunmap.sunelectric.map.repositories.GeneratorAccountRepository
import com.sunmap.sunelectric.map.utils.ConsumerAccountBuilder
import com.sunmap.sunelectric.map.utils.GeneratorAccountBuilder
import com.sunmap.sunelectric.map.utils.GeneratorAccountDTOBuilder
import com.sunmap.sunelectric.map.utils.Helper
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
class GeneratorControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var generatorAccountRepository: GeneratorAccountRepository

    @Test
    fun getAllGenerators() {
        val generatorAccountOne = generatorAccountRepository.save(GeneratorAccountBuilder().default())
        val generatorAccountTwo = generatorAccountRepository.save(GeneratorAccountBuilder(address = "20 Anson").default())

        mockMvc
                .perform(MockMvcRequestBuilders.get("/generator")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address", Matchers.`is`(generatorAccountOne.address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].address", Matchers.`is`(generatorAccountTwo.address)))

    }

    @Test
    fun getGeneratorByAddress() {
        val consumerAccountOne = ConsumerAccountBuilder().default()
        val consumerAccountTwo = ConsumerAccountBuilder(address = "146 Robison, Singapore").default()
        val consumerAccountThree = ConsumerAccountBuilder(address = "147 Robison, Singapore").default()
        val expectedGeneratorAccount = GeneratorAccountBuilder().withConsumerAccounts(listOf(
                consumerAccountOne,
                consumerAccountTwo,
                consumerAccountThree))
        val generatorAccount = generatorAccountRepository.save(expectedGeneratorAccount)

        mockMvc
                .perform(MockMvcRequestBuilders.get("/generator/${generatorAccount.address}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.`is`(generatorAccount.address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.consumerAddress[0]", Matchers.`is`(generatorAccount.consumerAccounts[0].address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.consumerAddress[1]", Matchers.`is`(generatorAccount.consumerAccounts[1].address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.consumerAddress[2]", Matchers.`is`(generatorAccount.consumerAccounts[2].address)))

    }

    @Test
    fun saveNewGenerator() {
        val generatorDto = GeneratorAccountDTOBuilder().default()

        mockMvc
                .perform(MockMvcRequestBuilders.post("/generator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Helper.serializeToJson(generatorDto)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.successMessage", Matchers.`is`("Generator is successfully saved")))
    }

    @Test
    fun removeGenerator() {
        val generatorAccount = generatorAccountRepository.save(GeneratorAccountBuilder(id = null).default())

        mockMvc
                .perform(MockMvcRequestBuilders.put("/generator/id/${generatorAccount.id}/")
                        .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.successMessage", Matchers.`is`("Generator is successfully removed")))
    }
}
