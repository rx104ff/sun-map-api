package com.sunmap.sunelectric.map.ControllerTest

import com.sunmap.sunelectric.map.repositories.GeneratorAccountRepository
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

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
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
        val generatorAccount = generatorAccountRepository.save(GeneratorAccountBuilder().default())

        mockMvc
                .perform(MockMvcRequestBuilders.get("/generator/${generatorAccount.address}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.`is`(generatorAccount.address)))

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
}
