package com.sunmap.sunelectric.map.ControllerTest

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sunmap.sunelectric.map.models.ConsumerAccount
import com.sunmap.sunelectric.map.models.GlobalInformation
import com.sunmap.sunelectric.map.repositories.ConsumerAccountRepository
import com.sunmap.sunelectric.map.utils.*
import org.assertj.core.api.Assertions
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
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class ConsumerControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var consumerAccountRepository: ConsumerAccountRepository

    @Test
    fun getAllConsumers() {
        val consumerAccountOne = consumerAccountRepository.save(ConsumerAccountBuilder().default())
        val consumerAccountTwo = consumerAccountRepository.save(ConsumerAccountBuilder(address = "290 Robinson").default())

        mockMvc
                .perform(MockMvcRequestBuilders.get("/consumer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address", Matchers.`is`(consumerAccountOne.address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].solarPlan", Matchers.`is`(consumerAccountOne.solarPlan!!.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].address", Matchers.`is`(consumerAccountTwo.address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].solarPlan", Matchers.`is`(consumerAccountTwo.solarPlan!!.toString())))
    }

    @Test
    fun getConsumerByAddress() {
        val consumerAccount = consumerAccountRepository.save(ConsumerAccountBuilder().default())

        mockMvc
                .perform(MockMvcRequestBuilders.get("/consumer/${consumerAccount.address}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.`is`(consumerAccount.address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.solarPlan", Matchers.`is`(consumerAccount.solarPlan!!.toString())))

    }

    @Test
    fun saveNewConsumer_savesNewConsumer() {
        val consumerDTO = ConsumerAccountDTOBuilder().default()

        mockMvc
                .perform(MockMvcRequestBuilders.post("/consumer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Helper.serializeToJson(consumerDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.successMessage", Matchers.`is`("Consumer Is Successfully Saved")))

    }
}